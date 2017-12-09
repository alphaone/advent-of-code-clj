(ns advent2017.dec7
  (:require [clojure.string :as cs]))

(defn parse-line [line]
  (let [[_ name weight h] (re-find #"(\w+)\s+\((\d+)\)(?: -> (.*))?" line)
        holding (if h (cs/split h #", ") [])]
    {:name    name
     :weight  (Integer/parseInt weight)
     :holding holding})
  )

(defn find-root [input]
  (let [programs (->> (cs/split-lines input)
                      (map parse-line))
        all-names (mapcat (fn [p] (:holding p)) programs)]
    (->> programs
         (remove (fn [p] (contains? (set all-names) (:name p))))
         first
         :name)))

