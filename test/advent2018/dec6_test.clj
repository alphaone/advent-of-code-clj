(ns advent2018.dec6-test
  (:require [clojure.test :refer :all]
            [advent2018.dec6 :as a]
            [clojure.java.io :as io]
            [clojure.string :as cs]))

(defonce coords
  (->> "dec6.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (map-indexed a/parse-coord)))

(deftest min-max-test
  (is (= 67 (apply min (map (comp first second) coords))))
  (is (= 357 (apply max (map (comp first second) coords))))
  (is (= 41 (apply min (map (comp second second) coords))))
  (is (= 353 (apply max (map (comp second second) coords)))))

(deftest manhatten-dist-test
  (is (= 10 (a/manhatten-dist [0 0] [5 5])))
  (is (= 10 (a/manhatten-dist [5 5] [0 0]))))

(deftest nearest-test
  (is (= 4 (a/nearest [0 0] [[1 [3 0]]
                             [2 [1 4]]
                             [3 [2 2]]
                             [4 [0 2]]
                             [5 [2 1]]])))
  (is (= nil (a/nearest [0 0] [[1 [3 0]]
                             [2 [1 4]]
                             [3 [2 2]]
                             [4 [0 2]]
                             [5 [2 0]]]))))

(deftest find-first-that-stayed-the-same-test
  (is (= [:b 2]
         (a/find-first-that-stayed-the-same
           [[:a 1] [:b 2] [:c 3]]
           [[:c 4] [:a 2] [:b 2]]))))

(deftest solve-a
  (is (= [46 3569]
         (time (a/solve-a [67 358] [41 354] coords)))))

(deftest solve-b
  (is (= 48978
         (time (a/solve-b [67 358] [41 354] coords)))))
