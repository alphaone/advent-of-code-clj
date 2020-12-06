(ns advent2020.day5
  (:require [clojure.string :as str]))

(defn seat-id [input]
  (-> input
      (str/replace "B" "1")
      (str/replace "F" "0")
      (str/replace "R" "1")
      (str/replace "L" "0")
      (Integer/parseInt 2)))

