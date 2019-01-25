(ns advent.3)

(defn move [[x y] dir]
  (case dir
    \^ [x (inc y)]
    \v [x (dec y)]
    \> [(inc x) y]
    \< [(dec x) y]))

(defn follow-directions [f path dir]
  (conj path (move (f path) dir)))

(defn santa [start f input]
  (-> (reduce (partial follow-directions f) start input)
      (set)
      (count)))

(defn single-santa [input]
  (santa '([0 0]) first input))

(defn double-santa [input]
  (santa '([0 0] [0 0]) fnext input))