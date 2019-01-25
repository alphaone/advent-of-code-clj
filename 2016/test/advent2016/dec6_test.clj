(ns advent2016.dec6-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [advent2016.dec6 :as a]
            [clojure.string :as cs]))

(deftest transpose-test
  (testing "it gets all the first, second, third, etc letters"
    (is (= ["ede" "era" "dvn"]
           (a/transpose ["eed" "drv" "ean"])))))

(deftest most-freq-test
  (testing "it finds the most frequent char of a string"
    (is (= \a
           (a/most-freq-char :most-frequent "aab")))))

(def example ["eedadn" "drvtee" "eandsr" "raavrd" "atevrs" "tsrnev" "sdttsa" "rasrtv" "nssdts" "ntnada" "svetve" "tesnvt" "vntsnd" "vrdear" "dvrsen" "enarar"])

(deftest solve-puzzle-test
  (testing "it solves the puzzle"
    (is (= "abc" (a/solve :most-frequent ["xzy" "abc" "abc"])))
    (is (= "easter" (a/solve :most-frequent example)))
    (is (= "advent" (a/solve :least-frequent example)))
    (let [input (-> (io/resource "dec6.txt") (slurp) (cs/split #"\n"))]
      (is (= "wkbvmikb"
             (a/solve :most-frequent input)))
      (is (= "evakwaga"
             (a/solve :least-frequent input))))))
