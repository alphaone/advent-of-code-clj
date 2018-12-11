(ns advent2018.dec9)

(defn new-pos [diff {:keys [last-pos marbles]}]
  (if (empty? marbles)
    0
    (let [x (mod (+ diff last-pos) (count marbles))]
      (if (zero? x) (count marbles) x))))

(defn default-turn [{:keys [marbles scores] :as circle} [p i]]
  (let [new-pos (new-pos 2 circle )
        [before after] (split-at new-pos marbles)]
    {:scores   scores
     :last-pos new-pos
     :marbles  (doall (concat before [i] after))}))

(defn twenty-three-turn [{:keys [marbles scores] :as circle} [p i]]
  (let [new-pos (new-pos -7 circle)
        [before after] (split-at new-pos marbles)
        score   (+ i (first after))
        after' (drop 1 after)]
    {:scores   (update scores p (fnil + 0) score)
     :last-pos new-pos
     :marbles  (doall (concat before after'))}))

(defn add-to-circle [circle [p i]]
  (when (zero? (mod i 2500)) (println "MarbleNr:" i))
  (if (and (< 0 i) (zero? (mod i 23)))
    (twenty-three-turn circle [p i])
    (default-turn circle [p i])))

(defn play [no-of-players max]
  (->> (map vector (cycle (range no-of-players)) (range (inc max)))
       (reduce add-to-circle
               {:last-pos 0
                :marbles  []
                :scores   {}})
       :scores
       (sort-by (comp - val))
       (first)))
