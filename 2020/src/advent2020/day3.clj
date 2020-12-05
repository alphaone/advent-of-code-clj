(ns advent2020.day3)

(defn new-pos [[delta-x] pos size]
  (mod (+ delta-x pos) size))

(defn new-map [[_ delta-y] map]
  (drop delta-y map))

(defn trees [pos line]
  ({\# 1 \. 0} (get line pos)))

(defn trees-on-slope [slope pos result map]
  (let [current-line (first map)]
    (if (nil? current-line)
      result
      (recur
        slope
        (new-pos slope pos (count current-line))
        (+ result (trees pos current-line))
        (new-map slope map)))))


