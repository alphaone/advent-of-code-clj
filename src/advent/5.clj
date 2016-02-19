(ns advent.5)

(defn vowel? [c]
  (some #(= c %) "aeiou"))

(defn three-vowels? [input]
  (->> input
       (filter vowel?)
       count
       (<= 3)))

(defn double-letter [input]
  (not (nil? (re-find #"(\w)\1" input))))

(defn substring? [sub st]
  (not= (.indexOf st sub) -1))

(defn naughty-things [input]
  (or (substring? "ab" input)
      (substring? "cd" input)
      (substring? "pq" input)
      (substring? "xy" input)))

(defn nice? [input]
  (and (three-vowels? input)
       (double-letter input)
       (not (naughty-things input))))

(defn count-nice-words [input]
  (let [words (clojure.string/split input #"\n")]
    (->> words
         (filter nice?)
         (count))))

(defn any-two-letter-twice [input]
  (not (nil? (re-find #"(\w{2,}).*\1" input))))

(defn repeats-with-exactly-one-letter-between [input]
  (not (nil? (re-find #"(\w).\1" input))))

(defn really-nice? [input]
  (and (any-two-letter-twice input)
       (repeats-with-exactly-one-letter-between input)))

(defn count-really-nice-words [input]
  (let [words (clojure.string/split input #"\n")]
    (->> words
         (filter really-nice?)
         (count))))