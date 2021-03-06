(ns advent2020.day11)

(defn get-seat [layout [x y]]
  (get-in layout [y x]))

(defn adjacent-positions [[x y]]
  [[(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]
   [(dec x) y] #_[x y] [(inc x) y]
   [(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]])

(defn adjacent-seats [[x y] layout]
  (->> (adjacent-positions [x y])
       (keep (partial get-seat layout))
       (frequencies)))

(defn apply-rule [layout pos]
  (let [cur-seat (get-seat layout pos)
        adj-seats (adjacent-seats pos layout)]
    (cond
      (and (= \L cur-seat) (nil? (adj-seats \#)))
      \#

      (and (= \# cur-seat) (<= 4 (adj-seats \# 0)))
      \L

      :else
      (get-seat layout pos))))

(defn advance [rule old-layout]
  (reduce
    (fn [new-layout [x y]] (assoc-in new-layout [y x] (rule old-layout [x y])))
    old-layout
    (for [x (range (count (first old-layout)))
          y (range (count old-layout))]
      [x y])))

(defn count-occupied [layout]
  (frequencies (apply concat layout)))

(defn find-stable [rule start-layout]
  (->> start-layout
       (iterate #(advance rule %))
       (partition 2 1)
       (drop-while (fn [[g1 g2]] (not= g1 g2)))
       ffirst
       count-occupied))

(defn look-in-direction [pos [fn-x fn-y]]
  (next (iterate (fn [[x y]] [(fn-x x) (fn-y y)]) pos)))

(defn seat-in-direction [layout pos dir]
  (->> dir
       (look-in-direction pos)
       (drop-while #(= \. (get-seat layout %)))
       first
       (get-seat layout)))

(defn adjacent-seats2 [pos layout]
  (->> [[dec dec] [identity dec] [inc dec]
        [dec identity] #_[identity identity] [inc identity]
        [dec inc] [identity inc] [inc inc]]
       (map #(seat-in-direction layout pos %))
       frequencies))

(defn apply-rule2 [layout pos]
  (let [cur-seat (get-seat layout pos)
        adj-seats (adjacent-seats2 pos layout)]
    (cond
      (and (= \L cur-seat) (nil? (adj-seats \#)))
      \#

      (and (= \# cur-seat) (<= 5 (adj-seats \# 0)))
      \L

      :else
      (get-seat layout pos))))
