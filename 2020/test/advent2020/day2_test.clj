(ns advent2020.day2-test
  (:require [clojure.test :refer :all]
            [advent2020.day2 :as day2]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(deftest parse-line
  (is (= {:min 1 :max 3 :char \a :pwd "abcde"}
         (day2/parse-line "1-3 a: abcde"))))

(deftest valid-test
  (is (= true (day2/valid? {:min 1 :max 3 :char \a :pwd "abcde"})))
  (is (= false (day2/valid? {:min 1, :max 3, :char \b, :pwd "cdefg"})))
  (is (= true (day2/valid? {:min 2, :max 9, :char \c, :pwd "ccccccccc"}))))

(def input (->> (io/resource "day2.txt")
                (slurp)
                (str/split-lines)
                (map day2/parse-line)))

(deftest solve-a
  (is (= 586
         (->> input
              (filter day2/valid?)
              count))))

(deftest valid2-test
  (is (= true (day2/valid2? {:min 1 :max 3 :char \a :pwd "abcde"})))
  (is (= false (day2/valid2? {:min 1, :max 3, :char \b, :pwd "cdefg"})))
  (is (= false (day2/valid2? {:min 2, :max 9, :char \c, :pwd "ccccccccc"}))))

(deftest solve-b
  (is (= 352
         (->> input
              (filter day2/valid2?)
              count))))
