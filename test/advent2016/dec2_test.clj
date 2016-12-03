(ns advent2016.dec2-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [advent2016.dec2 :as a]))

(deftest a-test
  (testing "it finds the code"
    (is (= "1985"
           (a/parse-instruction a/keypad-a "ULL\nRRDDD\nLURDL\nUUUUD"))))
  (testing "it follows the direction in one line"
    (is (= "1"
           (first (a/follow a/keypad-a ["5" []] "ULL"))))
    (is (= "9"
           (first (a/follow a/keypad-a ["1" []] "RRDDD"))))
    (is (= "8"
           (first (a/follow a/keypad-a ["9" []] "LURDL"))))
    (is (= "5"
           (first (a/follow a/keypad-a ["8" []] "UUUUD")))))
  (testing "puzzle"
    (is (= "65556"
           (->> (io/resource "dec2.txt")
                (slurp)
                (a/parse-instruction a/keypad-a))))))

(deftest moving-test
  (testing "it moves to the correct pos on the keypad"
    (is (= "2" (a/move a/keypad-a "5" "U")))
    (is (= "2" (a/move a/keypad-a "2" "U")))
    (is (= "8" (a/move a/keypad-a "5" "D")))
    (is (= "8" (a/move a/keypad-a "8" "D")))
    (is (= "4" (a/move a/keypad-a "5" "L")))
    (is (= "4" (a/move a/keypad-a "4" "L")))
    (is (= "6" (a/move a/keypad-a "5" "R")))
    (is (= "6" (a/move a/keypad-a "6" "R"))))
  (testing "get coords from number"
    (is (= [1 1] (a/coords a/keypad-a "5")))
    (is (= [1 2] (a/coords a/keypad-a "8"))))
  (testing "calcs new coords for dir"
    (is (= [1 0] (a/dir->coords [1 1] "U")))
    (is (= [0 -1] (a/dir->coords [0 0] "U"))))
  (testing "delta"
    (is (= [0 -1] (a/delta "U")))
    ))