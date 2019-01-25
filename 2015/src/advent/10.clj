(ns advent.10)

(defn x [[match single]]
  (str (count match) single))

(defn transform [input]
  (->> (re-seq #"(\d)\1*" input)
      (map x)
      (clojure.string/join)))
