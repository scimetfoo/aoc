(ns aoc.2024.day01
  (:require
   [clojure.java.io :as io]))

(defn transpose [coll]
  (apply map vector coll))

(def lists
  (->> (slurp (io/resource "day01"))
       (re-seq #"\d+")
       (map parse-long)
       (partition 2)
       (transpose)
       (map sort)))

(defn part-1 [[left right]]
  (reduce + (map (comp abs -) left right)))

(defn part-2 [[left right]]
  (let [freqs (frequencies right)]
    (transduce (map (fn [loc] (* loc (freqs loc 0))))
               +
               left)))

(comment (part-1 lists)
         (part-2 lists))
