(ns advent.8)

(defn replace-ascii-code [input]
  (clojure.string/replace input #"\\x([0-9a-f]{2})" (fn [[_ m]] (str "\\u00" m))))

(defn mem-length [input]
  (count (read-string (replace-ascii-code input))))

(defn code-length [input]
  (count input))

(defn diff [input]
  (->> (clojure.string/split input #"\n")
       (map (fn [l] (- (code-length l) (mem-length l))))
       (reduce +)))
