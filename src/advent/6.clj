(ns advent.6)

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
    :off (assoc grid coords 0)
    :on (assoc grid coords 1)
    :toggle (update grid coords #(mod ((fnil inc 0) %) 2))))

(defn switch-single-light-brightness [action grid coords]
  (case action
    :off (update grid coords #(max ((fnil dec 0) %) 0))
    :on (update grid coords (fnil inc 0))
    :toggle (update grid coords #((fnil + 0) % 2))))

(defn switch-single-instruction [switch-fn grid [action coords-list]]
  (reduce (partial switch-fn action) grid coords-list))

(defn lighten-up-grid [switch-fn instructions]
  (->> (clojure.string/split instructions #"\n")
       (map parse-line)
       (reduce (partial switch-single-instruction switch-fn) {})))

(defn count-lights [grid]
  (->> (vals grid) 
       (reduce +)))
