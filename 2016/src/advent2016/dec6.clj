(ns advent2016.dec6
  (:require [clojure.string :as cs]))

(defn transpose [m]
  (->> (apply mapv vector m)
       (map cs/join)))

(defn sort-by-freq [most-or-least-freq coll]
  (let [sorted (sort-by second coll)]
    (if (= most-or-least-freq :most-frequent)
      (-> sorted last first)
      (-> sorted ffirst))))

(defn most-freq-char [most-or-least-freq input]
  (->> input
       (frequencies)
       (sort-by-freq most-or-least-freq)))

(defn solve [most-or-least-freq input]
  (->> input
       (transpose)
       (map (partial most-freq-char most-or-least-freq))
       (cs/join)))