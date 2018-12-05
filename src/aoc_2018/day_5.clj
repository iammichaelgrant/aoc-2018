(ns aoc-2018.day-5
  (:require [clojure.string :as string]))

(defn equal-letter-opposite-polarity
  [first second]
  (and
    first
    second
    (not= first second)
    (= (string/upper-case first) (string/upper-case second))))

(defn trigger-units
  [polymer]
  (loop [result []
         chain  polymer]

    (cond
      (empty? chain)
      result

      (equal-letter-opposite-polarity
        (first chain) (second chain))
      (recur
        result
        (rest (rest chain)))

      :else
      (recur
        (conj result (first chain))
        (rest chain)))))

(defn react
  [polymer]

  (loop [previous []
         next     polymer]

    (if
      (= (count previous) (count next))
      next
      (recur
        next
        (trigger-units next)))))

(defn react-polymer
  [polymer unit]

  (->>
    polymer
    (remove
      #(=
        (string/lower-case %)
        (when unit (string/lower-case unit))))
    react
    count))

(defn react-polymer-without-problematic
  [polymer]

  (let [unit "abcdefghijklmnopqrstuvwxyz"]

    (->>
      unit
      (map #(react-polymer polymer %))
      sort
      first)))
