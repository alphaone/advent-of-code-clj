(ns advent2018.dec5
  (:require [clojure.string :as cs]
            [clojure.java.io :as io]))

(defn react? [^Character char-a ^Character char-b]
  (or (and (= (Character/toLowerCase char-a) char-b)
           (= char-a (Character/toUpperCase char-b)))
      (and (= (Character/toUpperCase char-a) char-b)
           (= char-a (Character/toLowerCase char-b)))))

(defn react-polymer* [polymer]
  (loop [[f s & rest-polymer] polymer
         reacted ""]
    (if (nil? s)
      (str reacted f)
      (let [f-s-react?    (react? f s)
            rest-polymer' (if f-s-react?
                            rest-polymer
                            (conj rest-polymer s))
            reacted'      (if f-s-react?
                            reacted
                            (str reacted f))]
        (recur rest-polymer' reacted')))))

(defn react-polymer [polymer]
  (let [reacted (react-polymer* polymer)]
    (if (= reacted polymer)
      polymer
      (recur reacted))))

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
       (sort-by (comp second))
       (first)))

(defn solve-b []
  (let [original (->> "dec5.txt"
                      (io/resource)
                      (slurp))]
    (find-problematic-unit original)))