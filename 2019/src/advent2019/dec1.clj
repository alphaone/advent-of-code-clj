(ns advent2019.dec1
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn fuel [mass]
  (-> mass (/ 3) Math/floor (- 2) int))

(defn solve-part1 []
  (->> "dec1.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (map #(Integer/parseInt %))
       (map fuel)
       (reduce +)))

