(ns advent2020.day3-test
  (:require [clojure.test :refer :all]
            [advent2020.day3 :as day3]
            [clojure.java.io :as io]
            [clojure.string :as cs]))

(def example-map
  ["..##......."
   "#...#...#.."
   ".#....#..#."
   "..#.#...#.#"
   ".#...##..#."
   "..#.##....."
   ".#.#.#....#"
   ".#........#"
   "#.##...#..."
   "#...##....#"
   ".#..#...#.#"])

(deftest trees-on-slope-test
  (is (= 7
         (day3/trees-on-slope [3 1] 0 0 example-map))))

(deftest new-pos-test
  (is (= 3 (day3/new-pos [3 1] 0 11)))
  (is (= 1 (day3/new-pos [3 1] 9 11))))

(deftest new-map-test
  (is (= [:line2 :line3] (day3/new-map [3 1] [:line1 :line2 :line3])))
  (is (= [:line3] (day3/new-map [3 2] [:line1 :line2 :line3]))))

(deftest trees-test
  (is (= 1 (day3/trees 0 "#.")))
  (is (= 0 (day3/trees 1 "#."))))

(def input
  (->> (io/resource "day3.txt")
       (slurp)
       (cs/split-lines)))

(deftest solve-a
  (is (= 228
         (day3/trees-on-slope [3 1] 0 0 input))))

(deftest solve-b
  (is (= 6818112000
         (->> [[1 1] [3 1] [5 1] [7 1] [1 2]]
              (map #(day3/trees-on-slope % 0 0 input))
              (reduce *)))))
