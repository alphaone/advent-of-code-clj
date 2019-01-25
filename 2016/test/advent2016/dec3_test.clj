(ns advent2016.dec3-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [advent2016.dec3 :as a]))

(deftest a
  (testing "it knows if a triangle is possible"
    (is (= false (a/possible? [5 10 25])))
    (is (= true (a/possible? [5 10 14])))
    (is (= true (a/possible? [14 10 5]))))
  (testing "it counts the possible triangles"
    (is (= 2 (a/count-possible [[2 2 2]
                                [3 3 3]
                                [3 3 10]])))
    (let [input (slurp (io/resource "dec3.txt"))]
      (is (= 1050 (-> input (a/parse-a) (a/count-possible))))
      (is (= 1921 (-> input (a/parse-b) (a/count-possible)))))))

(deftest parse-test
  (testing "it parses a string into triangles"
    (is (= [[942 136 297] [103 324 576]]
           (a/parse-a "  942  136  297\n  103  324  576")))))

(deftest parse-b-test
  (testing "it parses a string into triangles"
    (is (= [[101 102 103]
            [301 302 303]
            [501 502 503]
            [201 202 203]
            [401 402 403]
            [601 602 603]]
           (a/parse-b "  101 301 501\n102 302 502\n103 303 503\n201 401 601\n202 402 602\n203 403 603")))))