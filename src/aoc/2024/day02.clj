(ns aoc.2024.day02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-readings []
  (->> (str/split-lines (slurp (io/resource "day02")))
       (mapv #(mapv parse-long (str/split % #"\s+")))))

(defn safe? [nums]
  (let [diffs (map - (rest nums) nums)]
    (and (seq diffs)
         (apply = (map pos? diffs))
         (every? #(<= 1 (abs %) 3) diffs))))

(defn remove-at [xs idx]
  (concat (take idx xs) (drop (inc idx) xs)))

(defn possible-sequences [nums]
  (map (partial remove-at nums)
       (range (count nums))))

(defn dampened-safe? [nums]
  (or (safe? nums)
      (some safe? (possible-sequences nums))))

(defn part-1 [readings]
  (count (filter safe? readings)))

(defn part-2 [readings]
  (count (filter dampened-safe? readings)))

(comment
  (part-1 (parse-readings))
  (part-2 (parse-readings)))
