(ns advent2018.dec5-test
  (:require [clojure.test :refer :all]
            [advent2018.dec5 :as a]))

(deftest react?-test
  (is (a/react? \a \A))
  (is (a/react? \A \a))
  (is (not (a/react? \a \a)))
  (is (not (a/react? \A \A)))
  (is (not (a/react? \a \b)))
  (is (not (a/react? \a \B))))

(deftest react-polymer*-test
  (is (= "" (a/react-polymer* "aA")))
  (is (= "abAB" (a/react-polymer* "abAB")))
  (is (= "aabAAB" (a/react-polymer* "aabAAB")))
  (is (= "aA" (a/react-polymer* "abBA"))))

(deftest react-polymer-test
  (is (= "" (a/react-polymer "aA")))
  (is (= "abAB" (a/react-polymer "abAB")))
  (is (= "aabAAB" (a/react-polymer "aabAAB")))
  (is (= "" (a/react-polymer "abBA"))))

(deftest solva-a-test
  (is (= 10886
         (time (a/solve-a)))))

(deftest find-problematic-unit-test
  (is (= [\c 4]
         (a/find-problematic-unit "dabAcCaCBAcCcaDA"))))

(deftest solve-b-test
  (is (= [\v 4684]
         (time (a/solve-b)))))
