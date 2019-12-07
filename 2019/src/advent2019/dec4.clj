(ns advent2019.dec4)

(def find-double-reexp #"(\d)\1" )

(defn contains-double? [pwd]
  (re-find find-double-reexp pwd))

(defn never-decreases? [pwd]
  (every? (fn [[x y]] (<= (int x) (int y))) (partition 2 1 pwd)))

(defn valid? [pwd]
  (and (contains-double? pwd) (never-decreases? pwd)))
