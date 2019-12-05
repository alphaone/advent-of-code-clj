(ns advent2019.dec1
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn fuel [mass]
  (-> mass (/ 3) Math/floor (- 2) int))

(defn read-input []
  (->> "dec1.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (map #(Integer/parseInt %))))

(defn solve-part1 []
  (->> (read-input)
       (map fuel)
       (reduce +)))

(defn recursive-fuel [mass]
  (if (<= mass 6)
    0
    (let [f (fuel mass)]
      (+ f (recursive-fuel f)))))

(defn solve-part2 []
  (->> (read-input)
       (map recursive-fuel)
       (reduce +)))
