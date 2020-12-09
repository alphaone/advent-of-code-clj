(ns advent2020.day9-test
  (:require [clojure.test :refer :all]
            [advent2020.day9 :as day9]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(deftest valid?
  (is (= true (day9/valid? [35 20 15 25 47] 40)))
  (is (= true (day9/valid? [65 95 102 117 150] 182)))
  (is (= false (day9/valid? [95 102 117 150 182] 127))))

(def input (->> (io/resource "day9.txt")
                (slurp)
                (str/split-lines)
                (map #(Long/parseLong %))))

(deftest solve-a
  (is (= 127
         (day9/solve-a 5 [35 20 15 25 47 40 62 55 65 95 102 117 150 182 127 219 299 277 309 576])))
  (is (= 2089807806
         (day9/solve-a 25 input))))
