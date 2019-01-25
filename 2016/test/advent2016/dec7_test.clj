(ns advent2016.dec7-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [advent2016.dec7 :as a]
            [clojure.string :as cs]))

(deftest supports-tls-test
  (testing "it knows if a IP supports TLS"
    (is (a/supports-tls? "abba[mnop]qrst"))
    (is (not (a/supports-tls? "abcd[bddb]xyyx")))
    (is (not (a/supports-tls? "aaaa[qwer]tyui")))
    (is (a/supports-tls? "ioxxoj[asdfgh]zxcvbn"))))

(deftest supports-ssl-test
  (testing "it knows if a IP supports SSL"
    (is (a/supports-ssl? "aba[bab]xyz"))
    (is (not (a/supports-ssl? "xyx[xyx]xyx")))
    (is (a/supports-ssl? "aaa[kek]eke"))
    (is (a/supports-ssl? "zazbz[bzb]cdb"))))

(deftest abba-test
  (testing "it finds ABBA sequences"
    (is (a/abba? "abba"))
    (is (a/abba? "abxyyxfg"))
    (is (not (a/abba? "abcba")))
    (is (not (a/abba? "aaaa")))))

(deftest aba-test
  (testing "it finds all ABA sequences"
    (is (= [["a" "b"] ["a" "c"]]
           (a/aba "abaca")))))

(deftest bab-test
  (testing "it finds BAB sequences with given ABA"
    (is (a/bab? [["a" "b"]] "xbaby"))
    (is (a/bab? [["x" "y"] ["a" "b"]] "xbaby"))
    (is (not (a/bab? [["x" "y"] ["a" "b"]] "xbby")))
    (is (not (a/bab? [] "xbby")))))

(deftest parse-test
  (testing "it parses an IP"
    (is (= [["part1" "part2"] ["hypernet"]]
           (a/parse "part1[hypernet]part2")))
    (is (= [["luutcuiwkuvjayjmhvt" "pksxicdhklgeispteho" "jywabkhdqggvpryxzfv"]
            ["vwabmnqpyzuoxgfan" "cjhxxwfqxxrpwzoozxp"]]
           (a/parse "luutcuiwkuvjayjmhvt[vwabmnqpyzuoxgfan]pksxicdhklgeispteho[cjhxxwfqxxrpwzoozxp]jywabkhdqggvpryxzfv")))))

(deftest puzzle-test
  (testing "it solves the puzzle"
    (let [ips (-> (io/resource "dec7.txt")
                  (slurp)
                  (cs/split #"\n"))]
      (is (= 115 (->> ips
                      (filter a/supports-tls?)
                      (count))))
      (is (= 231 (->> ips
                      (filter a/supports-ssl?)
                      (count)))))))
