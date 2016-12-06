(ns advent2016.dec5-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [digest]
            [advent2016.dec5 :as a]
            [clojure.string :as cs]))

(deftest md5-test
  (testing "it calcs the md5 hash"
    (is (= "900150983cd24fb0d6963f7d28e17f72"
           (a/md5 "abc")))))

(deftest next-char-test
  (testing "it returns the first md5 hash with five zeros"
    (let [md5-hashes (a/md5-with-five-zeros "abc")]
      (is (= "00000155f8105dff7f56ee10fa9b9abd"
             (first md5-hashes )))
      (is (= "000008f82c5b3924a1ecbebf60344e00"
             (second md5-hashes )))
      (is (= "18f47a30"
             (a/password md5-hashes))))))

(deftest puzzle-test
  (testing "it solves the puzzle"
    (is (= "c6697b55"
           (-> "ffykfhsq"
               (a/md5-with-five-zeros)
               (a/password))))))