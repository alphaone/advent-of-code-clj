(ns advent2017.dec9
  (:require [clojure.string :as cs]))

(defn cleanup [input]
  (-> input
      (cs/replace #"!!" "")
      (cs/replace #"!." "")))

(defn remove-garbage [input]
  (-> (cleanup input)
      (cs/replace #"<.*?>" "")))

(defn garbage [input]
  (->> (cleanup input)
       (re-seq #"<(.*?)>")
       (mapcat second)))

(defn parse [clean-input]
  (-> clean-input
      (cs/replace #"\{" "[")
      (cs/replace #"\}" "]")
      (read-string)))

(defn score [lvl tree]
  (apply + lvl (map #(score (inc lvl) %) tree)))



