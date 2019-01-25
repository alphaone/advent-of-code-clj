(ns advent.11-test
  (:require [clojure.test :refer :all]
            [advent.11 :as a]))

(deftest inc-letter-test
  (testing "it inc's a letter"
    (is (= [\b false]
           (a/inc-letter \a)))
    (is (= [\c false]
           (a/inc-letter \b)))
    (is (= [\a true]
           (a/inc-letter \z)))))

(deftest next-password-test
  (testing "it gives the next password for a given password"
    (is (= "aaaaaaab"
           (a/next-password "aaaaaaaa")))
    (is (= "aaaaaaba"
           (a/next-password "aaaaaaaz")))
    (is (= "abcdefha"
           (a/next-password "abcdefgz")))
    (is (= "aaaaaaba"
           (last (take 27 (iterate a/next-password "aaaaaaaa")))))))

(deftest increasing-straight-test
  (testing "it includes one increasing straight"
    (is (= true
           (a/include-increasing-straight? "abc")))
    (is (= false
           (a/include-increasing-straight? "abd")))
    (is (= false
           (a/include-increasing-straight? "abbceffg")))))

(deftest forbidden-letters-test
  (testing "it does not include forbidden letters"
    (is (= false
           (a/no-forbidden-letters? "i")))
    (is (= false
           (a/no-forbidden-letters? "o")))
    (is (= false
           (a/no-forbidden-letters? "l")))
    (is (= true
           (a/no-forbidden-letters? "abc")))
    (is (= false
           (a/no-forbidden-letters? "hijklmmn")))))

(deftest two-different-non-overlapping-pairs-test
  (testing "it checks for pairs"
    (is (= true
           (a/two-non-overlapping-pairs "aabb")))
    (is (= false
           (a/two-non-overlapping-pairs "aaa")))
    (is (= true
           (a/two-non-overlapping-pairs "aabdcc")))
    (is (= false
           (a/two-non-overlapping-pairs "hijklmmn")))
    (is (= true
           (a/two-non-overlapping-pairs "abbceffg")))
    (is (= true
           (a/two-non-overlapping-pairs "abcdffaa")))))

(deftest valid-test
  (testing "it combines all checks"
    (is (= false
           (a/valid? "hijklmmn")))
    (is (= false
           (a/valid? "abbceffg")))
    (is (= false
           (a/valid? "abbcegjk")))
    (is (= true
           (a/valid? "abcdffaa")))
    (is (= true
           (a/valid? "ghjaabcc")))))

(deftest find-valid-password-test
  (testing "it checks against all the rules"
    (is (= "abcdffaa"
           (a/next-valid-password "abcdefgh")))
    (is (= "ghjaabcc"
           (a/next-valid-password "ghijklmn")))))

(deftest puzzle-test
  (testing "it solves the puzzle"
    (is (= "hxbxxyzz"
           (a/next-valid-password "hxbxwxba")))
    (is (= "hxcaabcc"
           (a/next-valid-password "hxbxxyzz")))))
