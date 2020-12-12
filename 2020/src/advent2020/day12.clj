(ns advent2020.day12)

(defn fwd [heading value [x y]]
  (case heading
    0 [x (- y value)]
    90 [(+ x value) y]
    180 [x (+ y value)]
    270 [(- x value) y]))

(defn move [[x y heading] [cmd value]]
  (case cmd
    :N [x (- y value) heading]
    :S [x (+ y value) heading]
    :E [(+ x value) y heading]
    :W [(- x value) y heading]
    :R [x y (mod (+ heading value) 360)]
    :L [x y (mod (- heading value) 360)]
    :F (conj (fwd heading value [x y]) heading)))
