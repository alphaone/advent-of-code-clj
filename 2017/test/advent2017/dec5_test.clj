(ns advent2017.dec5-test
  (:require [clojure.test :refer :all]
            [advent2017.dec5 :as a]
            [clojure.java.io :as io]))

(deftest step-a-test
  (testing "it should return a new state"
    (is (= {:r [1 3 0 1 -3] :p 0}
           (a/step-a {:r [0 3 0 1 -3] :p 0})))
    (is (= {:r [2 3 0 1 -3] :p 1}
           (a/step-a {:r [1 3 0 1 -3] :p 0})))
    (is (= {:r [2 4 0 1 -3] :p 4}
           (a/step-a {:r [2 3 0 1 -3] :p 1})))
    (is (= {:r [2 4 0 1 -2] :p 1}
           (a/step-a {:r [2 4 0 1 -3] :p 4})))
    (is (= {:r [2 5 0 1 -2] :p 5}
           (a/step-a {:r [2 4 0 1 -2] :p 1})))))

(deftest step-a-test
  (testing "it should return a new state"
    (is (= {:r [1 3 0 1 -3] :p 0}
           (a/step-b {:r [0 3 0 1 -3] :p 0})))
    (is (= {:r [2 3 0 1 -3] :p 1}
           (a/step-b {:r [1 3 0 1 -3] :p 0})))
    (is (= {:r [2 2 0 1 -3] :p 4}
           (a/step-b {:r [2 3 0 1 -3] :p 1})))
    (is (= {:r [2 2 0 1 -2] :p 1}
           (a/step-b {:r [2 2 0 1 -3] :p 4})))
    (is (= {:r [2 3 0 1 -2] :p 3}
           (a/step-b {:r [2 2 0 1 -2] :p 1})))))

(deftest valid?-test
  (testing "it checks if a state is valid"
    (is (= true (a/valid? {:r [2 4 0 1 -2] :p 1})))
    (is (= false (a/valid? {:r [2 5 0 1 -2] :p 5})))
    (is (= false (a/valid? {:r [2 5 0 1 -2] :p -1})))))

(deftest solve-a-test
  (testing "it solves the puzzle"
    (is (= 4 (a/count-steps a/step-a "1\n3\n0\n1\n-3")))
    (is (= 358309
           (time (a/count-steps a/step-a (slurp (io/resource "dec5.txt"))))))))

(deftest solve-b-test
  (testing "it solves the puzzle"
    (is (= 9 (a/count-steps a/step-b "1\n3\n0\n1\n-3")))
    (is (= 28178177
           (time (a/count-steps a/step-b (slurp (io/resource "dec5.txt"))))))))