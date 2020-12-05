(ns advent2020.day5-test
  (:require [clojure.test :refer :all]
            [advent2020.day5 :as day5]
            [clojure.string :as cs]
            [clojure.java.io :as io]
            [clojure.set :as set]))

(deftest seat-id-test
  (is (= 357 (day5/seat-id "FBFBBFFRLR"))))

(def input
  (->> (io/resource "day5.txt")
       (slurp)
       (cs/split-lines)))

(deftest solve-a
  (is (= 858 (apply max (map day5/seat-id input)))))

(deftest solve-b
  (let [boarding-passes (map day5/seat-id input)
        all-seats (set (range
                         (apply min boarding-passes)
                         (apply max boarding-passes)))]
    (is (= #{557}
           (set/difference
             all-seats
             boarding-passes)))))
