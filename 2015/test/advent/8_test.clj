(ns advent.8-test
  (:require [clojure.test :refer :all]
            [advent.8 :as a]))

(deftest replace-ascii-test
  (testing "it replaces ascii chars"
    (is (= "\\u0027"
           (a/replace-ascii-code "\\x27")))
    (is (= "\\xgi"
           (a/replace-ascii-code "\\xgi")))))

(deftest mem-length-test
  (testing "it gets the memory size"
    (is (= 0
           (a/mem-length "\"\"")))
    (is (= 3
           (a/mem-length "\"abc\"")))
    (is (= 7
           (a/mem-length "\"aaa\\\"aaa\"")))
    (is (= 1
           (a/mem-length "\"\\x27\"")))
    (let [input (-> (slurp "resources/8.txt")
                    (clojure.string/split #"\n")
                    first)]
      (is (= 29
             (a/mem-length input))))
    (let [input (-> (slurp "resources/8.txt")
                    (clojure.string/split #"\n")
                    (get 111))]
      (is (= 30
             (a/mem-length input))))))

(deftest code-length-test
  (testing "it gets the memory size"
    (is (= 2
           (a/code-length "\"\"")))
    (is (= 5
           (a/code-length "\"abc\"")))
    (is (= 10
           (a/code-length "\"aaa\\\"aaa\"")))
    (is (= 6
           (a/code-length "\"\\x27\"")))
    (let [input (-> (slurp "resources/8.txt")
                    (clojure.string/split #"\n")
                    first)]
      (is (= 38
             (a/code-length input))))
    (let [input (-> (slurp "resources/8.txt")
                    (clojure.string/split #"\n")
                    (get 111))]
      (is (= 40
             (a/code-length input))))))

(deftest diff-test
  (testing "it calcs the diff"
    (is (= 2
           (a/diff "\"\"")))
    (is (= 12
           (a/diff "\"\"\n\"abc\"\n\"aaa\\\"aaa\"\n\"\\x27\"")))
    (is (= 1333
           (a/diff (slurp "resources/8.txt"))))))
