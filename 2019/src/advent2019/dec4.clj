(ns advent2019.dec4)

(defn contains-freq-chars? [freq-pred pwd]
  (some freq-pred (vals (frequencies pwd))))

(defn never-decreases? [pwd]
  (every? (fn [[x y]] (<= (int x) (int y))) (partition 2 1 pwd)))

(defn valid? [freq-pred pwd]
  (and (contains-freq-chars? freq-pred pwd)
       (never-decreases? pwd)))
