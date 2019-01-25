(ns advent.5)

(defn vowel? [ch]
  (some #(= ch %) "aeiou"))

(defn three-vowels? [input]
  (->> (filter vowel? input)
       count
       (<= 3)))

(defn double-letter [input]
  (some? (re-find #"(\w)\1" input)))

(defn naughty-things [input]
  (or (clojure.string/includes? input "ab")
      (clojure.string/includes? input "cd")
      (clojure.string/includes? input "pq")
      (clojure.string/includes? input "xy")))

(defn nice? [input]
  (and (three-vowels? input)
       (double-letter input)
       (not (naughty-things input))))

(defn count-nice-words [input]
  (->> (clojure.string/split input #"\n")
       (filter nice?)
       count))


(defn any-two-letter-twice [input]
  (some? (re-find #"(\w{2,}).*\1" input)))

(defn repeats-with-exactly-one-letter-between [input]
  (some? (re-find #"(\w).\1" input)))

(defn really-nice? [input]
  (and (any-two-letter-twice input)
       (repeats-with-exactly-one-letter-between input)))

(defn count-really-nice-words [input]
  (->> (clojure.string/split input #"\n")
       (filter really-nice?)
       count))
