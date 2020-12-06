(ns advent2020.day6
  (:require [clojure.string :as str]))

(defn count-answers [f group]
  (->> group
       (map set)
       (apply f)
       count))

(defn input->groups [input]
  (-> input
      (str/split #"\R\R")
      (->> (map #(str/split % #"\R")))))
