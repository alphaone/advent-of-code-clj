(ns advent2017.dec9-test
  (:require [clojure.test :refer :all]
            [advent2017.dec9 :as a]
            [clojure.java.io :as io]))

(deftest cleanup-test
  (testing "it cleans up garbage"
    (is (= "ab" (a/remove-garbage "a<>b")))
    (is (= "abc" (a/remove-garbage "a<>b<>c")))
    (is (= "ab" (a/remove-garbage "a<random characters>b")))
    (is (= "ab" (a/remove-garbage "a<{!>}>b")))
    (is (= "ab" (a/remove-garbage "a<!!>b")))
    (is (= "ab" (a/remove-garbage "a<!!!>>b")))
    (is (= "ab" (a/remove-garbage "a<{o\"i!a,<{i<a>b")))))

(deftest count-garbage-test
  (testing "it cleans up garbage"
    (is (= 0 (count (a/garbage "a<>b"))))
    (is (= 0 (count (a/garbage "a<>b<>c"))))
    (is (= 17 (count (a/garbage "a<random characters>b"))))
    (is (= 2 (count (a/garbage "a<{!>}>b"))))
    (is (= 0 (count (a/garbage "a<!!>b"))))
    (is (= 0 (count (a/garbage "a<!!!>>b"))))
    (is (= 10 (count (a/garbage "a<{o\"i!a,<{i<a>b"))))))

(deftest parse-to-tree-test
  (testing "it should"
    (is (= [] (a/parse "{}")))
    (is (= [[[]]] (a/parse "{{{}}}")))
    (is (= [[[] []]] (a/parse "{{{},{}}}")))
    (is (= [[[] [] [[]]]] (a/parse "{{{},{},{{}}}}")))))

(deftest score-test
  (testing "it should"
    (is (= 1 (a/score 1 [])))
    (is (= 6 (a/score 1 [[[]]])))
    (is (= 5 (a/score 1 [[] []])))
    (is (= 16 (a/score 1 [[[] [] [[]]]])))))

(deftest solve-a-test
  (testing "it should"
    (is (= 12505
           (->> (slurp (io/resource "dec9.txt"))
                (a/remove-garbage)
                (a/parse)
                (a/score 1))))))

(deftest solve-b-test
  (testing "it should"
    (is (= 6671
           (->> (slurp (io/resource "dec9.txt"))
                (a/garbage)
                count)))))