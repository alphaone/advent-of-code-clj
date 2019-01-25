(ns advent.11)

(defn inc-letter [c]
  (if (= c \z)
    [\a true]
    [(-> c int inc char) false]))

(defn next-password [old-pwd]
  (let [rest (clojure.string/join (butlast old-pwd))
        [c' overflow?] (inc-letter (last old-pwd))
        rest' (if overflow? (next-password rest) rest)]
    (str rest' c')))

(defn- char-range [start end]
  (->> (range (int start) (inc (int end)))
       (map char)))

(def straights
  (->> (char-range \a \z)
       (partition 3 1)
       (map clojure.string/join)))

(defn include-increasing-straight? [input]
  (some? (some (partial clojure.string/includes? input) straights)))

(defn no-forbidden-letters? [input]
  (not (some? (re-find #"i|o|l" input))))

(defn two-non-overlapping-pairs [input]
  (some? (re-find #"(\w)\1.*(\w)\2" input)))

(defn valid? [input]
  (and (no-forbidden-letters? input)
       (include-increasing-straight? input)
       (two-non-overlapping-pairs input)))

(defn next-valid-password [pwd]
  (->> (iterate next-password pwd)
       (rest)
       (filter valid?)
       (first)))
