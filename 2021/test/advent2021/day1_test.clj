(ns advent2021.day1-test
  (:require [clojure.test :refer :all]
            [advent2021.day1 :as d]
            [clojure.java.io :as io]
            [clojure.string :as cs]))

(def input
  (->> (io/resource "day1.txt")
       (slurp)
       (cs/split-lines)
       (map #(Integer/parseInt %))))

(deftest solve-a
  (is (= 7
         (->> [199 200 208 210 200 207 240 269 260 263]
              (d/count-incrs))))
  (is (= 1559
         (->> input
              (d/count-incrs)))))

(deftest solve-b
  (is (= 5
         (->> [199 200 208 210 200 207 240 269 260 263]
              (d/window-sum)
              (d/count-incrs))))
  (is (= 1600
         (->> input
              (d/window-sum)
              (d/count-incrs)))))
