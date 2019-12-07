(ns advent2019.dec3
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn parse-command [cmd]
  (let [[_ direction amount] (re-matches #"([LRUD])(\d+)" cmd)]
    [direction (Integer/parseInt amount)]))

(def ops {"R" inc "L" dec "U" inc "D" dec})
(def which-coord {"R" 0 "L" 0 "U" 1 "D" 1})

(defn operation [cmd]
  (let [[dir steps] (parse-command cmd)
        op     (ops dir)
        x-or-y (which-coord dir)]
    [#(update % x-or-y op) steps]))

(defn steps [cmd]
  (second (parse-command cmd)))

(defn path [current-pos cmd]
  (let [[op amount] (operation cmd)]
    (->> (iterate op current-pos)
         (drop 1)
         (take amount))))

(defn move [current-pos cmd]
  (-> (path current-pos cmd)
      last))

(defn mark-board [cell step wire-id]
  (update cell wire-id (fn [historic] (or historic step))))

(defn lay-wire [step wire-id board idx pos]
  (update board pos mark-board (+ step (inc idx)) wire-id))

(defn single-wire [board current-pos step wire-id cmd]
  (let [path (vec (path current-pos cmd))]
    (reduce-kv (partial lay-wire step wire-id) board path)))

(defn wire [board current-pos step wire-id cmds]
  (if (empty? cmds)
    board
    (let [cmd    (first cmds)
          board' (single-wire board current-pos step wire-id cmd)
          pos'   (move current-pos cmd)
          step'  (+ step (steps cmd))]
      (recur board' pos' step' wire-id (rest cmds)))))

(defn find-nearest-crossings [board]
  (->> board
       (filter (fn [[_ cell]] (= (keys cell) [1 2])))
       keys
       (sort-by (fn [[x y]] (+ (Math/abs x) (Math/abs y))))
       first))

(defn fewest-steps-to-crossings [board]
  (->> board
       (filter (fn [[_ cell]] (= (keys cell) [1 2])))
       (sort-by (fn [[_ cell]] (reduce + (vals cell))))
       first))

(defn puzzle-input []
  (->> "dec3.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (map (fn [s] (cs/split s #",")))))

(defn solve-part1 []
  (let [[path1 path2] (puzzle-input)]
    (-> {}
        (wire [0 0] 0 1 (vec path1))
        (wire [0 0] 0 2 (vec path2))
        (find-nearest-crossings))))

(defn solve-part2 []
  (let [[path1 path2] (puzzle-input)]
    (-> {}
        (wire [0 0] 0 1 (vec path1))
        (wire [0 0] 0 2 (vec path2))
        (fewest-steps-to-crossings))))
