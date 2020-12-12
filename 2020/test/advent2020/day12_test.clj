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

(deftest move2-test
  (is (= [0 0 10 -11] (day12/move2 [0 0 10 -1] [:N 10])))
  (is (= [0 0 10 9] (day12/move2 [0 0 10 -1] [:S 10])))
  (is (= [0 0 20 -1] (day12/move2 [0 0 10 -1] [:E 10])))
  (is (= [0 0 0 -1] (day12/move2 [0 0 10 -1] [:W 10])))

  (is (= [0 0 -10 1] (day12/move2 [0 0 10 -1] [:R 180])))
  (is (= [0 0 -10 1] (day12/move2 [0 0 10 -1] [:L 180])))
  (is (= [0 0 1 10] (day12/move2 [0 0 10 -1] [:R 90])))
  (is (= [0 0 -1 -10] (day12/move2 [0 0 10 -1] [:L 90])))
  (is (= [0 0 -1 -10] (day12/move2 [0 0 10 -1] [:R 270])))
  (is (= [0 0 1 10] (day12/move2 [0 0 10 -1] [:L 270])))

  (is (= [100 -10 10 -1] (day12/move2 [0 0 10 -1] [:F 10])))
  (is (= [170 -38 10 -4] (day12/move2 [100 -10 10 -4] [:F 7])))
  )

(deftest solve-b
  (is (= [214 72 4 10]
         (reduce day12/move2 [0 0 10 -1] example-input)))
  (is (= 25235
           (->> input
                (reduce day12/move2 [0 0 10 -1])
                (take 2)
                (map #(Math/abs %))
                (apply +)))))
