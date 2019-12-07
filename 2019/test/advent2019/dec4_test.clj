(ns advent2019.dec4-test
  (:require [clojure.test :refer :all]
            [advent2019.dec4 :as d]))

(def puzzle-input (range 156218 (inc 652527)))

(deftest contains-double?-test
  (is (d/contains-double? "122345"))
  (is (not (d/contains-double? "123789"))))

(deftest never-decreases?-test
  (is (d/never-decreases? "122345"))
  (is (d/never-decreases? "111123"))
  (is (d/never-decreases? "135679"))
  (is (not (d/never-decreases? "223450"))))

(deftest valid?-test
  (is (d/valid? "122345"))
  (is (not (d/valid? "123789")))
  (is (not (d/valid? "223450"))))

(deftest solve-part1-test
  (is (= 1694
         (->> puzzle-input
              (map str)
              (filter d/valid?)
              count))))
