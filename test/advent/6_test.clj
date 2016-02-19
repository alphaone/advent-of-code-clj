(ns advent.6-test
  (:require [clojure.test :refer :all]
            [advent.6 :as a]))

(deftest parse-line-test
  (testing "it parse a line into a action and a list of coordinates"
    (is (= [:off [[1 1] [1 2] [2 1] [2 2]]]
           (a/parse-line "turn off 1,1 through 2,2")))
    (is (= [:on [[0 9] [0 10] [0 11] [1 9] [1 10] [1 11]]]
           (a/parse-line "turn on 0,9 through 1,11")))))

(deftest switch-on-off-test
  (testing "it applies an action to given coord of a grid"
    (is (= {[1 1] 1}
           (a/switch-single-light-on-off :on {} [1 1])))
    (is (= {[1 1] 0}
           (a/switch-single-light-on-off :off {[1 1] 1} [1 1])))
    (is (= {[1 1] 1
            [2 2] 1}
           (a/switch-single-light-on-off :toggle {[1 1] 1} [2 2])))
    (is (= {[1 1] 0}
           (a/switch-single-light-on-off :toggle {[1 1] 1} [1 1])))))

(deftest switch-brightness-test
  (testing "it applies an action to given coord of a grid"
    (is (= {[1 1] 1}
           (a/switch-single-light-brightness :on {} [1 1])))
    (is (= {[1 1] 0}
           (a/switch-single-light-brightness :off {[1 1] 1} [1 1])))
    (is (= {[1 1] 1
            [2 2] 2}
           (a/switch-single-light-brightness :toggle {[1 1] 1} [2 2])))
    (is (= {[1 1] 3}
           (a/switch-single-light-brightness :toggle {[1 1] 1} [1 1])))))

(deftest switch-instruction-test
  (testing "it applies an action to given coord of a grid"
    (is (= {[0 0] 1 
            [1 1] 1
            [2 2] 1}
           (a/switch-single-instruction
             a/switch-single-light-on-off
             {} [:on [[0 0] [1 1] [2 2]]])))))

(deftest count-lights-test
  (testing "it counts the lid lights"
    (is (= 0
           (a/count-lights {})))
    (is (= 1
           (a/count-lights {[5 5] 1})))
    (is (= 100
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-on-off
               "turn on 0,0 through 9,9"))))
    (is (= 95
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-on-off
               "turn on 0,0 through 9,9
               toggle 0,0 through 1,1
               turn off 9,9 through 9,9"))))

    (is (= 377891
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-on-off
               (slurp "resources/6.txt")))))

    (is (= 107
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-brightness
               "turn on 0,0 through 9,9
               toggle 0,0 through 1,1
               turn off 9,9 through 9,9"))))

    (is (= 14110788
           (a/count-lights
             (a/lighten-up-grid
               a/switch-single-light-brightness
               (slurp "resources/6.txt")))))))
