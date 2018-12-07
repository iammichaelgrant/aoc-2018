(ns aoc-2018.day-7
  (:require [clojure.string :as string]))

(defn parse-record
  [line]
  (let [split (string/split line #" must be finished before step ")]
    {:step (first (second split))
     :dependency [(last (first split))]}))

(defn find-next
  [thing order]

  (->> thing
       (filter #(contains? order (:dependency %)))
       :step
       sort-by
       first)
  )

(defn something
  [dependencies]

  (loop [remaining   dependencies
         order      (sorted-set)]


    (if
      (empty? remaining)
      order

      (let [next  (->> remaining
                       (filter #(contains? order (:dependency %)))
                       :step
                       sort-by
                       first)]

        (recur
          (conj order next)
          (remove #(= (:step %) next) remaining)

          )

        )


      )




    )

  )


(defn order
  [input]

  (->> input
       (map parse-record)


       )
  )
