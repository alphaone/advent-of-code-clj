(ns advent2020.day13-test
  (:require [clojure.test :refer :all]
            [advent2020.day13 :as day13]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse [input]
  (let [[line line2] (str/split-lines input)]
    [(Long/parseLong line)
     (->> (str/split line2 #",")
          (filter #(re-find #"\d" %))
          (map #(Long/parseLong %))
          )]))

(def input
  (->> (io/resource "day13.txt")
       (slurp)
       (parse)))

(def example-input
  (parse "939\n7,13,x,x,59,x,31,19"))

(deftest next-departure-test
  (is (= 945 (day13/next-departure 939 7) ))
  (is (= 944 (day13/next-departure 939 59) )))

(deftest solve-a
  (is (= 295
         (day13/earliest-bus example-input)))
  (is (= 2845
         (day13/earliest-bus input))))
