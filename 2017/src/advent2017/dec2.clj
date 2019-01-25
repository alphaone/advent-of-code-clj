(ns advent2017.dec2
  (:require [clojure.string :as cs])
  (:import (clojure.lang Ratio)))

(defn parse-line [line]
  (->> (cs/split line #"\s")
       (map #(Integer/parseInt %)))
  )

(defn diff [line]
  (- (apply max line) (apply min line)))

(defn checksum [input]
  (->> (cs/split-lines input)
       (map parse-line)
       (map diff)
       (reduce +)))

(defn even-division [line]
  (->> (for [x line y line :when (not= x y)] (/ x y))
       (filter (fn [r] (instance? Long r)))
       (first)))

(defn checksum-b [input]
  (->> (cs/split-lines input)
       (map parse-line)
       (map even-division)
       (reduce +)))