(ns advent2018.dec11)

(defn rack-id [x]
  (+ x 10))

(defn hundreds-digit [i]
  (-> i (/ 100) (Math/floor) (rem 10) (int)))

(defn power-level [serial-number [x y]]
  (let [rack-id (rack-id x)]
    (-> rack-id
        (* y)
        (+ serial-number)
        (* rack-id)
        (hundreds-digit)
        (- 5))))

(defn square-power-level [serial-number [x-start y-start]]
  (->> (for [x (range x-start (+ x-start 3))
             y (range y-start (+ y-start 3))]
         (power-level serial-number [x y]))
       (reduce +)))

(defn find-max-power-level [serial-number {:keys [max-power-level] :as acc} [x y]]
  (let [power-level (square-power-level serial-number [x y])]
    (if (> power-level max-power-level)
      {:max-power-level power-level
       :best-coords     [x y]}
      acc)))

(defn best-square [serial-number]
  (->> (for [x (range 1 299) y (range 1 299)] [x y])
       (reduce (partial find-max-power-level serial-number)
               {:max-power-level 0
                :best-coords     [-1 -1]})
       :best-coords))
