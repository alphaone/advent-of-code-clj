(ns advent.6)

(defn empty-grid [x y]
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

(defn switch-single-light-on-off [action grid coords]
  (case action
    :off (assoc-in grid coords 0)
    :on (assoc-in grid coords 1)
    :toggle (update-in grid coords #(mod (inc %) 2))))

(defn switch-single-light-brightness [action grid coords]
  (case action
    :off (update-in grid coords (fn [x] (max (dec x) 0)))
    :on (update-in grid coords inc)
    :toggle (update-in grid coords (partial + 2))))

(defn switch-single-instruction [switch-fn grid [action coords-list]]
  (reduce (partial switch-fn action) grid coords-list))

(defn lighten-up-grid [switch-fn grid instructions]
  (->> (clojure.string/split instructions #"\n")
       (map parse-line)
       (reduce (partial switch-single-instruction switch-fn) grid)))

(defn count-lights [grid]
  (reduce + (flatten grid)))
