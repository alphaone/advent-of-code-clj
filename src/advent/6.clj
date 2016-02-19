(ns advent.6)

(defn empty-grid [x y]
  (vec (for [_ (range y)]
         (vec (for [_ (range x)]
                false)))))
(defn empty-grid2 [x y]
  (vec (for [_ (range y)]
         (vec (for [_ (range x)]
                0)))))

(defn extract-action [line]
  (case (second (re-find #"(turn on|turn off|toggle)" line))
    "turn off" :off
    "turn on" :on
    "toggle" :toggle))

(defn extract-coordinates [line]
  (let [[_ x1 y1 x2 y2] (re-find #"(\d+),(\d+) through (\d+),(\d+)" line)]
    (for [x (range (read-string x1) (inc (read-string x2)))
          y (range (read-string y1) (inc (read-string y2)))]
      [x y])))

(defn parse-line [line]
  [(extract-action line) (extract-coordinates line)])

(defn switch-single-light [action grid coords]
  (case action
    :off (assoc-in grid coords false)
    :on (assoc-in grid coords true)
    :toggle (update-in grid coords not)))

(defn switch-single-instruction [grid [action coords-list]]
  (reduce (partial switch-single-light action) grid coords-list))

(defn lighten-up-grid [grid instructions]
   (->> (clojure.string/split instructions #"\n")
        (map parse-line)
        (reduce switch-single-instruction grid)))

(defn count-lights [grid]
  (->> grid
       (flatten)
       (filter true?)
       (count)))

(defn switch-single-light2 [action grid coords]
 (case action
   :off (update-in grid coords (fn [x] (max (dec x) 0)))
   :on (update-in grid coords inc)
   :toggle (update-in grid coords (partial + 2))))

(defn switch-single-instruction2 [grid [action coords-list]]
 (reduce (partial switch-single-light2 action) grid coords-list))

(defn lighten-up-grid2 [grid instructions]
  (->> (clojure.string/split instructions #"\n")
       (map parse-line)
       (reduce switch-single-instruction2 grid)))

(defn count-lights2 [grid]
  (reduce + (flatten grid)))
