(ns advent2020.day10)

(defn joltage-difference-distribution [input]
  (let [device (+ 3 (apply max input))
        wall 0]
    (->> [wall device]
         (concat input)
         (sort)
         (partition 2 1)
         (map (fn [[a b]] (- b a)))
         (frequencies))))

(defn solve-a [input]
  (let [dist (joltage-difference-distribution input)]
    (apply * (map dist ((juxt first last)
                        (sort (keys dist)))))))

(defn solve-b [input]
  (let [adapters (sort (conj input 0))
        ; a graph of how a adapter can be connected to which set of other adapters
        ;  {0 (1), 1 (4), 4 (5 6 7), 5 (6 7), 6 (7), 7 (10), 10 (11 12), 11 (12), 12 (15), 15 (16), 16 (19), 19 ()}
        graph (->> adapters
                   (map (fn [n] [n (filter #(< n % (+ 4 n)) adapters)]))
                   (into {}))
        ; give 19 a value 1 initial arrangement
        ; start from the end,
        ; look up 16 in the graph, it points to 19, which has 1 arrangement, 16 has now also 1 arrangement
        ; look up 15 in the graph, it points to 16, which has 1 arrangement, 15 has now also 1 arrangement
        ; ...
        ; look up 10 in the graph, it points to 11 and 12, which both have 1 arrangement, 10 has now 2 arrangements
        ; look up 7 in the graph, it points to 10, which has 2 arrangements, 7 has now also 2 arrangements
        ; ...
        ; look up 5 in the graph, it points to 6 and 7, which both have 2 arrangement, 5 has now 4 arrangements
        ; look up 4 in the graph, it points to 5, 6 and 7, which have 4, 2 and 2 arrangement, 4 has now 8 arrangements
        ; look up 1 in the graph, it points to 4, which has 8 arrangements, 1 has now also 8 arrangements
        ; look up 0 in the graph, it points to 1, which has 8 arrangements, 0 has now also 8 arrangements
        arrangements (->> adapters
                          butlast
                          reverse
                          (reduce (fn [acc n] (assoc acc n (reduce + (map acc (graph n)))))
                                  {(apply max adapters) 1}))]
    (get arrangements 0)))

(let [adapters (sort [16 10 15 5 1 11 7 19 6 12 4 0])]
  (->> adapters
       (map (fn [n] [n (filter #(< n % (+ 4 n)) adapters)]))
       (into {})))
