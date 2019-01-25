(ns advent2016.dec2
  (:require [clojure.string :as cs]
            [clojure.math.combinatorics :as comb]))

(def keypad-a
  [["1" "2" "3"]
   ["4" "5" "6"]
   ["7" "8" "9"]])

(def keypad-b
  [[nil nil "1" nil nil]
   [nil "2" "3" "4" nil]
   ["5" "6" "7" "8" "9"]
   [nil "A" "B" "C" nil]
   [nil nil "D" nil nil]])

(defn coords [keypad num]
  (->> (comb/cartesian-product (range (-> keypad first count))
                               (range (-> keypad count)))
       (filter (fn [[x y]] (= num (get-in keypad [y x]))))
       (first)))

(defn delta [dir]
  (case dir "U" [0 -1] "D" [0 1] "L" [-1 0] "R" [1 0]))

(defn dir->coords [[x y] dir]
  (let [[dx dy] (delta dir)]
    [(+ x dx) (+ y dy)]))

(defn move [keypad cur-num dir]
  (let [[x y] (-> (coords keypad cur-num)
                  (dir->coords dir))]
    (if-let [new-num (get-in keypad [y x])]
      new-num
      cur-num)))

(defn follow [keypad [cur-pos path] line]
  (let [new-pos (->> (cs/split line #"")
                     (reduce (partial move keypad) cur-pos))]
    [new-pos (conj path new-pos)]))

(defn parse-instruction [keypad instruction]
  (->> (cs/split instruction #"\n")
       (reduce (partial follow keypad) ["5" []])
       (second)
       (cs/join)))
