(ns advent.4-test
  (:require [clojure.test :refer :all]
            [advent.4 :as a]))

(deftest advent-coin-test
  (is (= 609043
         (a/advent-coins 5 "abcdef")))
  (is (= 1048970
         (a/advent-coins 5 "pqrstuv")))

  (is (= 282749
         (a/advent-coins 5 "yzbqklnj")))
  
  (is (= 9962624
         (a/advent-coins 6 "yzbqklnj"))))