(ns advent2017.dec8)

(defn parse-line [line]
  (let [[_ cmd-var cmd-op cmd-val cond-var cond-op cond-val] 
        (re-find #"(\w+)\s(\w+)\s(-?\d+)\sif\s(\w+)\s(\W+)\s(-?\d+)" line)]
    {:cmd  {:var (keyword cmd-var)
            :op  (keyword cmd-op)
            :val (Integer/parseInt cmd-val)}
     :cond {:var (keyword cond-var)
            :op  (keyword cond-op)
            :val (Integer/parseInt cond-val)}}))

(def op->f 
  {:inc +
   :dec -
   :< <
   :> >
   :!= not=
   :<= <=
   :>= >=
   :== =})

(defn make-cmd [cmd]
  (let [f (op->f (:op cmd))]
    (fnil (fn [v] (f v (:val cmd))) 0)))

(defn step [register {:keys [cmd cond]}]
  (if ((make-cmd cond) (get register (:var cond)))
    (update register (:var cmd) (make-cmd cmd))
    register))

(defn max-value [register]
  (apply max (vals register)))