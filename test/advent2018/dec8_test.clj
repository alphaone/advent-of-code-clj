(ns advent2018.dec8-test
  (:require [clojure.test :refer :all]
            [advent2018.dec8 :as a]
            [clojure.java.io :as io]
            [clojure.string :as cs]))

(def input (->> "dec8.txt"
                (io/resource)
                (slurp)
                (#(cs/split % #"\s"))
                (map #(Integer/parseInt %))))

(deftest sum-meta-entries-test
  (is (= [[] 150]
         (a/sum-meta-entries 0 [2 3 1 3 0 1 12 10 11 12 1 1 0 1 99 2 1 1 2])))
  (is (= [[] 138]
         (a/sum-meta-entries 0 [2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2])))
  (is (= [[] 33]
         (a/sum-meta-entries 0 [0 3 10 11 12])))
  (is (= [[2] 99]
         (a/sum-meta-entries 0 [0 1 99 2])))
  (is (= [[] 101]
         (a/sum-meta-entries 0 [1 1 0 1 99 2]))))

(deftest solve-a
  (is (= [() 36627]
         (a/sum-meta-entries 0 input))))