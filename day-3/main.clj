(ns day-3.main
  (:require [clojure.string :as str]))

(defn bin-to-dec [bin]
  (Integer/parseInt (str/join bin) 2))

(defn rates [report]
  (reduce #(map + %1 %2) (repeat (count (first report)) 0) report))

(defn compare-bits [op report-size]
  (fn [rate] (if (op rate (/ report-size 2)) 1 0)))

(defn gamma [report]
  (mapv (compare-bits >= (count report)) (rates report)))

(defn epsilon [report]
  (mapv (compare-bits < (count report)) (rates report)))

(defn calculate-rating [rate-fn report]
  (reduce
    (fn [report bit]
      (let [rate (rate-fn report)
            bit (mod bit (count rate))
            criteria (rate bit)
            relevant (filter #(= criteria (% bit)) report)]
        (if (= 1 (count relevant))
          (reduced (first relevant))
          relevant))) report (iterate inc 0)))

(defn oxygen-generator-rating [report] (calculate-rating gamma report))
(defn co2-scrubber-rating [report] (calculate-rating epsilon report))

(defn power-consumption [report]
  (* (bin-to-dec (gamma report))
     (bin-to-dec (epsilon report))))

(defn life-support-rating [report]
  (* (bin-to-dec (oxygen-generator-rating report))
     (bin-to-dec (co2-scrubber-rating report))))

(defn run-tests [input]
  (println (= 3687446 (power-consumption input)))
  (println (= 4406844 (life-support-rating input))))

(defn parse-digits [numberish]
  (mapv #(Integer/parseInt %) (str/split numberish #"")))

(defn load-data [file]
  (as-> file v
    (slurp v)
    (str/split v #"\n")
    (map parse-digits v)))

(run-tests (load-data "day-3/input.txt"))
