(ns advent2016.dec4-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [advent2016.dec4 :as a]))

(deftest checksum-test
  (testing "it calcs the check-sum of a room name"
    (is (= "abxyz" (a/checksum "aaaaa-bbb-z-y-x-123")))
    (is (= "abcde" (a/checksum "a-b-c-d-e-f-g-h-987")))
    (is (= "oarel" (a/checksum "not-a-real-room-404"))))
  (testing "it knows when a room is real"
    (is (a/real? ["aaaaa-bbb-z-y-x" 123 "abxyz"]))
    (is (a/real? ["a-b-c-d-e-f-g-h" 987 "abcde"]))
    (is (a/real? ["not-a-real-room" 404 "oarel"]))
    (is (not (a/real? ["totally-real-room" 200 "decoy"])))))

(deftest compare-test
  (testing "it can compare letter frequencies"
    (is (= 1 (a/compare-letter-freq [\a 3] [\b 4])))
    (is (= -1 (a/compare-letter-freq [\a 4] [\b 4])))))

(deftest parse-test
  (testing "it parses a room into name and checksum"
    (is (= ["aaaaa-bbb-z-y-x" 123 "abxyz"]
           (a/parse "aaaaa-bbb-z-y-x-123[abxyz]")))
    (is (= ["totally-real-room" 200 "decoy"]
           (a/parse "totally-real-room-200[decoy]")))))

(deftest puzzle-test
  (testing "it should solve the puzzle"
    (is (= 361724
           (-> (io/resource "dec4.txt")
               (slurp)
               (a/solve-a))))
    (is (= 482
           (-> (io/resource "dec4.txt")
               (slurp)
               (a/solve-b))))))

(deftest shift-cypher-test
  (testing "rotating chars"
    (is (= \B (a/rotate-char \A)))
    (is (= \b (a/rotate-char \a)))
    (is (= \a (a/rotate-char \z))))
  (testing "it decyphers a room name"
    (is (= "very encrypted name"
           (a/decypher ["qzmt-zixmtkozy-ivhz" 343])))))

