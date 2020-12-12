(ns advent2020.day12-test
  (:require [clojure.test :refer :all]
            [advent2020.day12 :as day12]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse [line]
  (let [[_ cmd value] (re-find #"([NSWEFRL])(\d+)" line)]
    [(keyword cmd) (Integer/parseInt value)]))

(def input (->> (io/resource "day12.txt")
                (slurp)
                (str/split-lines)
                (map parse)))

(def example-input (map parse ["F10" "N3" "F7" "R90" "F11"]))

(deftest move-test
  (is (= [0 -10 90] (day12/move [0 0 90] [:N 10])))
  (is (= [0 10 90] (day12/move [0 0 90] [:S 10])))
  (is (= [10 0 90] (day12/move [0 0 90] [:E 10])))
  (is (= [-10 0 90] (day12/move [0 0 90] [:W 10])))
  (is (= [0 0 0] (day12/move [0 0 90] [:R 270])))
  (is (= [0 0 180] (day12/move [0 0 90] [:L 270])))

  (is (= [0 -10 0] (day12/move [0 0 0] [:F 10])))
  (is (= [10 0 90] (day12/move [0 0 90] [:F 10])))
  (is (= [0 10 180] (day12/move [0 0 180] [:F 10])))
  (is (= [-10 0 270] (day12/move [0 0 270] [:F 10]))))

(deftest solve-a
  (is (= [17 8 180]
         (reduce day12/move [0 0 90] example-input)))
  (is (= 1533
         (->> input
              (reduce day12/move [0 0 90])
              (butlast)
              (map #(Math/abs %))
              (apply +)))))

