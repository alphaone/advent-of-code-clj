(ns advent2020.day7
  (:require [clojure.set :as set]))

(defn parse-rule [line]
  (let [[_ outside inside] (re-find #"(\w+ \w+) bags contain (.*)." line)
        bags (map second (re-seq #"\d+ (\w+ \w+) bags?" inside))]
    {outside bags}))

(defn parse-input [input]
  (reduce merge (map parse-rule input)))

(defn invert-bags [bags]
  (reduce
    (fn [m [k vs]]
      (reduce (fn [acc v] (update acc v (fnil conj []) k)) m vs))
    {}
    bags))

(defn can-contain? [inverted-bags color]
  (let [bags (set (get inverted-bags color))]
    (set/union bags (apply set/union (map #(can-contain? inverted-bags %) bags)))))

(defn parse-color-requirement [[_ n color]]
  [color (Integer/parseInt n)])

(defn parse-rule2 [line]
  (let [[_ outside inside] (re-find #"(\w+ \w+) bags contain (.*)." line)
        colored-bags (map parse-color-requirement (re-seq #"(\d+) (\w+ \w+) bags?" inside))]
    {outside (into {} colored-bags)}))

(defn parse-input2 [input]
  (reduce merge (map parse-rule2 input)))

(defn needed-bags [all-bags color]
  (->> (get all-bags color)
       (map (fn [[c n]] (+ n (* n (needed-bags all-bags c)))))
       (reduce +)))
