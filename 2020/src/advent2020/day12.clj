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

(defn fwd2 [value [x y wpx wpy]]
  [(+ x (* value wpx)) (+ y (* value wpy))])

(defn rotate [value [wpx wpy]]
  (case (mod (+ 360 value) 360)
    90 [(- wpy) wpx]
    180 [(- wpx) (- wpy)]
    270 [wpy (- wpx)]))

(defn move2 [[x y wpx wpy] [cmd value]]
  (case cmd
    :N [x y wpx (- wpy value)]
    :S [x y wpx (+ wpy value)]
    :E [x y (+ wpx value) wpy]
    :W [x y (- wpx value) wpy]

    :R (concat [x y] (rotate value [wpx wpy]))
    :L (concat [x y] (rotate (- value) [wpx wpy]))

    :F (concat (fwd2 value [x y wpx wpy]) [wpx wpy])
    ))
