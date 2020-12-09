(ns advent2020.day9)

(defn valid? [preamble x]
  (->> (for [a preamble b preamble :when (not= a b)]
         [a b])
       (filter (fn [[a b]] (= x (+ a b))))
       (seq)
       some?))

(defn partition->preamble-and-x [size partition]
  [(take size partition) (first (->> partition (drop size)))])

(defn solve-a [size input]
  (->> (partition (inc size) 1 input)
       (map #(partition->preamble-and-x size %))
       (filter (fn [[preamble x]] (not (valid? preamble x))))
       (first)
       (second)))
