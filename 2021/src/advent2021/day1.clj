(ns advent2021.day1)

(defn count-incrs [xs]
  (->> xs
       (partition 2 1)
       (filter (fn [[x y]] (< x y)))
       count))

(defn window-sum [xs]
  (->> xs
       (partition 3 1)
       (map #(reduce + %))))
