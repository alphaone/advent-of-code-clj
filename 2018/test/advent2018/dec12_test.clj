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
         (-> (a/evolve 10
                       {".#..." \#
                        "##..." \#}
                       {0 \#})
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
         (-> (a/evolve 20
                       rules
                       (a/string->pots input-a))
             (a/sum-up-plant-pots)))))
