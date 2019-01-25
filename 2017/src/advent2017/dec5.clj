(ns advent2017.dec5
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn step-a [{:keys [r p]}]
  (let [p' (+ p (get r p))]
    {:r (update r p inc)
     :p p'}))

(defn step-b [{:keys [r p]}]
  (let [x (get r p)
        p' (+ p x)]
    {:r (update r p (if (>= x 3) dec inc))
     :p p'}))

(defn valid? [{:keys [r p]}]
  (< -1 p (count r)))

(defn count-steps [step-fn input]
  (let [r (->> (cs/split-lines input)
               (mapv #(Integer/parseInt %)))
        s {:r r :p 0}]
    (loop [s s i 0]
      (if-not (valid? s)
        i
        (recur (step-fn s) (inc i))))))
