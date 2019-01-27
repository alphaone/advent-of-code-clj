(ns advent2018.dec7-test
  (:require [clojure.test :refer :all]
            [advent2018.dec7 :as a]
            [clojure.java.io :as io]
            [clojure.string :as cs]))

(def example {:A #{:C}
              :B #{:A}
              :C #{}
              :D #{:A}
              :E #{:B :D :F}
              :F #{:C}})

(deftest parse-line-test
  (is (= ["Y" "T"]
         (a/parse-line "Step Y must be finished before step T can begin."))))

(def graph (->> "dec7.txt"
                (io/resource)
                (slurp)
                (cs/split-lines)
                (map a/parse-line)
                (a/build-graph)))

(deftest build-graph-test
  (is (= {:A #{:C}
          :B #{:A}
          :C #{}
          :D #{:A}
          :E #{:B :D :F}
          :F #{:C}}
         (a/build-graph [[:C :A]
                         [:C :F]
                         [:A :B]
                         [:A :D]
                         [:B :E]
                         [:D :E]
                         [:F :E]]))))

(deftest next-step-test
  (is (= :C (a/next-step example)))
  (is (= :A (a/next-step {:A #{}
                          :B #{:A}
                          :D #{:A}
                          :E #{:B :D :F}
                          :F #{}})))
  (is (= :B (a/next-step {:B #{}
                          :D #{}
                          :E #{:B :D :F}
                          :F #{}})))
  (is (= :D (a/next-step {:D #{}
                          :E #{:D :F}
                          :F #{}})))
  (is (= :F (a/next-step {:E #{:F}
                          :F #{}})))
  (is (= :E (a/next-step {:E #{}}))))

(deftest single-step-test
  (is (= [:C
          {:A #{}
           :B #{:A}
           :D #{:A}
           :E #{:B :D :F}
           :F #{}}]
         (a/single-step example)))
  (is (= [:A {:B #{}
              :D #{}
              :E #{:B :D :F}
              :F #{}}]
         (a/single-step {:A #{}
                         :B #{:A}
                         :D #{:A}
                         :E #{:B :D :F}
                         :F #{}})))
  (is (= [:B
          {:D #{}
           :E #{:D :F}
           :F #{}}]
         (a/single-step {:B #{}
                         :D #{}
                         :E #{:B :D :F}
                         :F #{}})))
  (is (= [:F {:E #{}}]
         (a/single-step {:E #{:F}
                         :F #{}})))
  (is (= [:E {}]
         (a/single-step {:E #{}}))))

(deftest solve-a
  (is (= ":C:A:B:D:F:E"
         (a/solve-a example)))
  (is (= "BKCJMSDVGHQRXFYZOAULPIEWTN"
         (a/solve-a graph))))

(deftest work-test
  (is (= {:done    []
          :workers [["A" 18]]}
         (a/work {:workers [["A" 19]]})))
  (is (= {:done    ["A"]
          :workers []}
         (a/work {:workers [["A" 1]]}))))

(deftest assign-work-test
  (is (= {:graph   {:B #{:A}}
          :workers [[] [] [] [] [:A 61]]}
         (a/assign-work {:A #{}
                         :B #{:A}} [[] [] [] []])))
  (is (= {:graph   {:A #{}
                    :B #{:A}}
          :workers [[] [] [] [] []]}
         (a/assign-work {:A #{}
                         :B #{:A}} [[] [] [] [] []])))
  (is (= {:graph   {:A #{:C}
                    :B #{:A}}
          :workers []}
         (a/assign-work {:A #{:C}
                         :B #{:A}} []))))


(deftest step-time-test
  (is (= 61 (a/step-time :A)))
  (is (= 86 (a/step-time "Z"))))

(deftest solve-b
  (with-redefs [a/default-step-time 0
                a/number-of-workers 2]
    (is (= 15
           (a/solve-b example))))
  (is (= 1040
         (a/solve-b graph))))
