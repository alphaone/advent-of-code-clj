(ns advent2016.dec1
  (:require [clojure.string :as cs]))

(defn split-instr [instruction]
  (let [[dir l] (cs/split instruction #"" 2)]
    [dir (read-string l)]))

(defn abs [n] (max n (- n)))

(defn calc-distance [[sx sy] [ex ey]]
  (+ (abs (- sx ex))
     (abs (- sy ey))))

(defn turn [last-dir turning]
  (case last-dir
    "N" (case turning "S" "N" "L" "W" "R" "E")
    "E" (case turning "S" "E" "L" "N" "R" "S")
    "S" (case turning "S" "S" "L" "E" "R" "W")
    "W" (case turning "S" "W" "L" "S" "R" "N")))

(defn move-coords [dir l [x y]]
  (case dir
    "N" [x (+ y l)]
    "E" [(+ x l) y]
    "S" [x (- y l)]
    "W" [(- x l) y]))

(defn move [[coords last-dir] instruction]
  (prn [coords last-dir] instruction)
  (let [[left-or-right l] (split-instr instruction)
        new-dir (turn last-dir left-or-right)]
    [(move-coords new-dir l coords) new-dir]))

(defn move-b [[coords last-dir visited stay?] instruction]
  (prn [coords last-dir visited stay?] instruction)
  (if stay?
    [coords last-dir visited stay?]
    (let [[left-or-right l] (split-instr instruction)
          new-dir (turn last-dir left-or-right)
          new-coords (move-coords new-dir l coords)]
      [new-coords new-dir (conj visited new-coords) (contains? visited new-coords)])))

(defn follow [instructions]
  (->> (reduce move [[0 0] "N"] instructions)
       (first)
       (calc-distance [0 0])))

(defn expand [instruction]
  (let [[dir l] (split-instr instruction)]
    (cons (str dir "1")
          (repeat (dec l) "S1"))))

(defn follow-b [instructions]
  (->> instructions
       (map expand)
       (flatten)
       (reduce move-b [[0 0] "N" #{} false] )
       (first)
       (calc-distance [0 0])))
