(ns advent2018.dec5
  (:require [clojure.string :as cs]
            [clojure.java.io :as io]))

(defn react? [^Character char-a ^Character char-b]
  (or (and (= (Character/toLowerCase char-a) char-b)
           (= char-a (Character/toUpperCase char-b)))
      (and (= (Character/toUpperCase char-a) char-b)
           (= char-a (Character/toLowerCase char-b)))))

(defn react-neighbors [st elem]
  (if (and (peek st) (react? elem (peek st)))
    (pop st)
    (conj st elem)))

(defn react-polymer [polymer]
  (reduce react-neighbors [] polymer))

(defn solve-a []
  (->> "dec5.txt"
       (io/resource)
       (slurp)
       (react-polymer)
       (count)))

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

(defn without-char [polymer ^Character c]
  [c (-> polymer
         (cs/replace (str c) "")
         (cs/replace (str (Character/toUpperCase c)) ""))])

(defn find-problematic-unit [polymer]
  (->> (map #(without-char polymer %) (char-range \a \z))
       (pmap (fn [[c p]] [c (count (react-polymer p))]))
       (sort-by second)
       (first)))

(defn solve-b []
  (let [original (->> "dec5.txt"
                      (io/resource)
                      (slurp))]
    (find-problematic-unit original)))