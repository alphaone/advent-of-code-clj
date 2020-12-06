(ns advent2020.day6-test
  (:require [clojure.test :refer :all]
            [advent2020.day6 :as day6]
            [clojure.java.io :as io]
            [clojure.set :as set]))

(def example-input "abc\n\na\nb\nc\n\nab\nac\n\na\na\na\na\n\nb")

(deftest unique-answers-test
  (is (= 6
         (day6/count-answers set/union ["abcx" "abcy" "abcz"])))
  (is (= 3
         (day6/count-answers set/intersection ["abcx" "abcy" "abcz"]))))

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
              (map (partial day6/count-answers set/union))
              (reduce +))))
  (is (= 6443
         (->> input
              (day6/input->groups)
              (map (partial day6/count-answers set/union))
              (reduce +)))))

(deftest solve-b
  (is (= 6
         (->> example-input
              (day6/input->groups)
              (map (partial day6/count-answers set/intersection))
              (reduce +))))
  (is (= 3232
         (->> input
              (day6/input->groups)
              (map (partial day6/count-answers set/intersection))
              (reduce +)))))
