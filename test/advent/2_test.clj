(ns advent.2-test
  (:require [clojure.test :refer :all]
            [advent.2 :as a]))

(deftest extra-test
  (is (= 6
         (a/extra [2 3 4])))
  (is (= 1
         (a/extra [10 1 1]))))

(deftest paper-test
  (is (= 58
         (a/paper "2x3x4")))
  (is (= 43
         (a/paper "1x1x10")))

  (is (= 101
         (a/papers "2x3x4\n1x1x10")))

  (is (= 1606483
         (a/papers (slurp "resources/2.txt")))))

(deftest bow-test
  (is (= 24
         (a/bow [2 3 4])))
  (is (= 10
         (a/bow [1 1 10]))))

(deftest ribbon-test
  (is (= 34
         (a/ribbon "2x3x4")))
  (is (= 14
         (a/ribbon "1x1x10")))

  (is (= 48
         (a/ribbons "4x3x2\n1x1x10")))

  (is (= 3842356
         (a/ribbons (slurp "resources/2.txt")))))