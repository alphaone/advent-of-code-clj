(ns advent2020.day7-test
  (:require [clojure.test :refer :all]
            [advent2020.day7 :as day7]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def example-input
  ["light red bags contain 1 bright white bag, 2 muted yellow bags."
   "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
   "bright white bags contain 1 shiny gold bag."
   "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags."
   "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."
   "dark olive bags contain 3 faded blue bags, 4 dotted black bags."
   "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags."
   "faded blue bags contain no other bags."
   "dotted black bags contain no other bags."])

(deftest parse-input-test
  (is (= {"bright white" '("shiny gold")
          "dark olive"   '("faded blue" "dotted black")
          "dark orange"  '("bright white" "muted yellow")
          "dotted black" '()
          "faded blue"   '()
          "light red"    '("bright white" "muted yellow")
          "muted yellow" '("shiny gold" "faded blue")
          "shiny gold"   '("dark olive" "vibrant plum")
          "vibrant plum" '("faded blue" "dotted black")}
         (day7/parse-input example-input))))

(deftest parse-rule-test
  (is (= {"light red" ["bright white" "muted yellow"]}
         (day7/parse-rule "light red bags contain 1 bright white bag, 2 muted yellow bags.")))
  (is (= {"bright white" ["shiny gold"]}
         (day7/parse-rule "bright white bags contain 1 shiny gold bag.")))
  (is (= {"dotted black" []}
         (day7/parse-rule "dotted black bags contain no other bags."))))

(deftest invert-bags-test
  (is (= {"bright white" ["light red" "dark orange"]
          "dark olive"   ["shiny gold"]
          "dotted black" ["vibrant plum" "dark olive"]
          "faded blue"   ["muted yellow" "vibrant plum" "dark olive"]
          "muted yellow" ["light red" "dark orange"]
          "shiny gold"   ["muted yellow" "bright white"]
          "vibrant plum" ["shiny gold"]}
         (day7/invert-bags (day7/parse-input example-input)))))

(deftest can-contain-test
  (is (= #{"bright white" "dark orange" "light red" "muted yellow"}
         (-> (day7/parse-input example-input)
             (day7/invert-bags)
             (day7/can-contain? "shiny gold")))))

(def input (slurp (io/resource "day7.txt")))

(deftest solve-a
  (is (= 242
         (-> (str/split-lines input)
             (day7/parse-input)
             (day7/invert-bags)
             (day7/can-contain? "shiny gold")
             count)))
  )
