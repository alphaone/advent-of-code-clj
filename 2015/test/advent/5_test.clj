(ns advent.5-test
  (:require [clojure.test :refer :all]
            [advent.5 :as a]))

(deftest vowel-test
  (is (a/vowel? \a))
  (is (not (a/vowel? \b))))

(deftest at-least-three-vowels-test
  (is (a/three-vowels? "aei"))
  (is (a/three-vowels? "xazegov"))
  (is (a/three-vowels? "aeiouaeiouaeiou")))

(deftest double-letter-test
  (is (a/double-letter "xx"))
  (is (a/double-letter "abcdde"))
  (is (a/double-letter "aabbccdd"))
  (is (not (a/double-letter "abcdef"))))

(deftest naughty-things-test
  (is (a/naughty-things "abc"))
  (is (not (a/naughty-things "ghij"))))

(deftest is-nice-test
  (is (a/nice? "ugknbfddgicrmopn"))
  (is (a/nice? "aaa"))
  (is (not (a/nice? "jchzalrnumimnmhp")))
  (is (not (a/nice? "haegwjzuvuyypxyu")))
  (is (not (a/nice? "dvszwmarrgswjxmb"))))

(deftest count-nice-words-test
  (is (= 1
         (a/count-nice-words "ugknbfddgicrmopn\njchzalrnumimnmhp")))
  
  (is (= 238
         (a/count-nice-words (slurp "resources/5.txt")))))

(deftest twice-letters-test
  (testing "contains a pair of any two letters that appears at least twice in the string without overlapping"
    (is (= true
           (a/any-two-letter-twice "xyxy")))
    (is (= true
           (a/any-two-letter-twice "aabcdefgaa")))
    (is (= false
           (a/any-two-letter-twice "aaa")))))

(deftest repeats-with-exactly-one-letter-between-test
  (testing "It contains at least one letter which repeats with exactly one letter between them"
    (is (= true
           (a/repeats-with-exactly-one-letter-between "xyx")))
    (is (= true
           (a/repeats-with-exactly-one-letter-between "abcdefeghi")))
    (is (= true
           (a/repeats-with-exactly-one-letter-between "aaa")))
    (is (= false
           (a/repeats-with-exactly-one-letter-between "abc")))))

(deftest really-nice-test
  (testing "it uses the new rules to determine nice words"
    (is (= true
           (a/really-nice? "qjhvhtzxzqqjkmpb")))
    (is (= true
           (a/really-nice? "xxyxx")))
    (is (= false
           (a/really-nice? "uurcxstgmygtbstg")))
    (is (= false
           (a/really-nice? "ieodomkazucvgmuy")))))

(deftest count-really-nice-words-test
  (is (= 2
         (a/count-really-nice-words "qjhvhtzxzqqjkmpb\nxxyxx\nuurcxstgmygtbstg")))

  (is (= 69
         (a/count-really-nice-words (slurp "resources/5.txt")))))