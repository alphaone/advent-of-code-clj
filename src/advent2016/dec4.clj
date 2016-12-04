(ns advent2016.dec4
  (:require [clojure.string :as cs]))

(defn compare-letter-freq [[ch-a freq-a] [ch-b freq-b]]
  (let [freq-cmp (compare freq-b freq-a)]
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

(defn real-rooms [input]
  (->> (cs/split input #"\n")
       (map parse)
       (filter real?)))

(defn solve-a [input]
  (->> (real-rooms input)
       (map second)
       (reduce +)))

(defn rotate-char [ch]
  (case ch
    \z \a
    \Z \A
    \space \space
    \- \space
    (-> ch int inc char)))

(defn rotate-name [name]
  (->> name (map rotate-char) (cs/join)))

(defn decypher [[name department]]
  (nth (iterate rotate-name name) department))

(defn storage? [[name department _]]
  (= "northpole object storage"
     (decypher [name department])))

(defn solve-b [input]
  (->> (real-rooms input)
       (filter storage?)
       (first)
       (second)))