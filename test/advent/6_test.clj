(ns advent.6-test
  (:require [clojure.test :refer :all]
            [advent.6 :as a]))

(deftest empty-grid-test
  (is (= [[false false] [false false] [false false]]
         (a/empty-grid 2 3)))
  (is (= true
         (vector? (a/empty-grid 2 3)))))

(deftest parse-line-test
  (testing "it parse a line into a action and a list of coordinates"
    (is (= [:off [[1,1] [1,2] [2,1] [2,2]]]
         (a/parse-line "turn off 1,1 through 2,2")))
    (is (= [:on [[0,9] [0,10] [0,11] [1,9] [1,10] [1,11]]]
         (a/parse-line "turn on 0,9 through 1,11")))))

(deftest switch-test
  (testing "it applies an action to given coord of a grid"
    (is (= [[false false false]
            [false true false]
            [false false false]]
           (a/switch-single-light :on [[false false false]
                                       [false false false]
                                       [false false false]] [1,1])))
    (is (= [[false false false]
            [false false false]
            [false false false]]
           (a/switch-single-light :off [[false false false]
                                        [false true false]
                                        [false false false]] [1,1])))
    (is (= [[false false false]
            [false true false]
            [false false true]]
           (a/switch-single-light :toggle [[false false false]
                                           [false true false]
                                           [false false false]] [2,2])))
    (is (= [[false false false]
            [false false false]
            [false false false]]
           (a/switch-single-light :toggle [[false false false]
                                           [false true false]
                                           [false false false]] [1,1])))))

(deftest switch-instruction-test
  (testing "it applies an action to given coord of a grid"
    (is (= [[true false false]
            [false true false]
            [false false true]]
           (a/switch-single-instruction [[false false false]
                                         [false false false]
                                         [false false false]] [:on [[0,0] [1,1] [2,2]]])))))

(deftest count-lights-test
  (testing "it counts the lid lights"
    (is (= 0
           (a/count-lights [[false false] [false false]])))
    (is (= 1
           (a/count-lights [[false true] [false false]])))
    (is (= 100
           (a/count-lights
             (a/lighten-up-grid
               (a/empty-grid 10 10)
               "turn on 0,0 through 9,9"))))
    (is (= 95
           (a/count-lights
             (a/lighten-up-grid
               (a/empty-grid 10 10)
               "turn on 0,0 through 9,9
               toggle 0,0 through 1,1
               turn off 9,9 through 9,9"))))

    (is (= 377891
           (a/count-lights
             (a/lighten-up-grid
               (a/empty-grid 1000 1000)
               (slurp "resources/6.txt")))))

    (is (= 107
           (a/count-lights2
             (a/lighten-up-grid2
               (a/empty-grid2 10 10)
               "turn on 0,0 through 9,9
               toggle 0,0 through 1,1
               turn off 9,9 through 9,9"))))

    (is (= 14110788
           (a/count-lights2
            (a/lighten-up-grid2
              (a/empty-grid2 1000 1000)
              (slurp "resources/6.txt")))))))
