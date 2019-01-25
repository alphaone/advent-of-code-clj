(ns advent.3-test
  (:require [clojure.test :refer :all]
            [advent.3 :as a]))

(deftest move-test
  (is (= [0 1]
         (a/move [0 0] \^)))
  (is (= [0 -1]
         (a/move [0 0] \v)))
  (is (= [1 0]
         (a/move [0 0] \>)))
  (is (= [-1 0]
         (a/move [0 0] \<))))

(deftest santa-test
  (is (= 2
         (a/single-santa ">")))
  (is (= 4
         (a/single-santa "^>v<")))
  (is (= 2
         (a/single-santa "^v^v^v^v^v")))
  (is (= 2592
         (a/single-santa (slurp "resources/3.txt")))))


(deftest double-santa-test
  (is (= 3
         (a/double-santa "^v")))
  (is (= 3
         (a/double-santa "^>v<")))
  (is (= 11
         (a/double-santa "^v^v^v^v^v")))

  (is (= 2360
         (a/double-santa (slurp "resources/3.txt")))))

