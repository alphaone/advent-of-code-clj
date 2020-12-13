(ns advent2020.day13)

(defn next-departure [ts bus-id]
  (->> (iterate #(+ bus-id %) 0)
       (drop-while #(<= % ts))
       (first)))

(defn earliest-bus [[ts bus-ids]]
  (let [[bus-id departure]
        (->> bus-ids
             (map (fn [bus-id] [bus-id (next-departure ts bus-id)]))
             (apply min-key second))]
    (* bus-id (- departure ts))))
