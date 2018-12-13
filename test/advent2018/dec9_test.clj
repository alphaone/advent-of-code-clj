(ns advent2018.dec9-test
  (:require [clojure.test :refer :all]
            [advent2018.dec9 :as a])
  (:import (java.util ArrayDeque)))

(deftest rotate!-test
  (let [deque (ArrayDeque. [:a :b :c :d])]
    (a/rotate! deque 1)
    (is (= [:b :c :d :a] (into [] deque)))
    (a/rotate! deque 2)
    (is (= [:d :a :b :c] (into [] deque)))
    (a/rotate! deque -3)
    (is (= [:a :b :c :d] (into [] deque)))))

(deftest circle-test
  (is (= [0]
         (->> (a/add-to-circle {:marbles (ArrayDeque. [])} [:A 0])
              :marbles 
              (into []))))
  (is (= [1 0]
         (->> (a/add-to-circle {:marbles (ArrayDeque. [0])} [:B 1])
              :marbles 
              (into []))))
  (is (= [5 1 3 0 4 2]
         (->> (a/add-to-circle {:marbles (ArrayDeque. [4 2 1 3 0])} [:F 5])
              :marbles 
              (into []))))
  (is (= [14 7 0 8 4 9 2 10 5 11 1 12 6 13 3 ]
         (->> (a/add-to-circle {:marbles (ArrayDeque. [13 3 7 0 8 4 9 2 10 5 11 1 12 6])} [:A 14])
              :marbles
              (into []))))
  (is (= {}
         (->> (a/add-to-circle {:scores {} 
                                :marbles (ArrayDeque. [13 3 7 0 8 4 9 2 10 5 11 1 12 6])} [:A 14])
              :scores)))
  (is (= [19 2 20 10 21 5 22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 ]
         (->> (a/add-to-circle {:marbles (ArrayDeque. [22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 9 19 2 20 10 21 5])
                                :scores  {}} [:X 23])
              :marbles
              (into []))))
  (is (= {:X 32}
         (->> (a/add-to-circle {:marbles (ArrayDeque. [22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 9 19 2 20 10 21 5])
                                :scores  {}} [:X 23])
              :scores))))

(deftest play-test
  (is (= [5 32] (a/play 9 23)))
  (is (= 8317 (val (a/play 10 1618))))
  (is (= 146373 (val (a/play 13 7999))))
  (is (= 2764 (val (a/play 17 1104))))
  (is (= 54718 (val (a/play 21 6111))))
  (is (= 37305 (val (a/play 30 5807)))))

(deftest solve-a-test
  (is (= 412959 (val (time (a/play 435 71184))))))

(deftest solve-b-test
  (is (= 3333662986 (val (time (a/play 435 7118400))))))
