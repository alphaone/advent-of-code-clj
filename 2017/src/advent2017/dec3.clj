(ns advent2017.dec3)

(defn go-y [direction shell-no {:keys [x y]}]
  (let [steps (range 1 (inc (* 2 shell-no)))]
    (map (fn [i] {:x x :y (direction y i)}) steps)))

(defn go-x [direction shell-no {:keys [x y]}]
  (let [steps (range 1 (inc (* 2 shell-no)))]
    (map (fn [i] {:x (direction x i) :y y}) steps)))

(defn coords-for-shell [shell-no]
  (if (= 0 shell-no)
    [{:x 0 :y 0}]
    (let [pseudo-start {:x shell-no :y (* -1 shell-no)}
          to-north     (go-y + shell-no pseudo-start)
          to-west      (go-x - shell-no (last to-north))
          to-south     (go-y - shell-no (last to-west))
          to-east      (go-x + shell-no (last to-south))]
      (concat to-north to-west to-south to-east))))

(defn all-coords []
  (mapcat coords-for-shell (range)))

(defn manhatten-dist [c]
  (+ (Math/abs (:x c))
     (Math/abs (:y c))))

(defn neighbor-coods [{:keys [x y]}]
  (for [x' (range (dec x) (+ x 2))
        y' (range (dec y) (+ y 2))
        :when (not (and (= x x') (= y y')))]
    {:x x' :y y'}))

(defn neighbor-sum [adj-sums coord]
  (->> (neighbor-coods coord)
       (select-keys adj-sums)
       (vals)
       (reduce +)))

(defn step [stop adj-sums coord]
  (let [value (neighbor-sum adj-sums coord)]
    (if (> value stop)
      (reduced value)
      (assoc adj-sums coord value))))

