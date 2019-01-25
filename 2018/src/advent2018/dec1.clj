(ns advent2018.dec1
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn first-double-freq [{:keys [sum interim]} freq]
  (let [sum' (+ sum freq)]
    (if (contains? interim sum')
      (reduced sum')
      {:sum     sum'
       :interim (conj interim sum')})))

(defn solve-a []
  (->> "dec1.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (map #(Integer/parseInt %))
       (reduce +)))

(defn solve-b []
  (->> "dec1.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (map #(Integer/parseInt %))
       (cycle)
       (reduce first-double-freq {:sum 0 :interim #{0}})))

