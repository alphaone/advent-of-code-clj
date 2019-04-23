(ns advent2018.dec12-test
  (:require [clojure.test :refer :all]
            [advent2018.dec12 :as a]))

(deftest apply-rules-test
  (is (= \. (a/apply-rules "#####" {"#####" \.
                                    "##.##" \#})))
  (is (= \. (a/apply-rules "....." {"#####" \.
                                    "##.##" \#}))))

(deftest pots-on-pos-test
  (is (= "#####" (a/pots-on-pos (a/string->pots ".#####.") 3)))
  (is (= "..###" (a/pots-on-pos (a/string->pots ".#####.") 1))))

(deftest min-pot-test
  (is (= -2 (a/min-pot (a/string->pots "###"))))
  (is (= -1 (a/min-pot (a/string->pots ".#.")))))

(deftest max-pot-test
  (is (= 4 (a/max-pot (a/string->pots "###"))))
  (is (= 3 (a/max-pot (a/string->pots ".#.")))))

(deftest next-gen-test
  (is (= [-2
          "..##."]
         (-> (a/next-gen {".#..." \#}
                         (a/string->pots "#"))
             (a/pots->string))))
  (is (= [1 "...#."]
         (-> (a/next-gen {".#..." \#
                          "..#.." \.}
                         (a/string->pots "...#"))
             (a/pots->string)))))

(deftest evolve-test
  (is (= [-2 "..###########."]
         (-> (a/evolve {".#..." \#
                        "##..." \#}
                       {0 \#})
             (drop 10)
             (first)
             (a/pots->string)))))

(deftest sum-up-plant-pots-test
  (is (= 325
         (a/sum-up-plant-pots (a/string->pots -3 ".#....##....#####...#######....#.#..##.")))))

(def rules {"#...." \.,
            "#..##" \#,
            "....#" \.,
            "...#." \.,
            "...##" \#,
            "#.#.#" \.,
            ".#..." \#,
            "##.#." \.,
            "..#.#" \.,
            ".##.#" \#,
            "###.#" \#,
            ".#.##" \.,
            "....." \.,
            "#####" \#,
            "###.." \.,
            "##..#" \#,
            "#.###" \#,
            "#.#.." \.,
            "..###" \.,
            "..#.." \.,
            ".#..#" \#,
            ".##.." \#,
            "##..." \#,
            ".#.#." \#,
            ".###." \#,
            "#..#." \.,
            "####." \.,
            ".####" \#,
            "#.##." \#,
            "##.##" \.,
            "..##." \.,
            "#...#" \#})
(def input-a "##...#......##......#.####.##.#..#..####.#.######.##..#.####...##....#.#.####.####.#..#.######.##...")

(deftest solve-a-test
  (is (= 3421
         (->> (a/evolve rules
                        (a/string->pots input-a))
              (drop 20)
              (first)
              (a/sum-up-plant-pots)))))

(deftest sum-up-plant-pots-b-test
  (is (= 6244 (a/sum-up-plant-pots-b 99)))
  (is (= 6754 (a/sum-up-plant-pots-b 109)))
  (is (= 2550000001195 (a/sum-up-plant-pots-b 50000000000))))
