(ns advent2020.day8-test
  (:require [clojure.test :refer :all]
            [advent2020.day8 :as day8]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def example-input [[:nop +0]
                    [:acc +1]
                    [:jmp +4]
                    [:acc +3]
                    [:jmp -3]
                    [:acc -99]
                    [:acc +1]
                    [:jmp -4]
                    [:acc +6]])

(deftest step-fn-test
  (is (= [example-input #{0 1} 1 0]
         (day8/step [example-input #{0} 0 0])))
  (is (= [example-input #{0 1 2} 2 1]
         (-> [example-input #{0} 0 0]
             (day8/step)
             (day8/step))))
  (is (= [example-input #{0 1 2 3 4 6 7} 4 5]
         (->> (iterate day8/step [example-input #{0} 0 0])
              (drop 6)
              (first)))))

(deftest execute-test
  (is (= [1 0] (day8/execute [:nop +0] 0 0)))
  (is (= [1 5] (day8/execute [:acc +5] 0 0)))
  (is (= [2 5] (day8/execute [:jmp +2] 0 5))))

(deftest parse-test
  (is (= [:jmp -493]
         (day8/parse "jmp -493"))))

(def input (->> (io/resource "day8.txt")
                (slurp)
                (str/split-lines)
                (mapv day8/parse)))

(deftest solve-a
  (is (= [1 5]
         (->> (iterate day8/step [example-input #{0} 0 0])
              (drop-while (complement reduced?))
              first
              deref)))
  (is (= [108 1586]
         (->> (iterate day8/step [input #{0} 0 0])
              (drop-while (complement reduced?))
              first
              deref))))
