(ns advent2020.day1)

(defn pairs
  ([entries] (pairs entries []))
  ([[head & rest] res]
   (if (empty? rest)
     res
     (recur
       rest
       (concat res (map (fn [y] [head y]) rest))))))

(defn find-pair [target entries]
  (->> (pairs entries)
       (filter (fn [[x y]] (= target (+ x y))))))

(defn triples
  ([entries] (triples entries []))
  ([[head & rest] res]
   (if (empty? rest)
     res
     (recur
       rest
       (->> (pairs rest)
            (map (fn [[y z]] [head y z]) )
            (concat res))))))

(defn find-triple [target entries]
  (->> (triples entries)
       (filter (fn [[x y z]] (= target (+ x y z))))))
