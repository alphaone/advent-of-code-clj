(ns advent2018.dec2-test
  (:require [clojure.test :refer :all]
            [advent2018.dec2 :as a]))

(deftest contains-multiple?-test
  (is (not (a/contains-multiple? 2 "abcdef")))
  (is (a/contains-multiple? 2 "bababc"))
  (is (a/contains-multiple? 2 "abbcde"))
  (is (not (a/contains-multiple? 2 "abcccd")))
  (is (a/contains-multiple? 2 "aabcdd"))
  (is (a/contains-multiple? 2 "abcdee"))
  (is (not (a/contains-multiple? 2 "ababab")))

  (is (not (a/contains-multiple? 3 "abcdef")))
  (is (a/contains-multiple? 3 "bababc"))
  (is (not (a/contains-multiple? 3 "abbcde")))
  (is (a/contains-multiple? 3 "abcccd"))
  (is (not (a/contains-multiple? 3 "aabcdd")))
  (is (not (a/contains-multiple? 3 "abcdee")))
  (is (a/contains-multiple? 3 "ababab")))

(deftest checksum-test
  (is (= 12
         (a/checksum ["abcdef" "bababc" "abbcde" "abcccd" 
                      "aabcdd" "abcdee" "ababab"]))))

(deftest solve-a-test
  (is (= 4712
         (a/solve-a))))

(deftest differs-by-exactly-one-char?-test
  (is (not (a/differs-by-exactly-one-char? "abcde" "axcye")))
  (is (a/differs-by-exactly-one-char? "fghij" "fguij")))

(deftest common-letters-test
  (is (= "fgij"
         (a/common-letters "fghij" "fguij"))))

(deftest solve-b-test
  (is (= "lufjygedpvfbhftxiwnaorzmq"
         (a/solve-b))))