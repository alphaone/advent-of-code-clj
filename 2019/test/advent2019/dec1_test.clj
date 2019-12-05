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

(deftest recursive-fuel-test
  (testing "it calculates the needed fuel (even for the mass of the fuel)"
    (is (= 2 (d/recursive-fuel 12)))
    (is (= 966 (d/recursive-fuel 1969)))
    (is (= 50346 (d/recursive-fuel 100756)))))

(deftest solve-part2-test
  (testing "it solves part2"
    (is (= 4757427 (d/solve-part2)))))
