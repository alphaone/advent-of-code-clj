(ns advent2017.dec1)

(defn extract-digit [[_ n]] 
  (Integer/parseInt n))

(defn- make-ring [s]
  (str s (first s)))

(defn captcha-a [input]
  (->> input
       (make-ring)
       (re-seq #"(\d)(?=\1)")
       (map extract-digit)
       (reduce +)))

(defn matching-digits [input i]
  (let [l (count input)
        offset (/ l 2)
        x (nth input i)
        x' (nth input (mod (+ i offset) l))]
    (when (= x x') x)))

(defn captcha-b [input]
  (let [l (count input)]
    (->> (range l)
         (keep (partial matching-digits input))
         (map #(Integer/parseInt (str %)))
         (reduce +))))