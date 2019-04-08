(ns advent2018.dec11-test
  (:require [clojure.test :refer :all]
            [advent2018.dec11 :as a]))

(deftest power-level-test
  (testing "it gets the power level of a fuel cell"
    (is (= 4 (a/power-level 8 [3 5])))
    (is (= -5 (a/power-level 57 [122 79])))
    (is (= 0 (a/power-level 39 [217 196])))
    (is (= 4 (a/power-level 71 [101 153])))))

(deftest square-power-level-test
  (testing "it sum up the power levels in a 3x3 square"
    (is (= 29 (a/square-power-level 18 [33 45])))))

(deftest rack-id-test
  (is (= 13 (a/rack-id 3))))

(deftest hundereds-digit-test
  (testing "it gets the hundreds digit"
    (is (= 3 (a/hundreds-digit 12345)))))

(deftest find-max-power-level-test
  (is (= {:max-power-level 29
          :best-coords     [33 45]}
         (a/find-max-power-level 18
                                 {:max-power-level 10
                                  :best-coords     [9 9]} [33 45])))
  (is (= {:max-power-level 30
          :best-coords     [9 9]}
         (a/find-max-power-level 18
                                 {:max-power-level 30
                                  :best-coords     [9 9]} [33 45]))))

(deftest best-square-test
  (testing "it gets the square with the max power level"
    (is (= [33 45] (a/best-square 18)))
    (is (= [20 46] (a/best-square 4151)))))
