(ns advent2018.dec9-test
  (:require [clojure.test :refer :all]
            [advent2018.dec9 :as a]))

(deftest new-pos-test
  (is (= 0 (a/new-pos 2 {:last-pos 0 :marbles []})))
  (is (= 1 (a/new-pos 2 {:last-pos 0 :marbles [0]})))
  (is (= 1 (a/new-pos 2 {:last-pos 1 :marbles [0 1]})))
  (is (= 3 (a/new-pos 2 {:last-pos 1 :marbles [0 2 1]})))
  (is (= 1 (a/new-pos 2 {:last-pos 3 :marbles [0 2 1 3]}))))

(deftest circle-test
  (is (= {:last-pos 0
          :marbles  [0]
          :scores   {}}
         (a/add-to-circle {:last-pos 0
                           :marbles  []
                           :scores   {}} [:A 0])))
  (is (= {:last-pos 1
          :marbles  [0 1]
          :scores   {}}
         (a/add-to-circle {:last-pos 0
                           :marbles  [0]
                           :scores   {}} [:B 1])))
  (is (= {:last-pos 3
          :marbles  [0 4 2 5 1 3]
          :scores   {}}
         (a/add-to-circle {:last-pos 1
                           :marbles  [0 4 2 1 3]
                           :scores   {}} [:F 5])))

  (is (= {:last-pos 13
          :marbles  [0 8 4 9 2 10 5 11 1 12 6 13 3 14 7]
          :scores   {}}
         (a/add-to-circle {:last-pos 11
                           :marbles  [0 8 4 9 2 10 5 11 1 12 6 13 3 7]
                           :scores   {}} [:A 14])))
  (is (= {:last-pos 6
          :marbles  [0 16 8 17 4 18 19 2 20 10 21 5 22 11 1 12 6 13 3 14 7 15]
          :scores   {:X 32}}
         (a/add-to-circle {:last-pos 13
                           :marbles  [0 16 8 17 4 18 9 19 2 20 10 21 5 22 11 1 12 6 13 3 14 7 15]
                           :scores   {}} [:X 23]))))

(deftest play-test
  (is (= [5 32] (a/play 9 23)))
  (is (= 8317 (val (a/play 10 1618))))
  (is (= 146373 (val (a/play 13 7999))))
  (is (= 2764 (val (a/play 17 1104))))
  (is (= 54718 (val (a/play 21 6111))))
  (is (= 37305 (val (a/play 30 5807)))))

(deftest solve-a
  (is (= 412959 (val (time (a/play 435 71184))))))