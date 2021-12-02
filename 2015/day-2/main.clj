(ns day-2.main
  (:require [clojure.string :as str]))

(defn wrapping [boxes]
  (reduce
    (fn [total box]
      (let [paper (first total)
            ribbon (second total)
            [l w h] (map #(Integer/parseInt %) (str/split box #"x"))]
        [(+ paper
            (* 2 (+ (* l w) (* w h) (* h l)))
            (min (* l w) (* w h) (* h l)))
         (+ ribbon
            (* 2 (min (+ l w) (+ w h) (+ h l)))
            (* l w h))])) [0 0] boxes))

(defn paper [boxes]
  (first (wrapping boxes)))

(defn ribbon [boxes]
  (second (wrapping boxes)))

(defn run-tests [input]
  (println (= 1588178 (paper input)))
  (println (= 3783758 (ribbon input))))

(defn load-data [file]
  (-> file
      slurp
      (str/trim)
      (str/split #"\n")))

(run-tests (load-data "2015/day-2/input.txt"))
