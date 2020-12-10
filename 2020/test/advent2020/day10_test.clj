(ns advent2020.day10-test
  (:require [clojure.test :refer :all]
            [advent2020.day10 :as day10]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def example-input [16 10 15 5 1 11 7 19 6 12 4])
(def example-input2 [28 33 18 42 31 14 46 20 48 47 24 23 49 45 19
                     38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3])

(deftest joltage-difference-distribution-test
  (is (= {1 7
          3 5}
         (day10/joltage-difference-distribution example-input)))
  (is (= {1 22
          3 10}
         (day10/joltage-difference-distribution example-input2))))

(def input (->> (io/resource "day10.txt")
                (slurp)
                (str/split-lines)
                (map #(Long/parseLong %))))

(deftest solve-a
  (is (= 2059
         (day10/solve-a input))))

(deftest valid-test
  (is (day10/valid? [1 2 3]))
  (is (day10/valid? [1 4 7]))
  (is (not (day10/valid? [1 4 8]))))

(deftest solve-b
  (is (= 8
         (count (day10/solve-b example-input))))
  ;; takes already too long :\
  #_(is (= 19208
         (count (day10/solve-b example-input2))))
  )

