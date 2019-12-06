(ns advent2019.dec3-test
  (:require [clojure.test :refer :all]
            [advent2019.dec3 :as d]))

(deftest parse-command-test
  (testing "it parses a command"
    (is (= ["R" 20] (d/parse-command "R20")))))

(deftest move-test
  (testing "it move the current pos"
    (is (= [2 0] (d/move [0 0] "R2")))
    (is (= [-2 0] (d/move [0 0] "L2")))
    (is (= [0 3] (d/move [0 0] "U3")))
    (is (= [0 -100] (d/move [0 0] "D100")))))

(deftest path-test
  (testing "it returns all coords on the path"
    (is (= [[1 0] [2 0]] (d/path [0 0] "R2")))
    (is (= [[1 0] [2 0]] (d/path [0 0] "R2")))))

(deftest single-wire-test
  (testing "it lays the wire"
    (is (= {[1 0] 1
            [2 0] 1}
           (d/single-wire {} [0 0] 1 "R2"))))
  (testing "it will ignore crossing of same wire"
    (is (= {[1 0] 1
            [2 0] 1}
           (d/single-wire {[1 0] 1}
                          [0 0] 1 "R2"))))
  (testing "it marks crossings of different wires"
    (is (= {[1 0] 3
            [2 0] 1}
           (d/single-wire {[1 0] 2} [0 0] 1 "R2")))))

(deftest wire-test
  (testing "it lays the wire along the given paths"
    (is (= {[1 0] 1
            [2 0] 1
            [3 0] 1
            [4 0] 1
            [5 0] 1
            [6 0] 1
            [7 0] 1
            [8 0] 1
            [8 1] 1
            [8 2] 1
            [8 3] 1
            [8 4] 1
            [8 5] 1
            [7 5] 1
            [6 5] 1
            [5 5] 1
            [4 5] 1
            [3 5] 1
            [3 4] 1
            [3 3] 1
            [3 2] 1}
           (d/wire {} [0 0] 1 ["R8" "U5" "L5" "D3"])))))

(deftest crossing-test
  (testing "it finds crossings"
    (is (= [3 3]
           (-> {}
               (d/wire [0 0] 1 ["R8" "U5" "L5" "D3"])
               (d/wire [0 0] 2 ["U7" "R6" "D4" "L4"])
               (d/find-nearest-crossings))))
    (is (= [155 4]
           (-> {}
               (d/wire [0 0] 1 ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"])
               (d/wire [0 0] 2 ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"])
               (d/find-nearest-crossings))))
    (is (= [124 11]
           (-> {}
               (d/wire [0 0] 1 ["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"])
               (d/wire [0 0] 2 ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"])
               (d/find-nearest-crossings))))))

(deftest solve-part1-test
  (testing "it solves part 1"
    (is (= [253 63]
           (d/solve-part1)))))
