(ns advent2017.dec7-test
  (:require [clojure.test :refer :all]
            [advent2017.dec7 :as a]
            [clojure.java.io :as io]))

(deftest parse-line-test
  (testing "it parses a line"
    (is (= {:name    "pbga"
            :weight  66
            :holding []}
           (a/parse-line "pbga (66)")))
    (is (= {:name    "fwft"
            :weight  72
            :holding ["ktlj" "cntj" "xhth"]}
           (a/parse-line "fwft (72) -> ktlj, cntj, xhth")))))

(deftest solve-a-test
  (testing "it finds the root node"
    (is (= "hlqnsbe"
           (-> (slurp (io/resource "dec7.txt"))
               (a/all-programs)
               (a/find-root))))))

(deftest tree-test
  (testing "it should"
    (is (= {:holding [{:holding []
                       :name    "abc"
                       :weight  1
                       :cum-weight  1}
                      {:holding []
                       :name    "def"
                       :weight  2
                       :cum-weight  2}]
            :name    "ghi"
            :weight  4
            :cum-weight  7}
           (a/->tree {"abc" {:name    "abc"
                             :weight  1
                             :holding []}
                      "def" {:name    "def"
                             :weight  2
                             :holding []}
                      "ghi" {:name    "ghi"
                             :weight  4
                             :holding ["abc" "def"]}}
                     "ghi")))))

(deftest balanced?-test
  (testing "it should"
    (is (= false (a/balanced?  :w [{:w 10} {:w 10} {:w 10} {:w 9}])))
    (is (= true (a/balanced?  :w [{:w 10} {:w 10} {:w 10} {:w 10}])))))

(deftest find-unbalanced-value-test
  (testing "it should"
    (is (= {:w 9}
           (a/find-unbalanced  :w [{:w 10} {:w 10} {:w 10} {:w 9}])))))

(deftest find-wrong-weight-test
  (testing "it should"
    (testing "it should"
      (let [tree (a/->tree (-> (slurp (io/resource "dec7.txt"))
                               (a/all-programs)
                               (a/named))
                           "hlqnsbe")]
        (is (= {:siblings          (2102 2097 2097 2097 2097)
                :wrong-weight-node {:cum-weight 2102
                                    :weight     1998}}
               (a/find-wrong-weight [] tree)))))))
