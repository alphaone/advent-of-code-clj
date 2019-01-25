(ns advent.10-test
  (:require [clojure.test :refer :all]
            [advent.10 :as a]))

(deftest transform-test
  (testing "1 becomes 11 (1 copy of digit 1)."
    (is (= "11"
           (a/transform "1"))))
  (testing "11 becomes 21 (2 copies of digit 1)."
    (is (= "21"
           (a/transform "11"))))
  (testing "21 becomes 1211 (one 2 followed by one 1)."
    (is (= "1211"
           (a/transform "21"))))
  (testing "1211 becomes 111221 (one 1, one 2, and two 1s)."
    (is (= "111221"
           (a/transform "1211"))))
  (testing "111221 becomes 312211 (three 1s, two 2s, and one 1)."
    (is (= "312211"
           (a/transform "111221")))))

(deftest solver-puzzle-test
  (testing "it solves the puzzle"
    (is (= 329356
           (->> (iterate a/transform "3113322113")
                (take 41)
                (last)
                (count))))))

(deftest solver-puzzle2-test
  (testing "it solves the puzzle"
    (is (= 4666278
           (->> (iterate a/transform "3113322113")
                (take 51)
                (last)
                (count))))))