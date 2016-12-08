(ns advent2016.dec7
  (:require [clojure.string :as cs]))

(defn abba? [input]
  (let [[_ a b] (re-find #"(\w)(\w)\2\1" input)]
    (not= a b)))

(defn parse [ip]
  (let [parts (cs/split ip #"\[|\]")]
    [(take-nth 2 parts)
     (take-nth 2 (rest parts))]))

(defn supports-tls? [ip]
  (let [[ipparts hypernets] (parse ip)]
    (and (some abba? ipparts)
         (not-any? abba? hypernets))))

