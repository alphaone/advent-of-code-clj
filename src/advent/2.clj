(ns advent.2)

(defn measurements [input]
  (->> (clojure.string/split input #"x")
       (map read-string)))

(defn extra [measurements]
  (let [[longest second _] (sort measurements)]
    (* longest second)))

(defn paper [input]
  (let [[l w h] (measurements input)]
    (+ (* 2 l w)
       (* 2 w h)
       (* 2 l h)
       (extra [l w h]))))

(defn papers [input]
  (->> (clojure.string/split input #"\n")
       (map paper)
       (reduce +)))


(defn bow [v]
  (apply * v))

(defn ribbon [input]
  (let [measurements (measurements input)
        [longest second _] (sort measurements)]
    (+ (* 2 (+ longest second))
       (bow measurements))))

(defn ribbons [input]
  (->> (clojure.string/split input #"\n")
       (map ribbon)
       (reduce +)))