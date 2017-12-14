(ns advent2017.dec7
  (:require [clojure.string :as cs]))

(defn parse-line [line]
  (let [[_ name weight h] (re-find #"(\w+)\s+\((\d+)\)(?: -> (.*))?" line)
        holding (if h (cs/split h #", ") [])]
    {:name    name
     :weight  (Integer/parseInt weight)
     :holding holding}))

(defn all-programs [input]
  (->> (cs/split-lines input)
       (map parse-line)))

(defn named [ps]
  (into {} (map (fn [p] [(:name p) p]) ps)))

(defn find-root [programs]
  (let [all-names (mapcat (fn [p] (:holding p)) programs)]
    (->> programs
         (remove (fn [p] (contains? (set all-names) (:name p))))
         first
         :name)))

(defn descendants [all-nodes node-id]
  (get-in all-nodes [node-id :holding]))

(defn ->branch [node kids]
  (assoc node
    :holding kids
    :cum-weight (reduce + (conj (map :cum-weight kids) (:weight node)))))

(defn ->tree [all-nodes node-id]
  (let [node (get all-nodes node-id)]
    (->branch node (map (partial ->tree all-nodes)
                        (descendants all-nodes node-id)))))

(defn balanced? [key nodes]
  (prn "balanced" (map key nodes))
  (= 1 (count (frequencies (map key nodes)))))

(defn find-unbalanced [key nodes]
  (let [fs  (frequencies (map key nodes))
        least-freq-weight (first (reduce (fn [[mw mf] [w f]] (if (< f mf) [w f] [mw mf])) [-1 100] fs))]
    (first (filter (fn [n] (= least-freq-weight (key n))) nodes))))

(defn find-wrong-weight [siblings node]
  (prn "find-wrong-weight" (map :cum-weight (:holding node)))
  (let [kids (:holding node)]
    (if (balanced? :cum-weight kids)
      {:wrong-weight-node (select-keys node [:weight :cum-weight])
       :siblings          (map :cum-weight siblings)}
      (find-wrong-weight kids (find-unbalanced :cum-weight kids)))))