(ns advent2017.dec8-test
  (:require [clojure.test :refer :all]
            [advent2017.dec8 :as a]
            [clojure.java.io :as io]
            [clojure.string :as cs]))

(deftest parse-line-test
  (testing "it should parse a line"
    (is (= {:cmd  {:var :b :op :inc :val 5}
            :cond {:var :a :op :> :val 1}}
           (a/parse-line "b inc 5 if a > 1")))
    (is (= {:cmd  {:var :abc :op :dec :val -10}
            :cond {:var :def :op :>= :val -20}}
           (a/parse-line "abc dec -10 if def >= -20")))))

(deftest step-test
  (testing "it should"
    (is (= {:b 5}
           (a/step {}
                   {:cmd  {:var :b :op :inc :val 5}
                    :cond {:var :a :op :< :val 10}})))
    (is (= {}
           (a/step {}
                   {:cmd  {:var :b :op :inc :val 5}
                    :cond {:var :a :op :> :val 10}})))))

(deftest max-test
  (testing "it should"
    (is (= 20
           (a/max-value {:a 1 :b 20 :c 20 :d -39})))))

(deftest solve-a-test
  (testing "it should"
    (is (= 5946
           (->> (slurp (io/resource "dec8.txt"))
                (cs/split-lines)
                (map a/parse-line)
                (reduce a/step {:register {}})
                :register
                (a/max-value))))))

(deftest solve-a-test
  (testing "it should"
    (is (= 6026
           (->> (slurp (io/resource "dec8.txt"))
                (cs/split-lines)
                (map a/parse-line)
                (reduce a/step {:register {}})
                :max
                (apply max))))))
