(ns advent2018.dec10
  (:require [clojure.string :as cs]
            [quil.core :as q]
            [clojure.java.io :as io]))

(defn parse [line]
  (let [[_ x y vx vy] (re-find #"position=<\s*(\-?\d+),\s*(\-?\d+)> velocity=<\s*(-?\d+),\s*(-?\d+)>" line)]
    [[(Integer/parseInt x) (Integer/parseInt y)]
     [(Integer/parseInt vx) (Integer/parseInt vy)]]))

(defn draw-point [[origin-x origin-y] grid [x y]]
  (update grid (- y origin-y) assoc (- x origin-x) "#"))

(defn scale [min-v max-v min-t max-t i]
  (+ (* (/ (- i min-v) (- max-v min-v))
        (- max-t min-t))
     min-t))

(defn draw-dots [dots]
  (let [points  (map first dots)
        x-min   (apply min 0 (map first points))
        x-max   (apply max 400 (map first points))
        y-min   (apply min -100 (map second points))
        y-max   (apply max 400 (map second points))

        scale-x (partial scale x-min x-max 0 (q/width))
        scale-y (partial scale y-min y-max 0 (q/height))]

    (q/clear)
    (q/background 255)
    (q/text (str (q/frame-count)) 10 10)
    (q/fill 0 0 0)
    (doseq [[x y] points]
      (q/ellipse (scale-x x) (scale-y y) 2 2))))

(defn draw-reductions [reductions]
  (let [dots    (first @reductions)
        _       (swap! reductions rest)]
    (draw-dots dots)))

(defn fly-dot [[[x y] [vx vy]]]
  [[(+ x vx) (+ y vy)]
   [vx vy]])

(defn fly [dots]
  (map fly-dot dots))
