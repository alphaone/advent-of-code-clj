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

(defn correct-time-diff? [time bus-b offset-b]
  (= (mod time bus-b)
     (- bus-b offset-b)))

; BusA(7| 0)
; BusB(5| 1)
;
; 0---------1---------1---------2---------3---------4
; 0----5----0----5----0----5----0----5----0----5----0
; A......A......A......A......A......A......A......A
; B....B....B....B....B....B....B....B....B....B....B
;               ^^                                 ^^
; combined BusAB(35| 14)
(defn combine-bus [[bus-a offset-a] [bus-b offset-b]]
  (let [offset (->> (iterate #(+ bus-a %) offset-a)
                    (drop-while #(not (correct-time-diff? % bus-b offset-b)))
                    first)]
    [(* bus-a bus-b) offset]))
