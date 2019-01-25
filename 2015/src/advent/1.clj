(ns advent.1)

(defn count-char [ch input]
  (->> (filter #(= ch %) input)
       (count)))

(defn last-floor [input]
  (- (count-char \( input)
     (count-char \) input)))

(defn move [cur-floor ch]
  (case ch
    \( (inc cur-floor)
    \) (dec cur-floor)))

(defn run-stairs [{:keys [i floor in-basement?]} ch]
  (let [new-floor (move floor ch)]
    {:floor        new-floor
     :in-basement? (or (< new-floor 0) in-basement?)
     :i            (if in-basement? i (inc i))}))

(defn floor-on-entering-basement [input]
  (-> (reduce run-stairs {:i 0 :floor 0} input)
      :i))