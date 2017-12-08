(ns advent2017.dec4-test
  (:require [clojure.test :refer :all]
            [advent2017.dec4 :as a]
            [clojure.java.io :as io]))

(deftest valid?-test
  (testing "it checks if a passphrase is valid"
    (is (= true (a/valid-a? "aa bb cc dd ee")))
    (is (= false (a/valid-a? "aa bb cc dd aa")))
    (is (= true (a/valid-a? "aa bb cc dd aaa")))))

(deftest solve-a-test
  (testing "it should"
    (is (= 383
           (a/count-valids a/valid-a? (slurp (io/resource "dec4.txt")))))))

(deftest valid-b?-test
  (testing "it checks if a passphrase is valid"
    (is (= true (a/valid-b? "abcde fghij")))
    (is (= false (a/valid-b? "abcde xyz ecdab")))
    (is (= true (a/valid-b? "a ab abc abd abf abj")))
    (is (= true (a/valid-b? "iiii oiii ooii oooi oooo")))
    (is (= false (a/valid-b? "oiii ioii iioi iiio")))))

(deftest solve-b-test
  (testing "it should"
    (is (= 265
           (a/count-valids a/valid-b? (slurp (io/resource "dec4.txt")))))))