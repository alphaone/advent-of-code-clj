(ns advent2020.day1-test
  (:require [clojure.test :refer :all]
            [advent2020.day1 :as d]
            [clojure.java.io :as io]
            [clojure.string :as cs]))

(def input
  (->> (io/resource "day1.txt")
       (slurp)
       (cs/split-lines)
       (map #(Integer/parseInt %))))

(deftest solve-a
  (is (= 514579
         (->> [1721, 979, 366, 299, 675, 1456]
              (d/find-pair 2020)
              first
              (reduce *))))
  (is (= 436404
         (->> input
              (d/find-pair 2020)
              first
              (reduce *)))))

(deftest find-pair-test
  (testing "find pair with sum of 2020"
    (is (= [[1721 299]] (d/find-pair 2020 [1721, 979, 366, 299, 675, 1456])))))

(deftest pairs-test
  (is (= [] (d/pairs [])))
  (is (= [] (d/pairs [1])))
  (is (= [[1 2]] (d/pairs [1 2])))
  (is (= [[1 2] [1 3] [2 3]] (d/pairs [1 2 3]))))

(deftest solve-b
  (is (= 241861950
         (->> [1721, 979, 366, 299, 675, 1456]
              (d/find-triple 2020)
              first
              (reduce *))))
  (is (= 274879808
         (->> input
              (d/find-triple 2020)
              first
              (reduce *)))))

(deftest find-triple-test
  (testing "find triple with sum of 2020"
    (is (= [[979 366 675]] (d/find-triple 2020 [1721, 979, 366, 299, 675, 1456])))))

(deftest triples-test
  (is (= [] (d/triples [])))
  (is (= [] (d/triples [1])))
  (is (= [] (d/triples [1 2])))
  (is (= [[1 2 3]] (d/triples [1 2 3])))
  (is (= [[1 2 3] [1 2 4] [1 3 4] [2 3 4]] (d/triples [1 2 3 4]))))
