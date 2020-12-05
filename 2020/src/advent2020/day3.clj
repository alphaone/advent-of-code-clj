(ns advent2020.day3)

(defn new-pos [[delta-x] pos size]
  (mod (+ delta-x pos) size))

(defn new-map [[_ delta-y] map]
  (drop delta-y map))

(defn trees-in-line [pos line]
  ({\# 1 \. 0} (get line pos)))

(defn trees-on-slope [slope pos result map]
  (let [current-line (first map)]
    (if (nil? current-line)
      result
      (recur
        slope
        (new-pos slope pos (count current-line))
        (+ result (trees-in-line pos current-line))
        (new-map slope map)))))

(defn trees-in-pos [sledmap [x y] ]
  ({\# 1 \. 0} (get-in sledmap [y x])))

(defn trees-on-slope-alternative [[delta-x delta-y] [x y] sledmap]
  (let [size (count (first sledmap))
        xs (iterate (fn [x] (mod (+ x delta-x) size)) x)
        ys (iterate (fn [y] (+ y delta-y)) y)]
    (->> (interleave xs ys)
         (partition 2)
         (map (partial trees-in-pos sledmap) )
         (take-while some?)
         (reduce +))))
