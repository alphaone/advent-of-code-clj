(ns advent2017.dec6
  (:require [clojure.string :as cs]))

(defn distr [banks pos blocks]
  (loop [banks banks
         pos pos
         blocks blocks]
    (if (zero? blocks)
      banks
      (let [banks' (update banks pos inc)
            next-pos (mod (inc pos) (count banks))]
        (recur banks' next-pos (dec blocks))))))

(defn find-pos [x coll]
  (first (keep-indexed #(when (= %2 x) %1) coll)))

(defn pop-max-blocks [banks]
  (let [max (apply max banks)
        max-pos (find-pos max banks)]
    {:max max
     :pos max-pos
     :banks (assoc banks max-pos 0)}))

(defn step [banks]
  (let [{blocks :max pos :pos banks' :banks} (pop-max-blocks banks)
        next-pos (mod (inc pos) (count banks))]
    (distr banks' next-pos blocks)))

(defn solve [input]
  (loop [banks (->> (cs/split input #"\s+")
                    (mapv #(Integer/parseInt %)))
         known []
         i 0]
    (if (contains? (set known) banks)
      [i (inc (find-pos banks (reverse known)))]
      (recur (step banks) (conj known banks) (inc i)))))

