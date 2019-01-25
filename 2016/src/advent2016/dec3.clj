(ns advent2016.dec3
  (:require [clojure.string :as cs]))

(defn possible? [sides]
  (let [sorted (-> sides sort reverse)]
    (< (first sorted)
       (reduce + (rest sorted)))))

(defn count-possible [triangles]
  (->> triangles (filter possible?) (count)))

(defn parse-line-a [line]
  (map read-string (-> line (cs/trim) (cs/split #"\s+"))))

(defn parse-a [input]
  (map parse-line-a (cs/split input #"\n")))

(defn transpose [m]
  (apply mapv vector m))

(defn parse-b [input]
  (let [triangles (map parse-line-a (cs/split input #"\n"))]
    (->> (partition 3 triangles)
         (map transpose)
         (apply concat))))