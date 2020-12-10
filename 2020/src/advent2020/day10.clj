(ns advent2020.day10
  (:require [clojure.string :as str]))

(defn joltage-difference-distribution [input]
  (let [device (+ 3 (apply max input))
        wall 0]
    (->> [wall device]
         (concat input)
         (sort)
         (partition 2 1)
         (map (fn [[a b]] (- b a)))
         (frequencies))))

(defn solve-a [input]
  (let [dist (joltage-difference-distribution input)]
    (apply * (map dist ((juxt first last)
                        (sort (keys dist)))))))

(defn valid? [chain]
  (every? (fn [[a b]] (<= (- b a) 3)) (partition 2 1 chain)))

(defn vec-remove
  [pos coll]
  (vec (concat (subvec coll 0 pos) (subvec coll (inc pos)))))

(defn valid-chains [wall device chain]
  (let [chains (->> (for [i (range (count chain))]
                      (vec-remove i (vec chain)))
                    (filter (fn [chain] (valid? (concat [wall] chain [device])))))]
    (cons chain (mapcat (partial valid-chains wall device) chains))))

(defn solve-b [input]
  (let [device (+ 3 (apply max input))
        wall 0
        sorted (sort input)
        chains (valid-chains wall device sorted)
        ]
    (set (concat [sorted] chains ))))

