(ns advent2017.dec3)

(defn edge-length->shell-no [x]
  (loop [acc 0
         current x]
    (if (= current 1)
      acc
      (recur (inc acc) (dec (dec current))))))

(defn edge-length [x]
  (let [s   (Math/sqrt x)
        s'  (int (Math/ceil s))
        s'' (if (odd? s') s' (inc s'))]
    s''))

(defn shell-no [x]
  (edge-length->shell-no (edge-length x)))

(defn sqr [a] (* a a))

(defn dist-to-axis [x]
  (let [el  (edge-length x)
        corner (sqr el)
        dist (- corner x)]
    (Math/abs (- (mod dist (dec el)) (/ (dec el) 2))))
  )

(defn route-length [x]
  (+ (shell-no x) (dist-to-axis x)))
