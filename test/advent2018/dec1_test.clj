(ns advent2018.dec1-test
  (:require [clojure.test :refer :all]
            [advent2018.dec1 :as a]))

(deftest sum-up-frequencies-test
  (is (= 3 (->> [+1 +1 +1]
                (reduce +))))
  (is (= 0 (->> [+1 +1 -2]
                (reduce +))))
  (is (= -6 (->> [-1 -2 -3]
                 (reduce +)))))

(deftest interim-frequencies-test
  (is (= 0
         (->> [+1 -1]
              (reduce a/first-double-freq {:sum 0 :interim #{0}}))))
  (is (= 10
         (->> (cycle [+3, +3, +4, -2, -4])
              (reduce a/first-double-freq {:sum 0 :interim #{0}}))))
  (is (= 5
         (->> (cycle [-6, +3, +8, +5, -6])
              (reduce a/first-double-freq {:sum 0 :interim #{0}}))))
  (is (= 14
         (->> (cycle [+7, +7, -2, -7, -4])
              (reduce a/first-double-freq {:sum 0 :interim #{0}})))))

(deftest solve-a-test
  (is (= 520
         (a/solve-a))))

(deftest solve-b-test
  (is (= 394
         (a/solve-b))))

