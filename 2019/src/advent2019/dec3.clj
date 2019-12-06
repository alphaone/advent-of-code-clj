(ns advent2019.dec3
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn parse-command [cmd]
  (let [[_ direction amount] (re-matches #"([LRUD])(\d+)" cmd)]
    [direction (Integer/parseInt amount)]))

(def ops {"R" inc "L" dec "U" inc "D" dec})
(def which-coord {"R" 0 "L" 0 "U" 1 "D" 1})

(defn operation [cmd]
  (let [[dir amount] (parse-command cmd)
        op     (ops dir)
        x-or-y (which-coord dir)]
    [#(update % x-or-y op) amount]))

(defn path [current-pos cmd]
  (let [[op amount] (operation cmd)]
    (->> (iterate op current-pos)
         (drop 1)
         (take amount))))

(defn move [current-pos cmd]
  (-> (path current-pos cmd)
      last))

(defn mark-board [current wire-id]
  (bit-or current wire-id))

(defn lay-wire [wire-id board pos]
  (update board pos (fnil mark-board 0) wire-id))

(defn single-wire [board current-pos wire-id cmd]
  (let [path (path current-pos cmd)]
    (reduce (partial lay-wire wire-id) board path)))

(defn wire [board current-pos wire-id cmds]
  (if (empty? cmds)
    board
    (let [cmd    (first cmds)
          board' (single-wire board current-pos wire-id cmd)
          pos'   (move current-pos cmd)]
      (recur board' pos' wire-id (rest cmds)))))

(defn find-nearest-crossings [board]
  (->> board
       (filter #(= (val %) 3))
       keys
       (sort-by (fn [[x y]] (+ (Math/abs x) (Math/abs y))))
       first))

(defn solve-part1 []
  (let [[path1 path2] (->> "dec3.txt"
                          (io/resource)
                          (slurp)
                          (cs/split-lines)
                          (map (fn [s] (cs/split s #","))))]
    (-> {}
        (wire [0 0] 1 (vec path1))
        (wire [0 0] 2 (vec path2))
        (find-nearest-crossings))))
