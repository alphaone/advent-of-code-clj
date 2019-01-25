(ns advent.9-test
  (:require [clojure.test :refer :all]
            [advent.9 :as a]))

(deftest parse-line-test
  (testing "it "
    (is (= {:from     "London"
            :to       "Dublin"
            :distance 464}
           (a/parse-line "London to Dublin = 464")))))

(deftest parse-test
  (testing "it returns a map of distances"
    (is (= {["Belfast" "Dublin"] 141
            ["Belfast" "London"] 518
            ["Dublin" "Belfast"] 141
            ["Dublin" "London"]  464
            ["London" "Belfast"] 518
            ["London" "Dublin"]  464}
           (a/parse "London to Dublin = 464\nLondon to Belfast = 518\nDublin to Belfast = 141")))))

(deftest locations-test
  (testing "it returns a list of location"
    (is (= #{"Belfast" "Dublin" "London"}
           (a/locations {["Belfast" "Dublin"] 141
                         ["Belfast" "London"] 518
                         ["Dublin" "Belfast"] 141
                         ["Dublin" "London"]  464
                         ["London" "Belfast"] 518
                         ["London" "Dublin"]  464})))))

(deftest shortest-route-test
  (testing "it returns shortes route"
    (is (= {:shortest-route          ["Belfast" "Dublin" "London"]
            :shortest-route-distance 605}
           (a/shortest-route {["Belfast" "Dublin"] 141
                              ["Belfast" "London"] 518
                              ["Dublin" "Belfast"] 141
                              ["Dublin" "London"]  464
                              ["London" "Belfast"] 518
                              ["London" "Dublin"]  464})))))

(deftest route-distance-test
  (testing "it calcs the distance of a route"
    (is (= 605
           (a/route-distance {["Belfast" "Dublin"] 141
                              ["Belfast" "London"] 518
                              ["Dublin" "Belfast"] 141
                              ["Dublin" "London"]  464
                              ["London" "Belfast"] 518
                              ["London" "Dublin"]  464} 
                             ["Belfast" "Dublin" "London"])))))

(deftest solve-puzzle-test
  (testing "it solves the puzzle"
    (is (= {:shortest-route          ["Tristram" "AlphaCentauri" "Norrath" "Straylight" "Faerun" "Snowdin" "Tambi" "Arbre"]
            :shortest-route-distance 141}
           (a/shortest-route (a/parse (slurp "resources/9.txt")))))))

(deftest solve-puzzle2-test
  (testing "it solves the puzzle"
    (is (= {:longest-route          ["AlphaCentauri" "Arbre" "Tristram" "Snowdin" "Straylight" "Tambi" "Norrath" "Faerun"]
            :longest-route-distance 736}
           (a/longest-route (a/parse (slurp "resources/9.txt")))))))
