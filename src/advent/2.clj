(ns advent.2)

(defn extra [v]
  (let [sorted (sort v)]
    (* (first sorted) (second sorted))))

(defn paper [in]
  (let [v (->> (clojure.string/split in #"x")
               (map read-string))
        l (first v)
        w (second v)
        h (get (vec v) 2)]
    (+ (* 2 l w)
       (* 2 w h)
       (* 2 l h)
       (extra v)))
  
  )

(defn papers [in]
  (let [ps (clojure.string/split in #"\n")]
    (reduce + (map paper ps))))


(defn bow [v]
  (apply * v))

(defn ribbon [in]
  (let [v (->> (clojure.string/split in #"x")
               (map read-string))
        sorted (sort v)]
    (+ (* 2 (+ (first sorted) (second sorted)))
       (bow v))))

(defn ribbons [in]
  (let [ps (clojure.string/split in #"\n")]
    (reduce + (map ribbon ps))))