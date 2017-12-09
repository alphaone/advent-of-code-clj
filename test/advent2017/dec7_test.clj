(ns advent2017.dec7-test
  (:require [clojure.test :refer :all]
            [advent2017.dec7 :as a]
            [clojure.java.io :as io]))

(deftest parse-line-test
  (testing "it parses a line"
    (is (= {:name "pbga" 
            :weight 66 
            :holding []}
           (a/parse-line "pbga (66)")))
    (is (= {:name "fwft" 
            :weight 72 
            :holding ["ktlj" "cntj" "xhth"]}
           (a/parse-line "fwft (72) -> ktlj, cntj, xhth")))))

(deftest solve-a-test
  (testing "it finds the root node"
    (is (=  "hlqnsbe"
            (a/find-root (slurp (io/resource "dec7.txt")))))))
