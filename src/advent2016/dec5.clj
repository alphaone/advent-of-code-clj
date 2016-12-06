(ns advent2016.dec5
  (:require [digest]
            [clojure.string :as cs]))


(defn md5 [input]
  (digest/md5 input))

(defn md5-with-five-zeros [door-id]
  (->> (range)
       (map #(str door-id %))
       (map md5)
       (filter #(cs/starts-with? % "00000"))))

(defn fifth-letter [word]
  (-> word (nth 5) (str)))

(defn password [md5-hashes]
  (->> md5-hashes
       (map fifth-letter)
       (take 8)
       (cs/join)))

