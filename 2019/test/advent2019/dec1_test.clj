(ns advent2019.dec1-test
  (:require [clojure.test :refer :all]
            [advent2019.dec1 :as d]))

(deftest fuel-test
  (testing "it calculates the needed fuel for a given mass"
    (is (= 2 (d/fuel 12)))
    (is (= 2 (d/fuel 14)))
    (is (= 654 (d/fuel 1969)))
    (is (= 33583 (d/fuel 100756)))))

(deftest solve-part1-test
  (testing "it solves part1"
    (is (= 3173518 (d/solve-part1)))))
