(ns advent2018.dec3-test
  (:require [clojure.test :refer :all]
            [advent2018.dec3 :as a]))

(deftest coords-for-shell-test
  (is (= {[3 2] ["123"]
          [3 3] ["123"]
          [3 4] ["123"]
          [4 2] ["123"]
          [4 3] ["123"]
          [4 4] ["123"]}
         (a/add-patch-to-grid {} {:id "123" :x 3 :y 2 :w 2 :h 3})))
  (is (= {[3 2] ["123"]
          [3 3] ["123" "456"]
          [3 4] ["123"]
          [4 2] ["123"]
          [4 3] ["123" "456"]
          [4 4] ["123"]}
         (-> {}
             (a/add-patch-to-grid {:id "123" :x 3 :y 2 :w 2 :h 3})
             (a/add-patch-to-grid {:id "456" :x 3 :y 3 :w 2 :h 1})))))

(deftest coords-for-patch-test
  (is (= #{[3 2] [3 3] [3 4]
           [4 2] [4 3] [4 4]}
         (a/coords-for-patch {:id "123" :x 3 :y 2 :w 2 :h 3}))))

(deftest overlap-inches-test
  (is (= 2
         (a/overlap-inches {[3 2] ["123" "456"]
                            [3 3] ["123"]
                            [3 4] ["123" "987"]
                            [4 2] ["123"]
                            [4 3] ["123"]
                            [4 4] ["123"]}))))

(deftest nonoverlapping-test
  (is (= #{:c}
         (a/non-overlap-patches [[:a :b] [:a] [:c] [:d :a :b]]))))

(deftest read-patch-test
  (is (= {:id "123" :x 3 :y 2 :w 5 :h 4}
         (a/read-patch "#123 @ 3,2: 5x4"))))

(deftest solve-test
  (is (= 117505
         (a/solve-a))))

(deftest solve-b-test
  (is (= #{"1254"}
         (a/solve-b))))