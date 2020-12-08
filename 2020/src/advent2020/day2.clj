(ns advent2020.day2)

(defn parse-line [line]
  (let [[_ min max char pwd] (re-find #"(\d+)-(\d+) (\w): (\w+)" line)]
    {:min (Integer/parseInt min)
     :max (Integer/parseInt max)
     :char (first char)
     :pwd pwd}))

(defn valid? [{:keys [min max char pwd]}]
  (<= min (get (frequencies pwd) char 0) max))

(defn valid2? [{:keys [min max char pwd]}]
  (let [max-pos-valid? (= char (get pwd (dec max)))]
    (not= (= char (get pwd (dec min))) max-pos-valid?)))

