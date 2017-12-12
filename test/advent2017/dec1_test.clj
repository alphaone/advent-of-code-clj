(ns advent2017.dec1-test
  (:require [clojure.test :refer :all]
            [advent2017.dec1 :as a]
            [clojure.java.io :as io]))

(deftest make-ring-test
  (testing "it should"
    (is (= "1231"
           (#'a/make-ring "123")))))

(deftest captcha-a-test
  (testing "it should"
    (is (= 3 (a/captcha-a "1122")))
    (is (= 0 (a/captcha-a "1234")))
    (is (= 4 (a/captcha-a "1111")))
    (is (= 9 (a/captcha-a "91212129")))))

(deftest solve-a-test
  (testing "it should"
    (is (= 1203
           (a/captcha-a (slurp (io/resource "dec1.txt")))))))

(deftest captcha-b-test
  (testing "it should"
    (is (= 6 (a/captcha-b "1212")))
    (is (= 0 (a/captcha-b "1221")))
    (is (= 4 (a/captcha-b "123425")))
    (is (= 12 (a/captcha-b "123123")))
    (is (= 4 (a/captcha-b "12131415")))))

(deftest matches?-test
  (testing "it should"
    (is (= \1 (a/matching-digits "1212" 0)))
    (is (= \2 (a/matching-digits "1212" 1)))
    (is (= \1 (a/matching-digits "1212" 2)))
    (is (= \2 (a/matching-digits "1212" 3)))

    (is (= nil (a/matching-digits "1221" 0)))
    (is (= nil (a/matching-digits "1221" 1)))
    (is (= nil (a/matching-digits "1221" 2)))
    (is (= nil (a/matching-digits "1221" 3)))))

(deftest solve-b-test
  (testing "it should"
    (is (= 1146
           (a/captcha-b (slurp (io/resource "dec1.txt")))))))

(deftest shift-test
  (testing "it "
    (is (= [:c :d :a :b]
           (a/shift 2 [:a :b :c :d])))))
