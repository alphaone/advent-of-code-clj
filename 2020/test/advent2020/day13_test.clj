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
          (map #(Long/parseLong %)))]))

(def input
  (->> (io/resource "day13.txt")
       (slurp)
       (parse)))

(def example-input
  (parse "939\n7,13,x,x,59,x,31,19"))

(deftest next-departure-test
  (is (= 945 (day13/next-departure 939 7)))
  (is (= 944 (day13/next-departure 939 59))))

(deftest solve-a
  (is (= 295
         (day13/earliest-bus example-input)))
  (is (= 2845
         (day13/earliest-bus input))))

(defn parse2 [input]
  (let [[_ line2] (str/split-lines input)]
    (->> (str/split line2 #",")
         (keep-indexed
           (fn [i x]
             (when (not= x "x")
               (let [x (Long/parseLong x)]
                 [x (mod i x)])))))))

(def input2
  (->> (io/resource "day13.txt")
       (slurp)
       (parse2)
       (sort-by first)))

(deftest combine-test
  (is (= [91 77]
         (day13/combine-bus [7 0] [13 1])))
  (is (= [1365 896]
         (reduce day13/combine-bus [[7 0] [13 1] [15 4]]))))

(deftest solve-b
  (is (= [3162341 1068781]
         (reduce day13/combine-bus (parse2 "939\n7,13,x,x,59,x,31,19"))))
  (is (= [4199 3417]
         (reduce day13/combine-bus (parse2 "\n17,x,13,19"))))
  (is (= [1687931 1261476]
         (reduce day13/combine-bus (parse2 "\n67,7,x,59,61"))))
  (is (= [5876813119 1202161486]
         (reduce day13/combine-bus (parse2 "\n1789,37,47,1889"))))
  (is (= [2265213528143033 487905974205117]
         (reduce day13/combine-bus input2))))
