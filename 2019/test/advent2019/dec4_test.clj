(ns advent2019.dec4-test
  (:require [clojure.test :refer :all]
            [advent2019.dec4 :as d]))

(def puzzle-input (range 156218 (inc 652527)))

(deftest contains-at-least-double?-test
  (is (d/contains-freq-chars? #(>= % 2) "122345"))
  (is (not (d/contains-freq-chars? #(>= % 2) "123789")))
  (is (d/contains-freq-chars? #(= % 2) "112233"))
  (is (d/contains-freq-chars? #(= % 2) "111122"))
  (is (not (d/contains-freq-chars? #(= % 2) "123444"))))

(deftest never-decreases?-test
  (is (d/never-decreases? "122345"))
  (is (d/never-decreases? "111123"))
  (is (d/never-decreases? "135679"))
  (is (not (d/never-decreases? "223450"))))

(deftest valid?-test
  (is (d/valid? #(>= % 2) "122345"))
  (is (not (d/valid? #(>= % 2) "123789")))
  (is (not (d/valid? #(>= % 2) "223450"))))

(deftest solve-part1-test
  (is (= 1694
         (->> puzzle-input
              (map str)
              (filter (partial d/valid? #(>= % 2)))
              count))))

(deftest solve-part2-test
  (is (= 1148
         (->> puzzle-input
              (map str)
              (filter (partial d/valid? #(= % 2)))
              count))))
