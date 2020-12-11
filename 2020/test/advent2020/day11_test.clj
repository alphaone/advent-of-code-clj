(ns advent2020.day11-test
  (:require [clojure.test :refer :all]
            [advent2020.day11 :as day11]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def input (->> (io/resource "day11.txt")
                (slurp)
                (str/split-lines)))

(def example-input ["L.LL.LL.LL"
                    "LLLLLLL.LL"
                    "L.L.L..L.."
                    "LLLL.LL.LL"
                    "L.LL.LL.LL"
                    "L.LLLLL.LL"
                    "..L.L....."
                    "LLLLLLLLLL"
                    "L.LLLLLL.L"
                    "L.LLLLL.LL"])

(defn parse [input]
  (mapv #(vec (char-array %)) input))

(deftest adjacent-seats-test
  (is (= {\L 2 \. 1}
         (day11/adjacent-seats [0 0] example-input)))
  (is (= {\L 6 \. 2}
         (day11/adjacent-seats [1 1] example-input))))

(deftest apply-rule-test
  (is (= \# (day11/apply-rule ["L.L" ".L." "L.L"] [1 1])))
  (is (= \# (day11/apply-rule ["L.L" ".#." "L.L"] [1 1])))
  (is (= \. (day11/apply-rule ["L.L" "..." "L.L"] [1 1])))
  (is (= \# (day11/apply-rule ["#.L" ".#." "#.#"] [1 1])))
  (is (= \L (day11/apply-rule ["#.#" ".#." "#.#"] [1 1]))))

(deftest parse-input-test
  (is (= [[\L \. \L]
          [\. \L \.]
          [\L \. \L]]
         (parse ["L.L" ".L." "L.L"]))))

(deftest advance-test
  (is (= ["#.##.##.##"
          "#######.##"
          "#.#.#..#.."
          "####.##.##"
          "#.##.##.##"
          "#.#####.##"
          "..#.#....."
          "##########"
          "#.######.#"
          "#.#####.##"]
         (->> example-input
              (parse)
              (day11/advance day11/apply-rule)
              (map str/join))))
  (is (= ["#.#L.L#.##"
          "#LLL#LL.L#"
          "L.#.L..#.."
          "#L##.##.L#"
          "#.#L.LL.LL"
          "#.#L#L#.##"
          "..L.L....."
          "#L#L##L#L#"
          "#.LLLLLL.L"
          "#.#L#L#.##"]
         (->> example-input
              (parse)
              (iterate #(day11/advance day11/apply-rule %))
              (drop 6)
              (first)
              (map str/join)))))

(deftest solve-a
  (is (= {\# 37 \. 29 \L 34}
         (day11/find-stable day11/apply-rule (parse example-input))))
  (is (= {\# 2283 \. 1635 \L 4992}
         (day11/find-stable day11/apply-rule (parse input)))))

(deftest adjacent2-test
  (is (= {\# 8}
         (day11/adjacent-seats2 [3 4] (parse [".......#."
                                              "...#....."
                                              ".#......."
                                              "........."
                                              "..#L....#"
                                              "....#...."
                                              "........."
                                              "#........"
                                              "...#....."])))))

(deftest solve-b
  (is (= {\# 26 \. 29 \L 45}
         (day11/find-stable day11/apply-rule2 (parse example-input))))
  (is (= {\# 2054 \. 1635 \L 5221}
         (day11/find-stable day11/apply-rule2 (parse input)))))
