(ns advent.7
  (:require [clojure.core.cache :as cache]))

(defn parse-and-or [line]
  (let [[x action y res]
        (take-last 4
          (re-find #"(\w+) (AND|OR) (\w+) -> (\w+)" line))]
    [(keyword res)
     [(keyword action) (keyword x) (keyword y)]]))

(defn parse-shift [line]
  (let [[x action y res]
        (take-last 4
          (re-find #"(\w+) (LSHIFT|RSHIFT) (\d+) -> (\w+)" line))]
    [(keyword res)
     [(keyword action) (keyword x) (read-string y)]]))

(defn parse-not [line]
  (let [[x res]
        (take-last 2
          (re-find #"NOT (\w+) -> (\w+)" line))]
    [(keyword res)
     [:NOT (keyword x)]]))

(defn parse-direct [line]
  (let [[x res]
        (take-last 2
          (re-find #"(\w+) -> (\w+)" line))]
   [(keyword res)
    [:DIRECT (keyword x)]]))

(defn parse-line [line]
  (cond
    (re-find #"AND|OR" line) (parse-and-or line)
    (re-find #"LSHIFT|RSHIFT" line) (parse-shift line)
    (re-find #"NOT" line) (parse-not line)
    :default (parse-direct line)))

(defn build-map [input]
  (->> (clojure.string/split input #"\n")
       (map parse-line)
       (into {})))

(declare evaluate-memo)

(defn evaluate [cache wire-map key]
  (let [v (get wire-map key)]
    (if v
      (case (first v)
        :DIRECT (evaluate-memo cache wire-map (second v))
        :LSHIFT (bit-shift-left (evaluate-memo cache wire-map (second v)) (get v 2))
        :RSHIFT (bit-shift-right (evaluate-memo cache wire-map (second v)) (get v 2))
        :AND    (bit-and (evaluate-memo cache wire-map (second v)) (evaluate-memo cache wire-map (get v 2)))
        :OR     (bit-or (evaluate-memo cache wire-map (second v)) (evaluate-memo cache wire-map (get v 2)))
        :NOT    (bit-not (evaluate-memo cache wire-map (second v))))
      (read-string (name key)))))

(defn evaluate-memo [cache wire-map key]
  (if (cache/has? @cache key)
      (get @cache key)
      (let [v (evaluate cache wire-map key)]
        (swap! cache #(cache/miss % key v))
        v)))
