(ns aoc-2018.day-3
  (:require [clojure.string :as string]))


(defn extract-details
  [claim]

  (let [[claim-id
         left
         top
         width
         height] (remove  string/blank?  (string/split claim #"[#|@|,|:|x| ]"))]

    {:claim-id claim-id
     :left left
     :top top
     :width width
     :height height}))

(defn count-overlapping-inches
  [claims]

  (map extract-details claims)

  )