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

(defn square-power-level [serial-number size [x-start y-start]]
  (->> (for [x (range x-start (+ x-start size))
             y (range y-start (+ y-start size))]
         (power-level serial-number [x y]))
       (reduce +)))

(defn find-max-power-level [serial-number size {:keys [max-power-level] :as acc} [x y]]
  (let [power-level (square-power-level serial-number size [x y])]
    (if (> power-level max-power-level)
      (do (println "Found something good:" size "->" power-level "(" x y ")")
          {:max-power-level power-level
           :best-coords     [x y]
           :size            size})
      acc)))

(defn best-square-of-size [serial-number {:keys [:max-power-level] :as acc} size]
  (->> (for [x (range 1 (- 301 (dec size))) 
             y (range 1 (- 301 (dec size)))] 
         [x y])
       (reduce (partial find-max-power-level serial-number size) acc)))

(defn best-square [serial-number]
  (->> (range 1 20)
       (reduce (partial best-square-of-size serial-number)
               {:max-power-level 0})))
