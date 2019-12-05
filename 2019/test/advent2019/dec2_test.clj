(ns advent2019.dec2-test
  (:require [clojure.test :refer :all]
            [advent2019.dec2 :as d]))

(deftest step-test
  (testing "it runs a single operation"
    (is (= [2 0 0 0 99] (d/process-instruction [1 0 0 0 99] 0)))
    (is (= [2 3 0 6 99] (d/process-instruction [2 3 0 3 99] 0)))
    (is (= [2 4 4 5 99 9801] (d/process-instruction [2 4 4 5 99 0] 0)))))

(deftest op-test
  (is (= + (d/op [1 0 0 0] 0)))
  (is (= * (d/op [2 0 0 0] 0))))

(deftest process-test
  (testing "it should"
    (is (= [30 1 1 4 2 5 6 0 99]
           (d/process [1 1 1 4 99 5 6 0 99] 0)))
    (is (= [3500 9 10 70 2 3 11 0 99 30 40 50]
           (d/process [1 9 10 3 2 3 11 0 99 30 40 50] 0)))))

(deftest solve-part1-test
  (testing "it solves part1"
    (is (= 5434663 (d/solve-part1 12 2)))))

(deftest solve-part2-test
  (testing "it solves part2"
    (is (= [45 59 19690720]
           (d/solve-part2)))))
