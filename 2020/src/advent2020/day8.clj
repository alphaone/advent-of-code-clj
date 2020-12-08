(ns advent2020.day8)

(defn parse [line]
  (let [[_ cmd arg] (re-find #"(\w{3}) ([+-]\d+)" line)]
    [(keyword cmd) (Integer/parseInt arg)]))

(defn execute [[cmd arg] pos acc]
  (case cmd
    :nop [(inc pos) acc]
    :acc [(inc pos) (+ acc arg)]
    :jmp [(+ pos arg) acc]
    [pos acc]))

(defn step [[input history pos acc]]
  (let [op (get input pos)
        [pos acc] (execute op pos acc)]
    (cond
      (nil? op) (reduced [:done pos acc])
      (contains? history pos) (reduced [:loop pos acc])
      :else [input (conj history pos) pos acc])))

(defn solve [input]
  (->> (iterate step [input #{0} 0 0])
       (drop-while (complement reduced?))
       first
       deref))

(defn mutate-op [[cmd arg]]
  (case cmd
    :nop [:jmp arg]
    :jmp [:nop arg]))

(defn mutate-code [input]
  (keep-indexed
    (fn [i op]
      (when (contains? #{:jmp :nop} (first op))
        (assoc input i (mutate-op op))))
    input))
