(ns advent2016.dec1-test
  (:require [clojure.test :refer :all]
            [advent2016.dec1 :as a]
            [clojure.string :as cs]))

(def puzzle-str "R4, R1, L2, R1, L1, L1, R1, L5, R1, R5, L2, R3, L3, L4, R4, R4, R3, L5, L1, R5, R3, L4, R1, R5, L1, R3, L2, R3, R1, L4, L1, R1, L1, L5, R1, L2, R2, L3, L5, R1, R5, L1, R188, L3, R2, R52, R5, L3, R79, L1, R5, R186, R2, R1, L3, L5, L2, R2, R4, R5, R5, L5, L4, R5, R3, L4, R4, L4, L4, R5, L4, L3, L1, L4, R1, R2, L5, R3, L4, R3, L3, L5, R1, R1, L3, R2, R1, R2, R2, L4, R5, R1, R3, R2, L2, L2, L1, R2, L1, L3, R5, R1, R4, R5, R2, R2, R4, R4, R1, L3, R4, L2, R2, R1, R3, L5, R5, R2, R5, L1, R2, R4, L1, R5, L3, L3, R1, L4, R2, L2, R1, L1, R4, R3, L2, L3, R3, L2, R1, L4, R5, L1, R5, L2, L1, L5, L2, L5, L2, L4, L2, R3")

(deftest a-test
  (testing "Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away"
    (is (= 5
           (a/follow ["R2", "L3"]))))
  (testing "R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away"
    (is (= 2
           (a/follow ["R2" "R2" "R2"]))))
  (testing "R5, L5, R5, R3 leaves you 12 blocks away"
    (is (= 12
           (a/follow ["R5" "L5" "R5" "R3"]))))
  (testing "puzzle"
    (is (= 161
           (-> (cs/split puzzle-str #", ")
               (a/follow))))))

(deftest b-test
  (testing "instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away"
    (is (= 4
           (a/follow-b ["R8" "R4" "R4" "R8"]))))
  (testing "puzzle"
    (is (= 110
           (-> (cs/split puzzle-str #", ")
               (a/follow-b))))))

(deftest turn-test
  (testing "turn"
    (is (= "W" (a/turn "N" "L")))
    (is (= "W" (a/turn "S" "R")))
    (is (= "W" (a/turn "W" "S")))))

(deftest moving-test
  (testing "move-coords"
    (is (= [0 1]
           (a/move-coords  "N" 1 [0 0])))
    (is (= [-2 0]
           (a/move-coords  "W" 2 [0 0]))))
  (testing "move"
    (is (= [[2 0] "E"]
           (a/move [[0 0] "N"] "R2")))
    (is (= [[-7 5] "W"]
           (a/move [[-2 5] "S"] "R5")))))

(deftest parsing-test
  (testing "split instruction"
    (is (= ["R" 2]
           (a/split-instr "R2")))))

(deftest expand-test
  (testing "expand"
    (is (= ["R1" "S1" "S1"]
           (a/expand "R3")))))
