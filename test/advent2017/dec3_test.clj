(ns advent2017.dec3-test
  (:require [clojure.test :refer :all]
            [advent2017.dec3 :as a]))

(deftest edge->shell-no-test
  (testing "it should"
    (is (= 0 (a/edge-length->shell-no 1)))
    (is (= 1 (a/edge-length->shell-no 3)))))

(deftest shell-no-test
  (testing "it should calc shell number for a given int"
    (is (= 0 (a/shell-no 1)))
    (is (= 1 (a/shell-no 2)))
    (is (= 1 (a/shell-no 3)))
    (is (= 1 (a/shell-no 9)))
    (is (= 2 (a/shell-no 10)))))

(deftest dist-to-axis-test
  (testing "it should"
    (is (= 0 (a/dist-to-axis 2)))
    (is (= 1 (a/dist-to-axis 3)))
    (is (= 0 (a/dist-to-axis 4)))

    (is (= 0 (a/dist-to-axis 11)))
    (is (= 1 (a/dist-to-axis 12)))
    (is (= 2 (a/dist-to-axis 13)))
    (is (= 1 (a/dist-to-axis 14)))
    (is (= 0 (a/dist-to-axis 15)))
    (is (= 1 (a/dist-to-axis 16)))
    (is (= 2 (a/dist-to-axis 17)))
    (is (= 1 (a/dist-to-axis 18)))
    
    (is (= 2 (a/dist-to-axis 21)))
    (is (= 1 (a/dist-to-axis 22)))
    (is (= 0 (a/dist-to-axis 23)))
    (is (= 1 (a/dist-to-axis 24)))
    (is (= 2 (a/dist-to-axis 25)))))

(deftest route-length-test
  (testing "it calc the route length"
    (is (= 1 (a/route-length 2)))
    (is (= 3 (a/route-length 20)))
    (is (= 5 (a/route-length 26)))))

(deftest solve-a-test
  (testing "it should"
    (is (= 371
           (a/route-length 368078)))))