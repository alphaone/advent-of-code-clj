(ns advent2016.dec7
  (:require [clojure.string :as cs]))

(defn abba? [input]
  (let [[_ a b] (re-find #"(\w)(\w)\2\1" input)]
    (not= a b)))

(defn aba [input]
  (->> (re-seq #"(\w)(?=(\w)\1)" input)
       (map (fn [[a _ b]] [a b]))
       (filter (fn [[a b]] (not= a b)))))

(defn aba->bab [[a b]]
  (str b a b))

(defn bab? [aba-seqs hypernet]
  (->> aba-seqs 
       (map aba->bab)
       (some (fn [bab] (cs/includes? hypernet bab)))))

(defn parse [ip]
  (let [parts (cs/split ip #"\[|\]")]
    [(take-nth 2 parts)
     (take-nth 2 (rest parts))]))

(defn supports-tls? [ip]
  (let [[ipparts hypernets] (parse ip)]
    (and (some abba? ipparts)
         (not-any? abba? hypernets))))

(defn supports-ssl? [ip]
  (let [[ipparts hypernets] (parse ip)
        abas (apply concat (map aba ipparts))]
    (some #(bab? abas %) hypernets)))
