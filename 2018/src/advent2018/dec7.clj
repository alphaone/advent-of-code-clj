(ns advent2018.dec7
  (:require [clojure.set :as set]))

(defn parse-line [line]
  (let [[_ s1 s2] (re-find #"Step (\w) .* step (\w)" line)]
    [s1 s2]))

(defn empty-graph [parsed-lines]
  (into {} (map (fn [s] [s #{}]) (set (mapcat identity parsed-lines)))))

(defn build-graph [parsed-lines]
  (reduce
    (fn [graph [s1 s2]] (update graph s2 conj s1))
    (empty-graph parsed-lines)
    parsed-lines))

(defn next-step [graph]
  (->> graph
       (filter (comp empty? val))
       (sort-by key)
       (ffirst)))

(defn remove-done [graph done]
  (->> graph
       (map (fn [[s deps]] [s (set/difference deps #{done})]))
       (into {})))

(defn single-step [graph]
  (let [next (next-step graph)]
    [next (-> graph (dissoc next) (remove-done next))]))

(defn solve-a [graph]
  (loop [graph graph
         res   ""]
    (let [[next-step graph'] (single-step graph)
          res' (str res next-step)]
      (if (not next-step)
        res'
        (recur graph' res')))))

(defn separate [f coll]
  ((juxt (partial filter f) (partial remove f)) coll))

(defn work [{:keys [workers]}]
  (let [[done worker'] (->> workers
                            (map (fn [[w t]] [w (dec t)]))
                            (separate (fn [[w t]] (zero? t))))]
    {:done    (map first done)
     :workers worker'}))

(def number-of-workers 5)
(def default-step-time 60)

(defn step-time [step]
  (let [st (-> step (name) (char-array) (first) (int) (- 64))]
    (+ default-step-time st)))

(defn assign-work [graph workers]
  (reduce
    (fn [{g :graph w :workers :as acc} i]
      (if-let [next (next-step g)]
        {:workers (conj w [next (step-time next)])
         :graph   (dissoc g next)}
        acc))
    {:graph   graph
     :workers workers}
    (range (count workers) number-of-workers)))

(defn solve-b [graph]
  (reduce
    (fn [{:keys [graph workers]} sec]
      (let [{workers' :workers done :done} (work {:workers workers})
            graph' (reduce remove-done graph done)]
        (if (and (empty? workers') (empty? graph'))
          (reduced sec)
          (assign-work graph' workers'))))
    {:graph graph :workers []}
    (range)))