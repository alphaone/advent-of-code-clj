(ns advent.6-test
  (:require [clojure.test :refer :all]
            [advent.6 :as a]))

(deftest empty-grid-test
  (is (= [[0 0] [0 0] [0 0]]
         (a/empty-grid 2 3)))
  (is (= true
         (vector? (a/empty-grid 2 3)))))

(deftest parse-line-test
  (testing "it parse a line into a action and a list of coordinates"
    (is (= [:off [[1, 1] [1, 2] [2, 1] [2, 2]]]
           (a/parse-line "turn off 1,1 through 2,2")))
    (is (= [:on [[0, 9] [0, 10] [0, 11] [1, 9] [1, 10] [1, 11]]]
           (a/parse-line "turn on 0,9 through 1,11")))))

(deftest switch-test
  (testing "it applies an action to given coord of a grid"
    (is (= [[0 0 0]
            [0 1 0]
            [0 0 0]]
           (a/switch-single-light-on-off :on [[0 0 0]
                                              [0 0 0]
                                              [0 0 0]] [1, 1])))
    (is (= [[0 0 0]
            [0 0 0]
            [0 0 0]]
           (a/switch-single-light-on-off :off [[0 0 0]
                                               [0 1 0]
                                               [0 0 0]] [1, 1])))
    (is (= [[0 0 0]
            [0 1 0]
            [0 0 1]]
           (a/switch-single-light-on-off :toggle [[0 0 0]
                                                  [0 1 0]
                                                  [0 0 0]] [2, 2])))
    (is (= [[0 0 0]
            [0 0 0]
            [0 0 0]]
           (a/switch-single-light-on-off :toggle [[0 0 0]
                                                  [0 1 0]
                                                  [0 0 0]] [1, 1])))))

(deftest switch-instruction-test
  (testing "it applies an action to given coord of a grid"
    (is (= [[1 0 0]
            [0 1 0]
            [0 0 1]]
           (a/switch-single-instruction
             a/switch-single-light-on-off
             [[0 0 0]
              [0 0 0]
              [0 0 0]] [:on [[0, 0] [1, 1] [2, 2]]])))))

(deftest count-lights-test
  (testing "it counts the lid lights"
    (is (= 0
           (a/count-lights [[0 0] [0 0]])))
    (is (= 1
           (a/count-lights [[0 1] [0 0]])))
    (is (= 100
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-on-off
               (a/empty-grid 10 10)
               "turn on 0,0 through 9,9"))))
    (is (= 95
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-on-off
               (a/empty-grid 10 10)
               "turn on 0,0 through 9,9
               toggle 0,0 through 1,1
               turn off 9,9 through 9,9"))))

    (is (= 377891
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-on-off
               (a/empty-grid 1000 1000)
               (slurp "resources/6.txt")))))

    (is (= 107
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-brightness
               (a/empty-grid 10 10)
               "turn on 0,0 through 9,9
               toggle 0,0 through 1,1
               turn off 9,9 through 9,9"))))

    (is (= 14110788
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-brightness
               (a/empty-grid 1000 1000)
               (slurp "resources/6.txt")))))))
