(ns advent2018.dec2
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn contains-multiple? [x box-id]
  (->> box-id
       (frequencies)
       (vals)
       (some #(= x %))))

(defn checksum [box-ids]
  (let [twos   (->> box-ids
                    (filter #(contains-multiple? 2 %))
                    (count))
        threes (->> box-ids
                    (filter #(contains-multiple? 3 %))
                    (count))]
    (* twos threes)))

(defn solve-a []
  (->> "dec2.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (checksum)))

(defn differs-by-exactly-one-char? [box-id-a box-id-b]
  (->> (map vector box-id-a box-id-b) 
       (remove #(= (first %) (second %)))
       (count)
       (= 1)))

(defn common-letters [box-id-a box-id-b]
  (->> (map vector box-id-a box-id-b)
       (filter #(= (first %) (second %)))
       (map first)
       (cs/join)))

(defn find-boxes [all box-id]
  (filter #(differs-by-exactly-one-char? box-id %) all))

(defn solve-b []
  (let [box-ids (->> "dec2.txt"
                     (io/resource)
                     (slurp)
                     (cs/split-lines))]
    (->> box-ids 
         (mapcat (partial find-boxes box-ids)) 
         (apply common-letters))))

