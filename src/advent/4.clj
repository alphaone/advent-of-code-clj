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

(defn is-coin? [no-of-zeros secret i]
  (let [adv-coin (str secret i)]
    (.startsWith (md5 adv-coin) (apply str (repeat no-of-zeros "0")))))

(defn advent-coins [no-of-zeros secret]
  (first (filter (partial is-coin? no-of-zeros secret) (range 1e7))))
