(ns advent2017.dec1)

(defn- make-pseudo-ring [s]
  (str s (first s)))

(defn captcha-a [input]
  (->> input
       (make-pseudo-ring)
       (re-seq #"(\d)(?=\1)")
       (map second)
       (map #(Integer/parseInt (str %)))
       (reduce +)))

(defn matching-digits [input i]
  (let [l      (count input)
        offset (/ l 2)
        x      (nth input i)
        x'     (nth input (mod (+ i offset) l))]
    (when (= x x') x)))

(defn captcha-b [input]
  (let [l (count input)]
    (->> (range l)
         (keep (partial matching-digits input))
         (map #(Integer/parseInt (str %)))
         (reduce +))))

(defn shift [offset list]
  (concat (drop offset list) (take offset list)))

(defn captcha [offset input]
  (let [input' (shift offset input)]
    (->> (map #(if (= %1 %2) %2 \0) input input')
         (map #(Integer/parseInt (str %)))
         (reduce +))))

(defn captcha-b [input]
  (let [offset (/ (count input) 2)]
    (captcha offset input)))

(defn captcha-a [input]
  (captcha 1 input))