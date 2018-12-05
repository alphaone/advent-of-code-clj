(ns advent2018.dec3
  (:require [clojure.java.io :as io]
            [clojure.string :as cs]
            [clojure.set :as set]))

(defn coords-for-patch [{:keys [x y w h]}]
  (set (for [x' (range x (+ x w))
             y' (range y (+ y h))]
         [x' y'])))

(defn add-patch-to-grid [grid patch]
  (let [id     (:id patch)
        coords (coords-for-patch patch)]
    (reduce
      (fn [grid [x y]] (update grid [x y] (fnil conj []) id))
      grid
      coords)
    ))

(defn overlap-inches [grid]
  (->> grid
       (filter (fn [[k v]] (< 1 (count v))))
       (count)))

(defn separate [f coll]
  ((juxt (partial filter f) (partial remove f)) coll))

(defn non-overlap-patches [markers]
  (let [[multi single] (map (comp set flatten) (separate #(< 1 (count %)) markers))]
    (set/difference single multi)))

(defn read-patch [string]
  (let [[_ id x y w h] (re-find #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)" string)]
    {:id id
     :x  (Integer/parseInt x)
     :y  (Integer/parseInt y)
     :w  (Integer/parseInt w)
     :h  (Integer/parseInt h)}))

(defn solve-a []
  (->> (io/resource "dec3.txt")
       (slurp)
       (cs/split-lines)
       (map read-patch)
       (reduce add-patch-to-grid {})
       (overlap-inches)))

(defn solve-b []
  (->> (io/resource "dec3.txt")
       (slurp)
       (cs/split-lines)
       (map read-patch)
       (reduce add-patch-to-grid {})
       (vals)
       (non-overlap-patches)))