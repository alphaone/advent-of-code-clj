(ns advent2016.dec1
  (:require [clojure.string :as cs]))

(defn split-instr [instruction]
  (let [[dir l] (cs/split instruction #"" 2)]
    [dir (read-string l)]))

(defn abs [n] (max n (- n)))

(defn calc-distance [[sx sy] [ex ey]]
  (+ (abs (- sx ex))
     (abs (- sy ey))))

(defn turn [last-dir left-or-right]
  (case last-dir
    "N" (if (= left-or-right "L") "W" "E")
    "E" (if (= left-or-right "L") "N" "S")
    "S" (if (= left-or-right "L") "E" "W")
    "W" (if (= left-or-right "L") "S" "N")))

(defn move-coords [dir l [x y]]
  (case dir
    "N" [x (+ y l)]
    "E" [(+ x l) y]
    "S" [x (- y l)]
    "W" [(- x l) y]))

(defn move [[coords last-dir] instruction]
  (let [[left-or-right l] (split-instr instruction)
        new-dir (turn last-dir left-or-right)]
    [(move-coords new-dir l coords) new-dir]))

(defn follow [instructions]
  (->> (reduce move [[0 0] "N"] instructions)
       (first)
       (calc-distance [0 0])))
