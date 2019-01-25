(ns advent.9
  (:require [clojure.math.combinatorics :as combo]))

(defn parse-line [line]
  (let [res (re-find #"(\w+) to (\w+) = (\d+)" line)]
    {:from     (nth res 1)
     :to       (nth res 2)
     :distance (read-string (nth res 3))}))

(defn collect-distances [distances line]
  (let [dist-info (parse-line line)]
    (assoc distances
      [(:from dist-info) (:to dist-info)] (:distance dist-info)
      [(:to dist-info) (:from dist-info)] (:distance dist-info))))

(defn parse [lines]
  (reduce collect-distances {} (clojure.string/split-lines lines)))

(defn locations [distances]
  (-> (keys distances)
      (flatten)
      (set)))

(defn sum-up-distance [distances accu stage]
  (+ accu (get distances stage)))

(defn route-distance [distances route]
  (reduce (partial sum-up-distance distances) 0 (partition 2 1 route)))

(defn find-shortest [distances accu route]
  (let [cur-route-distance (route-distance distances route)]
    (if (< cur-route-distance (:shortest-route-distance accu))
      (assoc accu :shortest-route-distance cur-route-distance
                  :shortest-route route)
      accu)))

(defn shortest-route [distances]
  (reduce 
    (partial find-shortest distances) 
    {:shortest-route-distance 1e10} 
    (combo/permutations (locations distances))))

(defn find-longest [distances accu route]
  (let [cur-route-distance (route-distance distances route)]
    (if (> cur-route-distance (:longest-route-distance accu))
      (assoc accu :longest-route-distance cur-route-distance
                  :longest-route route)
      accu)))

(defn longest-route [distances]
  (reduce 
    (partial find-longest distances) 
    {:longest-route-distance 0} 
    (combo/permutations (locations distances))))
