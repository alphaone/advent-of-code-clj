(ns advent2016.dec8)

(defn on [display [x y]]
  (assoc-in display [y x] "#"))

(defn col [display pos]
  (mapv #(nth % pos) display))

(defn rotate
  ([lst]
   (let [r (butlast lst)
         l (last lst)]
     (vec (cons l r))))
  ([offset lst]
    (nth (iterate rotate lst) offset)))

(defn assoc-col [display pos col]
  (mapv (fn [row val] (assoc row pos val)) display col))

(defmulti draw (fn [type & _] type))
(defmethod draw :rect [_ display w h]
  (let [coords (for [x (range w) y (range h)] [x y])]
    (reduce on display coords)))

(defmethod draw :rotate-column [_ display pos offset]
  (->> (col display pos)
       (rotate offset)
       (assoc-col display pos)))

(defmethod draw :rotate-row [_ display pos offset]
  (->> (nth display pos)
       (rotate offset)
       (assoc display pos)))

(defn parse-rect [instruction]
  (->> (re-find #"rect (\d+)x(\d+)" instruction)
       (rest)
       (map read-string)))

(defn parse-rotate [instruction]
  (let [match (->> (re-find #"rotate (row|column) (y|x)=(\d+) by (\d+)" instruction)
                   (rest))]
    [(->> match first (str "rotate-") keyword)
     [(read-string (nth match 2)) 
      (read-string (nth match 3))]]))

(defn parse [instruction]
  (case (re-find #"^\w+" instruction)
    "rect" [:rect (parse-rect instruction)]
    "rotate" (parse-rotate instruction)))

(defn apply-draw [display [type args]] 
  (apply draw type display args))