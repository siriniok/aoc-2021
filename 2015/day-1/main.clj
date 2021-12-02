(ns day-1.main
  (:require [clojure.string :as str]))

(defn count-floors
  [basement-stop? instructions]
  (reduce
    (fn [acc instruction]
      (let [floor (acc 0)
            moves (acc 1)]
        (if (and basement-stop? (= floor -1))
          (reduced acc)
          (case instruction
            "(" [(inc floor) (inc moves)]
            ")" [(dec floor) (inc moves)]))))
    [0 0] instructions))

(defn end-floor [instructions]
  ((count-floors false instructions) 0))

(defn first-time-basement [instructions]
  ((count-floors true instructions) 1))

(defn run-tests [input]
  (println (= 232 (end-floor input)))
  (println (= 1783 (first-time-basement input))))

(defn load-data [file]
  (-> file
      slurp
      (str/trim)
      (str/split #"")))

(run-tests (load-data "2015/day-1/input.txt"))
