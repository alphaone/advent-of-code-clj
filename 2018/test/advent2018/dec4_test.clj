(ns advent2018.dec4-test
  (:require [clojure.test :refer :all]
            [advent2018.dec4 :as a]))

(deftest parse-guard-test
  (is (= 3109
         (a/parse-guard "[1518-02-05 00:00] Guard #3109 begins shift"))))

(deftest parse-incident-test
  (is (= 7 (a/parse-incident "[1518-02-06 00:07] falls asleep")))
  (is (= 43 (a/parse-incident "[1518-02-06 00:43] wakes up"))))

(deftest sum-up-sleeptime-test
  (is (= [123 37]
         (a/sum-up-sleeptime [123 [{:incidents [[17 28] [35 48]]}
                                   {:incidents [[42 55]]}]]))))

(deftest calendar-test
  (is (= [1 1 1 1 1 1 1 1 1 1
          1 1 1 1 1 1 1 1 1 1
          1 1 1 1 1 1 1 1 1 1
          1 1 1 1 1 1 1 1 1 1
          1 1 1 1 1 1 1 1 1 1
          1 1 1 1 1 1 1 1 1 0]
         (a/calendar [0 59])))
  (is (= [0 0 0 0 0 0 0 0 0 0
          1 1 1 1 1 1 1 1 1 1
          0 0 0 0 0 0 0 0 0 0
          0 0 0 0 0 0 0 0 0 0
          0 0 0 0 0 0 0 0 0 0
          0 0 0 0 0 0 0 0 0 0]
         (a/calendar [10 20]))))

(deftest sleepiest-minute-test
  (is (= [27 2]
         (a/sleepiest-minute [{:incidents [[17 28] [35 48]]}
                              {:incidents [[27 30]]}]))))

(deftest solve-a-test
  (is (= 101262
         (a/solve-a))))

(deftest solve-b-test
  (is (= 71976
         (a/solve-b))))


