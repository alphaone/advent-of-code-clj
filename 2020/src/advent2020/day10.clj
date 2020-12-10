(ns advent2020.day10)

(defn joltage-difference-distribution [input]
  (let [my-device (+ 3 (apply max input))
        outlet 0]
    (->> [outlet my-device]
         (concat input)
         (sort)
         (partition 2 1)
         (map (fn [[a b]] (- b a)))
         (frequencies)))
  )

(defn solve-a [input]
  (let [dist (joltage-difference-distribution input)]
    (apply * (map dist ((juxt first last)
                        (sort (keys dist)))))))

