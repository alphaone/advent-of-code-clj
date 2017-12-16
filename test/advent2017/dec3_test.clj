(ns advent2017.dec3-test
  (:require [clojure.test :refer :all]
            [advent2017.dec3 :as a]))

(deftest coords-for-shell-test
  (testing "it calcs all (ordered) coords of one shell"
    (is (= [{:x 0 :y 0}] (a/coords-for-shell 0)))
    (is (= [{:x 1 :y 0} {:x 1 :y 1}
            {:x 0 :y 1} {:x -1 :y 1}
            {:x -1 :y 0} {:x -1 :y -1}
            {:x 0 :y -1} {:x 1 :y -1}]
           (a/coords-for-shell 1)))
    (is (= [{:x 2 :y -1} {:x 2 :y 0} {:x 2 :y 1} {:x 2 :y 2}
            {:x 1 :y 2} {:x 0 :y 2} {:x -1 :y 2} {:x -2 :y 2}
            {:x -2 :y 1} {:x -2 :y 0} {:x -2 :y -1} {:x -2 :y -2}
            {:x -1 :y -2} {:x 0 :y -2} {:x 1 :y -2} {:x 2 :y -2}]
           (a/coords-for-shell 2)))))

(deftest solve-a-test
  (testing "it finds the given position and returns its distance"
    (is (= 371
           (->> (a/all-coords)
                (drop (dec 368078))
                first
                a/manhatten-dist)))))

(deftest neighbor-coords-test
  (testing "it finds the neighbors of a coord"
    (is (= [{:x -1 :y -1} {:x -1 :y 0} {:x -1 :y 1}
            {:x 0 :y -1} {:x 0 :y 1}
            {:x 1 :y -1} {:x 1 :y 0} {:x 1 :y 1}]
           (a/neighbor-coods {:x 0 :y 0})))))

(deftest step-test
  (testing "it calcs a new version of the adj-sums map"
    (is (= {{:x 0 :y 0} 1
            {:x 1 :y 0} 1}
           (a/step 1000 {{:x 0 :y 0} 1}
                   {:x 1 :y 0})))
    (is (= {{:x 0 :y 0} 1
            {:x 1 :y 0} 1
            {:x 1 :y 1} 2}
           (a/step 1000 {{:x 0 :y 0} 1
                         {:x 1 :y 0} 1}
                   {:x 1 :y 1})))))
(deftest solve-b-test
  (testing "it finds the first sum of input"
    (is (= 369601
           (reduce (partial a/step 368078)
                   {{:x 0 :y 0} 1}
                   (drop 1 (a/all-coords)))))))