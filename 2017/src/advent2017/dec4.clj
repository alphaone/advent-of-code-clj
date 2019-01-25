(ns advent2017.dec4
  (:require [clojure.string :as cs]))

(defn valid-a? [passphrase]
  (let [words (cs/split passphrase #"\W+")]
    (= (count words) (count (set words)))))

(defn valid-b? [passphrase]
  (let [words (cs/split passphrase #"\W+")]
    (= (count words) (count (set (map frequencies words))))))

(defn count-valids [valid-fn input]
  (->> input
       (cs/split-lines)
       (map valid-fn)
       (filter true?)
       (count)))
