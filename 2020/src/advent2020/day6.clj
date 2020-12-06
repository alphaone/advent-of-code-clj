(ns advent2020.day6
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(defn any-answers [group]
  (->> group
       (map set)
       (apply set/union)
       count))

(defn input->groups [input]
  (-> input
      (str/split #"\R\R")
      (->> (map #(str/split % #"\R")))))
