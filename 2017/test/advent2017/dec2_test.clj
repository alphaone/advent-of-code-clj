(ns advent2017.dec2-test
  (:require [clojure.test :refer :all]
            [advent2017.dec2 :as a]
            [clojure.java.io :as io]))

(deftest parse-line-test
  (testing "it should parse a line"
    (is (= [12 34 56 78]
           (a/parse-line "12 34 56 78")))
    (is (= [12 34 56 78]
           (a/parse-line "12\t34\t56\t78")))))

(deftest diff-test
  (testing "it should find diff between max and min in line"
    (is (= 8 (a/diff [5 1 9 5])))
    (is (= 4 (a/diff [7 5 3])))
    (is (= 6 (a/diff [2 4 6 8])))))

(deftest checksum-test
  (testing "it should"
    (is (= 18 (a/checksum "5 1 9 5\n7 5 3\n2 4 6 8")))))

(deftest solve-a-test
  (testing "it should solve puzzle A"
    (is (= 47623 (a/checksum (slurp (io/resource "dec2.txt")))))))

(deftest evenly-division-test
  (testing "it should"
    (is (= 4 (a/even-division [5 9 2 8])))
    (is (= 3 (a/even-division [9 4 7 3])))
    (is (= 2 (a/even-division [3 8 6 5])))))

(deftest solve-b-test
  (testing "it should solve puzzle A"
    (is (= 312 (a/checksum-b (slurp (io/resource "dec2.txt")))))))