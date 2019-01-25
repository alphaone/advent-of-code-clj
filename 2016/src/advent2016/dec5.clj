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

(defn sixth-letter [word]
  (-> word (nth 5) (str)))

(defn password-a [md5-hashes]
  (->> md5-hashes
       (map sixth-letter)
       (take 8)
       (cs/join)))

(defn sixth-and-seventh-letter [word]
  (let [[pos ch] (->> [5 6]
                      (map #(nth word %))
                      (map str))]
    [(read-string pos) ch]))

(defn has-position? [[pos _]]
  (and (number? pos) (<= 0 pos 7)))

(defn complete-pwd? [pwd]
  (not (cs/includes? pwd "_")))

(defn build-password [password [pos ch]]
  (let [pwd (vec password)]
    (if (= \_ (nth pwd pos))
      (-> pwd (assoc pos ch) (cs/join))
      password)))

(defn password-b [md5-hashes]
  (->> md5-hashes
       (map sixth-and-seventh-letter)
       (filter has-position?)
       (reductions build-password "________")
       (filter complete-pwd?)
       (first))
  )

