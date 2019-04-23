(ns advent2018.dec12
  (:require [clojure.string :as cs]))

(defn apply-rules [pots rules]
  (get rules pots (nth pots 2)))

(defn pots-on-pos [all-pots pos]
  (cs/join (map #(get all-pots % \.) (range (- pos 2) (+ pos 3)))))

(defn f [rules old-pots new-pots pos]
  (->> rules
       (apply-rules (pots-on-pos old-pots pos))
       (assoc new-pots pos)))

(defn plant? [[_ v]] (= v \#))

(defn min-pot [all-pots]
  (let [relevant-positions (->> all-pots (filter plant?) (keys))]
    (- (apply min relevant-positions) 2)))

(defn max-pot [all-pots]
  (let [relevant-positions (->> all-pots (filter plant?) (keys))]
    (+ (apply max relevant-positions) 2)))

(defn next-gen [rules old-pots]
  (let [positions (range (min-pot old-pots) (inc (max-pot old-pots)))]
    (reduce (partial f rules old-pots) {} positions)))


(defn sum-up-plant-pots [pots]
  (->> pots
       (filter plant?)
       (map first)
       (reduce +)))

(defn pots->string [all-pots]
  [(first (sort (keys all-pots)))
   (cs/join (map second (sort-by first all-pots)))])

(defn string->pots
  ([input]
   (string->pots 0 input))
  ([starting-pos input]
   (->> input
        (map-indexed (fn [i x] [(+ starting-pos i) x]))
        (into {}))))

(defn evolve [rules starting-pots]
  (->> starting-pots
       (iterate (partial next-gen rules))))

(defn print-gen [i [last curr]]
  (let [sum-last (sum-up-plant-pots last)
        sum-curr (sum-up-plant-pots curr)]
    (println (format "%3d: %d (%d)" (inc i) sum-curr (- sum-curr sum-last)))))

(defn print-gens [rules starting-pots n]
  (doall (map-indexed print-gen (partition 2 1 (take n (evolve rules starting-pots)))))
  nil)

; + 51 in summed up plant pots starting from gen 99 (6244)
(defn sum-up-plant-pots-b [number-of-generations]
  (+ 6244 (* 51 (- number-of-generations 99))))
