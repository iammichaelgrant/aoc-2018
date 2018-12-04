(ns aoc-2018.day-4
  (:require [clojure.string :as string]))

(defn parse-line
  [line]

  (let [ split-1  (string/split line #" ")

         month    (nth (string/split split-1 #"-") 1)
         day      (nth (string/split split-1 #"-") 2)
         date     (str month "-" day)

         hour     (nth (string/split split-1 #":") 0)
         minute   (nth (string/split split-1 #":") 1)



         ]


    )

  )

(defn format-records
  [raw-records]

  (reduce
    #(update {} )
    {}
    raw-records)

  []
  )


(defn strategy-1
  [records]

  records
  )