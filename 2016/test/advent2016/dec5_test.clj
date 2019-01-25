(ns advent2016.dec5-test
  (:require [clojure.test :refer :all]
            [digest]
            [advent2016.dec5 :as a]))

(deftest md5-test
  (testing "it calcs the md5 hash"
    (is (= "900150983cd24fb0d6963f7d28e17f72"
           (a/md5 "abc")))))

(defonce abc-md5s (a/md5-with-five-zeros "abc"))

(deftest password-test
  (testing "it returns the first md5 hash with five zeros"
    (is (= "00000155f8105dff7f56ee10fa9b9abd"
           (first abc-md5s)))
    (is (= "000008f82c5b3924a1ecbebf60344e00"
           (second abc-md5s)))

    (is (= "18f47a30"
           (a/password-a abc-md5s)))
    (is (= "05ace8e3"
           (a/password-b abc-md5s)))))

(deftest sixth-and-seventh-test
  (testing "it finds the pos and the char in a string"
    (is (= [0 "5"]
           (a/sixth-and-seventh-letter "*****05***")))))

(deftest build-password-test
  (testing "it builds the password from given position and chars"
    (is (= "a_______"
           (a/build-password "________" [0 "a"])))
    (is (= "a______x"
           (a/build-password "a_______" [7 "x"]))))
  (testing "it does not override exisiting chars"
    (is (= "a_______"
           (a/build-password "a_______" [0 "b"])))))

(defonce puzzle-md5s (a/md5-with-five-zeros "ffykfhsq"))

(deftest puzzle-test
  (testing "it solves the puzzle"
    (is (= "c6697b55"
           (a/password-a puzzle-md5s)))
    (is (= "8c35d1ab"
           (a/password-b puzzle-md5s)))))