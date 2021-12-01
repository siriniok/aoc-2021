(ns day-1.main
  (:require [clojure.string :as str]))

(def sum (partial reduce +))

(defn count-depth-increases
  ([sweep-report] (count-depth-increases 1 sweep-report))
  ([window-size sweep-report]
   (reduce
     (fn [count v]
       (if (> (sum (take-last window-size v)) (sum (take window-size v)))
         (inc count)
         count))
     0 (partition (inc window-size) 1 sweep-report))))

(defn run-tests [input]
  (println (= 1233 (count-depth-increases input)))
  (println (= 1275 (count-depth-increases 3 input))))

(defn load-data [file]
  (as-> file v
    (slurp v)
    (str/split v #"\s")
    (map #(Integer/parseInt %) v)))

(run-tests (load-data "day-1/input.txt"))
