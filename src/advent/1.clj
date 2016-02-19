(ns advent.1)

(defn c [i ch]
  (->> i
       (filter #(= ch %))
       (count)))

(defn floor [i]
  (- (c i \() 
     (c i \))))

(defn basement-enter [input]
  (loop [i 1 in input f 0]
    (let [c (first in)
          new-f (if (= c \() (inc f) (dec f))]
      (if (> 0 new-f)
        i
        (recur (inc i) (rest in) new-f)))))