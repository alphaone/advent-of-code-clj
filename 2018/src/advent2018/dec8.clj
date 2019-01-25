(ns advent2018.dec8)

(defn sum-meta-entries [acc [f s & rest :as input]]
  (when (> 100 (count input)) (prn acc input))
  (if (= f 0)
    (do
      (prn "A" )
      [(drop s rest) (reduce + acc (take s rest))])
    (let [[rest' sum] (do (prn "V") (sum-meta-entries 0 rest))]
      (sum-meta-entries (+ acc sum) (concat [(dec f) s] rest')))
    ))
