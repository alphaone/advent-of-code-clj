(ns advent.4)

(import 'java.security.MessageDigest
        'java.math.BigInteger)

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defn coin? [search-pattern secret i]
  (let [advent-coin (str secret i)]
    (clojure.string/starts-with? (md5 advent-coin) search-pattern)))

(defn advent-coins [no-of-zeros secret]
  (let [search-pattern (apply str (repeat no-of-zeros "0"))]
    (->> (filter (partial coin? search-pattern secret) (range 1e7))
         (first))))
