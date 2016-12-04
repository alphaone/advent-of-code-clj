(ns advent2016.dec4
  (:require [clojure.string :as cs]))

(defn compare-letter-freq [[ch-a freq-a] [ch-b freq-b]]
  (let [freq-cmp (compare  freq-b freq-a)]
    (if (zero? freq-cmp)
      (compare ch-a ch-b)
      freq-cmp)))

(defn checksum [room-name]
  (->> (cs/replace room-name #"\d|-" "")
       (frequencies)
       (sort compare-letter-freq)
       (take 5)
       (map first)
       (cs/join)))

(defn parse [room]
  (let [result (re-find #"(.*)-(\d+)\[(.*)\]" room)]
    [(nth result 1) 
     (read-string (nth result 2)) 
     (nth result 3)]))

(defn real? [[name _ given-checksum]]
  (= given-checksum (checksum name)))

(defn solve [input]
  (let [rooms (cs/split input #"\n")]
    (->> rooms
         (map parse)
         (filter real?)
         (map second)
         (reduce +))))