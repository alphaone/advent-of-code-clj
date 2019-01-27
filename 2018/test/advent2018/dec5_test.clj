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

(deftest react-neighbors-test
  (is (= [\a] (a/react-neighbors [] \a)))
  (is (= [] (a/react-neighbors [\a] \A)))
  (is (= [\a \b] (a/react-neighbors [\a] \b)))
  (is (= [\a \b \c] (a/react-neighbors [\a \b] \c)))
  (is (= [\a \b] (a/react-neighbors [\a \b \c] \C))))

(deftest react-polymer-test
  (is (= [] (a/react-polymer "aA")))
  (is (= [\a \b \A \B] (a/react-polymer "abAB")))
  (is (= [\a \a \b \A \A \B] (a/react-polymer "aabAAB")))
  (is (= [] (a/react-polymer "abBA"))))

(deftest solva-a-test
  (is (= 10886
         (a/solve-a))))

(deftest find-problematic-unit-test
  (is (= [\c 4]
         (a/find-problematic-unit "dabAcCaCBAcCcaDA"))))

(deftest solve-b-test
  (is (= [\v 4684]
         (a/solve-b))))
