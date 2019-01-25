(ns advent2018.dec9
  (:import (java.util Deque ArrayDeque)))

(defn rotate! [^Deque deque ^Integer amount]
  (let [forward   (fn [_] (.addLast deque (.removeFirst deque)))
        backwards (fn [_] (.addFirst deque (.removeLast deque)))]
    (when-not (.isEmpty deque)
      (if (pos? amount)
        (run! forward (range amount))
        (run! backwards (range (Math/abs amount))))))
  deque)

(defn default-turn [{:keys [^Deque marbles scores]} [p i]]
  (rotate! marbles 2)
  (.addFirst marbles i)
  {:scores   scores
   :marbles  marbles})

(defn twenty-three-turn [{:keys [marbles scores] :as circle} [p i]]
  (rotate! marbles -7)
  (let [score   (+ i (.removeFirst marbles))]
    {:scores   (update scores p (fnil + 0) score)
     :marbles  marbles}))

(defn add-to-circle [circle [p i]]
  (if (and (< 0 i) (zero? (mod i 23)))
    (twenty-three-turn circle [p i])
    (default-turn circle [p i])))

(defn play [no-of-players max]
  (->> (map vector (cycle (range no-of-players)) (range (inc max)))
       (reduce add-to-circle
               {:marbles (ArrayDeque. [])
                :scores  {}})
       :scores
       (sort-by (comp - val))
       (first)))
