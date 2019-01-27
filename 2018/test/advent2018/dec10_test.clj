(ns advent2018.dec10-test
  (:require [clojure.test :refer :all]
            [advent2018.dec10 :as a]
            [clojure.string :as cs]
            [clojure.java.io :as io]
            [quil.core :as q]))

(def sample
  "position=< 9,  1> velocity=< 0,  2>
  position=< 7,  0> velocity=<-1,  0>
  position=< 3, -2> velocity=<-1,  1>
  position=< 6, 10> velocity=<-2, -1>
  position=< 2, -4> velocity=< 2,  2>
  position=<-6, 10> velocity=< 2, -2>
  position=< 1,  8> velocity=< 1, -1>
  position=< 1,  7> velocity=< 1,  0>
  position=<-3, 11> velocity=< 1, -2>
  position=< 7,  6> velocity=<-1, -1>
  position=<-2,  3> velocity=< 1,  0>
  position=<-4,  3> velocity=< 2,  0>
  position=<10, -3> velocity=<-1,  1>
  position=< 5, 11> velocity=< 1, -2>
  position=< 4,  7> velocity=< 0, -1>
  position=< 8, -2> velocity=< 0,  1>
  position=<15,  0> velocity=<-2,  0>
  position=< 1,  6> velocity=< 1,  0>
  position=< 8,  9> velocity=< 0, -1>
  position=< 3,  3> velocity=<-1,  1>
  position=< 0,  5> velocity=< 0, -1>
  position=<-2,  2> velocity=< 2,  0>
  position=< 5, -2> velocity=< 1,  2>
  position=< 1,  4> velocity=< 2,  1>
  position=<-2,  7> velocity=< 2, -2>
  position=< 3,  6> velocity=<-1, -1>
  position=< 5,  0> velocity=< 1,  0>
  position=<-6,  0> velocity=< 2,  0>
  position=< 5,  9> velocity=< 1, -2>
  position=<14,  7> velocity=<-2,  0>
  position=<-3,  6> velocity=< 2, -1>")

(def sample-dots
  (->> sample
       (cs/split-lines)
       (map a/parse)))

(def dots
  (->> "dec10.txt"
       (io/resource)
       (slurp)
       (cs/split-lines)
       (map a/parse)))

(deftest parse-test
  (is (= [[[9 1] [0 2]]
          [[7 0] [-1 0]]
          [[3 -2] [-1 1]]]
         (take 3 sample-dots))))

(deftest fly-test
  (is (= [[[9 3] [0 2]]
          [[6 0] [-1 0]]
          [[2 -1] [-1 1]]]
         (take 3 (a/fly sample-dots)))))

(deftest scale
  (is (= -10 (a/scale -50000 50000 -10 1000 -50000)))
  (is (= 1000 (a/scale -50000 50000 -10 1000 50000))))

(defn setup []
  (q/smooth)
  (q/frame-rate 5))

(comment
  (deftest visulize-a-test
    (let [states (atom (drop 10600 (reductions (fn [acc _] (a/fly acc)) dots (range))))]
      (q/defsketch testsketch
        :renderer :opengl
        :setup setup
        :size [1400 1000]
        :draw #(a/draw-reductions states))
      )))

(comment
  (deftest solve-a-b-test
    (let [dots (first (drop 10656 (reductions (fn [acc _] (a/fly acc)) dots (range))))]
      (q/defsketch testsketch
        :renderer :opengl
        :setup setup
        :size [1400 1000]
        :draw #(a/draw-dots dots))
      )
    ; XLZAKBGZ
    ; 10656
    ))