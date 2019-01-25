(ns advent.7-test
  (:require [clojure.test :refer :all]
            [clojure.core.cache :as cache]
            [advent.7 :as a]))

(deftest build-wire-map-test
  (testing "it builds a map with all the wirings"
    (is (= {:a [:AND :b :c]
            :x [:NOT :a]}
           (a/build-map "b AND c -> a
                         NOT a -> x")))))

(deftest parse-line-test
  (testing "it parses a single line"
    (is (= [:b [:DIRECT :14146]]
           (a/parse-line "14146 -> b")))
    (is (= [:a [:DIRECT :b]]
           (a/parse-line "b -> a")))
    (is (= [:a [:AND :b :c]]
           (a/parse-line "b AND c -> a")))
    (is (= [:a [:NOT :b]]
           (a/parse-line "NOT b -> a")))
    (is (= [:es [:LSHIFT :eo 15]]
           (a/parse-line "eo LSHIFT 15 -> es")))))

(defn new-cache []
  (atom (cache/fifo-cache-factory {})))

(deftest evaluate-test
  (testing "it evaluates the wire map to get the value of the given name"
    (is (= 5
           (a/evaluate-memo (new-cache) {:x [:DIRECT :5]}
                                        :x)))
    (is (= 2r11100
           (a/evaluate-memo (new-cache) {:a [:DIRECT :2r0111]
                                         :x [:LSHIFT :a 2]}
                                        :x)))
    (is (= 1
           (a/evaluate-memo (new-cache) {:a [:DIRECT :2r1001]
                                         :x [:RSHIFT :a 3]}
                                        :x)))
    (is (= 2r1001
           (a/evaluate-memo (new-cache) {:a [:DIRECT :2r1011]
                                         :b [:DIRECT :2r1101]
                                         :x [:AND :a :b]}
                                        :x)))
    (is (= 2r1111
           (a/evaluate-memo (new-cache) {:a [:DIRECT :2r1011]
                                         :b [:DIRECT :2r1101]
                                         :x [:OR :a :b]}
                                        :x)))
    (is (= -8
           (a/evaluate-memo (new-cache) {:a [:DIRECT :2r0111]
                                         :x [:NOT :a]}
                                        :x)))

    (let [wire-map (a/build-map (slurp "resources/7.txt"))]
      (is (= 956
             (a/evaluate-memo (new-cache) wire-map :a))))

    (let [wire-map (assoc (a/build-map (slurp "resources/7.txt"))
                          :b [:DIRECT :956])]
      (is (= 40149
             (a/evaluate-memo (new-cache) wire-map :a))))))
