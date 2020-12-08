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
    (if (contains? history pos)
      (reduced [pos acc])
      [input (conj history pos) pos acc])))
