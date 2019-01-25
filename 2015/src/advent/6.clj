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

(defn switch-on-off [action grid coord]
  (case action
    :off (assoc grid coord 0)
    :on (assoc grid coord 1)
    :toggle (update grid coord #(mod ((fnil inc 0) %) 2))))

(defn switch-all-at-once [grid action coords-list]
  (case action
    :on (apply assoc grid (interleave coords-list (repeat 1)))
    :off (apply assoc grid (interleave coords-list (repeat 0)))
    :toggle (apply assoc grid (interleave coords-list (repeat 1))))
  )



(defn switch-single-light-brightness [action grid coord]
  (case action
    :off (update grid coord #(max ((fnil dec 0) %) 0))
    :on (update grid coord (fnil inc 0))
    :toggle (update grid coord #((fnil + 0) % 2))))

(def a (atom {}))

(defn switch-single-instruction [switch-fn grid [action coords-list]]
  (let [time (System/currentTimeMillis)
        #_(reduce (partial switch-fn action) grid coords-list)
        res (switch-all-at-once grid action coords-list)
        took (- (System/currentTimeMillis) time)]
    (swap! a update action conj {:count (count coords-list) :time took})
    res))

(defn lighten-up-grid [switch-fn instructions]
  (->> (clojure.string/split instructions #"\n")
       (map parse-line)
       (reduce (partial switch-single-instruction switch-fn) {})))

(defn count-lights [grid]
  (->> (vals grid)
       (reduce +)))
