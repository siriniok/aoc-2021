(ns day-2.main
  (:require [clojure.string :as str]))

(defn strategy
  ([dir x y u]
   (case dir
     :forward [(+ x u) y 0]
     :down    [x (+ y u) 0]
     :up      [x (- y u) 0]))
  ([dir x y aim u]
   (case dir
     :forward [(+ x u) (+ y (* u aim)) aim]
     :down    [x y (+ aim u)]
     :up      [x y (- aim u)])))

(defn position [with-aim course]
  (reduce
    (fn [pos command]
      (let [x (pos 0)
            y (pos 1)
            aim (pos 2)
            commandv (str/split command #"\s")
            dir (keyword (commandv 0))
            u (Integer/parseInt (commandv 1))]
        (if with-aim
          (strategy dir x y aim u)
          (strategy dir x y u)))) [0 0 0] course))

(defn scalar-position [position]
  (* (position 0) (position 1)))

(defn run-tests [input]
  (println (= 1990000 (->> input (position false) scalar-position)))
  (println (= 1975421260 (->> input (position true) scalar-position))))

(defn load-data [file]
  (-> file
    slurp
    (str/split #"\n")))

(run-tests (load-data "day-2/input.txt"))
