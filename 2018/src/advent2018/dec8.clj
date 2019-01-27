(ns advent2018.dec8)

(defn sum-meta-entries [acc [f s & rest :as input]]
  (if (= f 0)
    [(drop s rest) (reduce + acc (take s rest))]
    (let [[rest' sum] (sum-meta-entries 0 rest)]
      (sum-meta-entries (+ acc sum) (concat [(dec f) s] rest')))
    ))
