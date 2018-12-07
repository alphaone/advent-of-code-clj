(ns advent2018.dec6)

(defn parse-coord [i input]
  (let [[_ x y] (re-find #"(\d+), (\d+)" input)]
    [i [(Integer/parseInt x) (Integer/parseInt y)]]))

(defn manhatten-dist [[x1 y1] [x2 y2]]
  (+ (Math/abs (- x1 x2))
     (Math/abs (- y1 y2))))

(defn nearest [point other-points]
  (let [[closest second-closest] (->> other-points
                                      (map (fn [[i p]] {:id   i
                                                        :dist (manhatten-dist point p)}))
                                      (sort-by :dist)
                                      (take 2))]
    (when (not= (:dist closest) (:dist second-closest))
      (:id closest))))

(defn sum-up-manhatten [point other-points]
  (->> other-points
       (map (fn [[i p]] (manhatten-dist point p)))
       (reduce +)))

(defn find-first-that-stayed-the-same [coll-a coll-b]
  (let [lookup (into {} coll-b)]
    (->> coll-a
         (filter (fn [[id x]] (= x (get lookup id))))
         (first))))

(defn solve-a [[x-start x-end] [y-start y-end] coords]
  (let [grid       (for [x (range x-start x-end)
                         y (range y-start y-end)]
                     [[x y] (nearest [x y] coords)])
        first-run  (->> grid
                        (group-by second)
                        (map (fn [[id points]] [id (count points)]))
                        (sort-by (comp - second)))
        second-run (->> (for [x (range (- x-start 5) (+ x-end 5))
                              y (range (- y-start 5) (+ y-end 5))
                              :when (not (and (< x-start x x-end)
                                              (< y-start y y-end)))]
                          [[x y] (nearest [x y] coords)])
                        (concat grid)
                        (group-by second)
                        (map (fn [[id points]] [id (count points)]))
                        (sort-by (comp - second)))]
    (find-first-that-stayed-the-same first-run second-run)))

(defn solve-b [[x-start x-end] [y-start y-end] coords]
  (->> (for [x (range x-start x-end)
             y (range y-start y-end)]
         [[x y] (sum-up-manhatten [x y] coords)])
       (filter (fn [[_ d]] (< d 10000)))
       (count)))