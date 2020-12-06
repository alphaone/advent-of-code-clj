(ns advent2020.day6-test
  (:require [clojure.test :refer :all]
            [advent2020.day6 :as day6]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def example-input "abc\n\na\nb\nc\n\nab\nac\n\na\na\na\na\n\nb")

(deftest unique-answers-test
  (is (= 6
         (day6/any-answers ["abcx" "abcy" "abcz"]))))

(deftest input->groups-test
  (is (= [["abc"]
          ["a" "b" "c"]
          ["ab" "ac"]
          ["a" "a" "a" "a"]
          ["b"]]
         (day6/input->groups example-input))))

(def input
  (slurp (io/resource "day6.txt")))

(deftest solve-a
  (is (= 11
         (->> example-input
              (day6/input->groups)
              (map day6/any-answers)
              (reduce +))))
  (is (= 6443
         (->> input
              (day6/input->groups)
              (map day6/any-answers)
              (reduce +)))))
