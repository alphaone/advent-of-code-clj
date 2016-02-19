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
  (let [[action a b] (get wire-map key)]
    (if action
      (case action
        :DIRECT (evaluate-memo cache wire-map a)
        :LSHIFT (bit-shift-left (evaluate-memo cache wire-map a) b)
        :RSHIFT (bit-shift-right (evaluate-memo cache wire-map a) b)
        :AND (bit-and (evaluate-memo cache wire-map a) (evaluate-memo cache wire-map b))
        :OR (bit-or (evaluate-memo cache wire-map a) (evaluate-memo cache wire-map b))
        :NOT (bit-not (evaluate-memo cache wire-map a)))
      (read-string (name key)))))

(defn evaluate-memo [cache wire-map key]
  (if (cache/has? @cache key)
    (get @cache key)
    (let [v (evaluate cache wire-map key)]
      (swap! cache #(cache/miss % key v))
      v)))

