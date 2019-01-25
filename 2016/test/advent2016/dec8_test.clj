(ns advent2016.dec8-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [advent2016.dec8 :as a]
            [clojure.string :as cs]))

(deftest rect-test
  (testing "it draws a rect"
    (is (= [["#" "#" "#" "_"]
            ["#" "#" "#" "_"]
            ["_" "_" "_" "_"]]
           (a/draw :rect [["_" "_" "_" "_"]
                          ["_" "_" "_" "_"]
                          ["_" "_" "_" "_"]] 3 2)))))

(deftest rotate-column-test
  (testing "it rotates a column"
    (is (= [[1 0 1 0]
            [1 0 1 0]
            [0 1 0 0]
            [0 1 0 0]]
           (a/draw :rotate-column [[1 1 1 0]
                                   [1 1 1 0]
                                   [0 0 0 0]
                                   [0 0 0 0]] 1 2)))))

(deftest rotate-row-test
  (testing "it rotates a column"
    (is (= [[1 0 0 1 0]
            [1 1 1 0 0]
            [0 1 0 0 0]]
           (a/draw :rotate-row [[1 0 1 0 0]
                                [1 1 1 0 0]
                                [0 1 0 0 0]] 0 3)))))

(deftest column-test
  (testing "it returns col x of display"
    (is (= [1 1 0]
           (a/col [[0 1 0]
                   [0 1 0]
                   [1 0 1]] 1)))))

(deftest rotate-test
  (testing "it rotates a list"
    (is (= [4 1 2 3]
           (a/rotate [1 2 3 4])))))

(deftest assoc-col-test
  (testing "it assoc's a col to a display"
    (is (= [[0 1 0]
            [0 1 0]
            [0 1 0]]
           (a/assoc-col [[0 0 0]
                         [0 0 0]
                         [0 0 0]] 1 [1 1 1])))))

(deftest parse-test
  (testing "it parse the instructions"
    (is (= [:rect [12 13]] (a/parse "rect 12x13")))
    (is (= [:rotate-row [0 5]] (a/parse "rotate row y=0 by 5")))
    (is (= [:rotate-column [13 1]] (a/parse "rotate column x=13 by 1")))))

(def empty-display
  (vec (repeat 6 (vec (repeat 50 " ")))))

(deftest puzzle-test
  (testing "it solves the puzzle"
    (let [instructions (-> (io/resource "dec8.txt")
                           (slurp)
                           (cs/split #"\n"))
          display' (->> instructions
                     (map a/parse)
                     (reduce a/apply-draw empty-display))]
      (is (= 115
             (->> display'
                  (flatten)
                  (filter #(= "#" %))
                  (count))))
      (is (= (str "#### #### #### #   ##  # #### ###  ####  ###   ## \n"
                  "#    #    #    #   ## #  #    #  # #      #     # \n"
                  "###  ###  ###   # # ##   ###  #  # ###    #     # \n"
                  "#    #    #      #  # #  #    ###  #      #     # \n"
                  "#    #    #      #  # #  #    # #  #      #  #  # \n"
                  "#### #    ####   #  #  # #    #  # #     ###  ##  ")
             (cs/join "\n" (map cs/join display')))))))