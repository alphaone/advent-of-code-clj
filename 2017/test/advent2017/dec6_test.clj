(ns advent2017.dec6-test
  (:require [clojure.test :refer :all]
            [advent2017.dec6 :as a]
            [clojure.java.io :as io]))

(deftest distribute-test
  (testing "it distributes blocks on banks"
    (is (= [1 1 0 0] (a/distr [0 0 0 0] 0 2)))
    (is (= [2 2 1 1] (a/distr [1 1 0 0] 0 4)))
    (is (= [2 2 2 3] (a/distr [0 0 0 0] 3 9)))))

(deftest pop-max-blocks-test
  (testing "it remvoes the max blocks from banks"
    (is (= {:max   7
            :pos   2
            :banks [0 2 0 0]}
           (a/pop-max-blocks [0 2 7 0])))))

(deftest step-test
  (testing "it calcs a new state"
    (is (= [2 4 1 2] (a/step [0 2 7 0])))
    (is (= [3 1 2 3] (a/step [2 4 1 2])))
    (is (= [0 2 3 4] (a/step [3 1 2 3])))
    (is (= [1 3 4 1] (a/step [0 2 3 4])))))

(deftest solve-a-test
  (testing "it should solve the puzzle"
    (is (= 4 (first (a/solve "2\t4\t1\t2"))))
    (is (= 6681 (first (a/solve (slurp (io/resource "dec6.txt"))))))))

(deftest solve-b-test
  (testing "it should solve the puzzle"
    (is (= 4 (second (a/solve "2\t4\t1\t2"))))
    (is (= 2392 (second (a/solve (slurp (io/resource "dec6.txt"))))))))