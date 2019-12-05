(ns advent2019.dec2
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(def ops {1 +
          2 *})

(defn op [memory instruction-pointer]
  (->> (nth memory instruction-pointer) (get ops)))

(defn process-instruction [memory instruction-pointer]
  (let [operator       (op memory instruction-pointer)
        operand1       (nth memory (nth memory (inc instruction-pointer)))
        operand2       (nth memory (nth memory (+ instruction-pointer 2)))
        target-address (nth memory (+ instruction-pointer 3))
        result         (operator operand1 operand2)]
    (assoc memory target-address result)))

(defn end-state? [memory intruction-pointer]
  (= 99 (nth memory intruction-pointer)))

(defn process [memory instruction-pointer]
  (let [memory'                  (process-instruction memory instruction-pointer)
        next-instruction-pointer (+ instruction-pointer 4)]
    (if (end-state? memory' next-instruction-pointer)
      memory'
      (recur memory' next-instruction-pointer))))

(defn split-comma [s]
  (cs/split s #","))

(defn read-input []
  (->> "dec2.txt"
       io/resource
       slurp
       cs/trim-newline
       split-comma
       (map #(Integer/parseInt %))
       vec))

(defn solve-part1 []
  (let [noun 12
        verb 2]
    (-> (read-input)
        (assoc 1 noun
               2 verb)
        (process 0)
        first)))
