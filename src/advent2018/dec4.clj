(ns advent2018.dec4
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]))

(defn parse-guard [guard-str]
  (->> guard-str
       (re-find #"#(\d+)")
       (second)
       (Integer/parseInt)))

(defn parse-incident [incident-str]
  (->> incident-str
       (re-find #"\d{4}-\d{2}-\d{2} 00:(\d{2})")
       (second)
       (Integer/parseInt)))

(defn parse-shift [[[guard-str] incident-strs]]
  {:guard     (parse-guard guard-str)
   :incidents (partition 2 (map parse-incident incident-strs))})

(defn sum-up-sleeptime [[guard shifts]]
  [guard (->> shifts
              (mapcat :incidents)
              (map (fn [[a b]] (- b a)))
              (reduce +))])

(defn calendar [[start end]]
  (reduce (fn [acc i] (assoc acc i 1))
          (vec (repeat 60 0))
          (range start end)))

(defn sleepiest-minute [shifts]
  (->> shifts
       (mapcat :incidents)
       (map calendar)
       (apply mapv +)
       (map-indexed vector)
       (sort-by (comp - second))
       (first)))

(def sleep-schedule
  (->> "dec4.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (sort)
       (partition-by #(.endsWith % "begins shift"))
       (partition 2)
       (map parse-shift)
       (group-by :guard)))

(defn solve-a []
  (let [sleepiest-guard  (->> sleep-schedule
                              (map sum-up-sleeptime)
                              (sort-by (comp - second))
                              ffirst)
        sleepiest-minute (->> sleepiest-guard
                              (get sleep-schedule)
                              (sleepiest-minute)
                              (first))]
    (* sleepiest-guard sleepiest-minute)))

(defn solve-b []
  (let [[guard [minute _]] (->> sleep-schedule
                                (map (fn [[guard shifts]] [guard (sleepiest-minute shifts)]))
                                (sort-by (comp - second second))
                                (first))]
    (* guard minute)))
