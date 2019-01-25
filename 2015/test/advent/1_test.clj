(ns advent.1-test
  (:require [clojure.test :refer :all]
            [advent.1 :as a]))

(deftest floor-test
  (is (= 0
         (a/last-floor "()()")))
  (is (= 0
         (a/last-floor "(())")))
  (is (= 3
         (a/last-floor "(((")))
  (is (= 3
         (a/last-floor "(()(()(")))
  (is (= 3
         (a/last-floor "))(((((")))

  (is (= 74
         (a/last-floor (slurp "resources/1.txt")))))

(deftest basement-enter-test
  (is (= 1
         (a/floor-on-entering-basement ")")))
  (is (= 5
         (a/floor-on-entering-basement "()()))")))
  (is (= 1795
         (a/floor-on-entering-basement (slurp "resources/1.txt")))))
