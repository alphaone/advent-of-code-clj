(ns advent.4
  (:require [digest]))

(defn coin? [search-pattern secret i]
  (let [advent-coin (str secret i)]
    (clojure.string/starts-with? (digest/md5 advent-coin) search-pattern)))

(defn advent-coins [no-of-zeros secret]
  (let [search-pattern (apply str (repeat no-of-zeros "0"))]
    (->> (filter (partial coin? search-pattern secret) (range 1e7))
         (first))))
