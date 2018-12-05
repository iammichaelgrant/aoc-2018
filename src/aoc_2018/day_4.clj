(ns aoc-2018.day-4
  (:require [clojure.string :as string]))

(defn parse-record
  [line]
  (let [split         (string/split line #"[\-|\]|\[| |:]")

        month         (nth split 2)
        day           (nth split 3)
        date          (str month "-" day)

        hour          (Integer/parseInt (nth split 4))
        minute        (Integer/parseInt (nth split 5))

        guard-number  (second (string/split (nth split 8) #"#"))
        begins-shift  (= (nth split 7) "Guard")
        wakes-up      (= (nth split 7) "wakes")
        falls-asleep  (= (nth split 7) "falls")]
    {:month month
     :day   day
     :hour hour
     :minute minute
     :begins-shift begins-shift
     :guard-number guard-number
     :wakes-up wakes-up
     :falls-asleep falls-asleep}))

(defn enhance-records
  [records]
  (loop [updated-items  []
         previous       nil
         current        records]
    (cond
      (empty? current)        updated-items

      (:begins-shift (first current)) (recur
                                        (conj updated-items (first current))
                                        (first current)
                                        (rest current))

      (:falls-asleep (first current)) (recur
                                        (conj updated-items (assoc
                                                             (first current)
                                                             :guard-number
                                                             (:guard-number previous)))
                                        (assoc
                                         (first current)
                                         :guard-number
                                         (:guard-number previous))
                                        (rest current))

      (:wakes-up (first current))     (recur
                                        (conj updated-items (assoc
                                                             (first current)
                                                             :guard-number
                                                              (:guard-number previous)
                                                             :minutes-asleep
                                                              (- (:minute (first current))
                                                                 (:minute previous))
                                                             :asleep
                                                              (range
                                                               (:minute previous)
                                                               (:minute (first current)))))
                                        (assoc
                                         (first current)
                                         :guard-number
                                          (:guard-number previous)
                                         :minutes-asleep
                                          (- (:minute (first current))
                                             (:minute previous))
                                         :asleep
                                          (range
                                           (:minute previous)
                                           (:minute (first current))))
                                        (rest current)))))

(defn count-minutes-asleep
  [records]

  (reduce
   (fn [result next]
     (if
       (:wakes-up next)
       (update
        result
        (:guard-number next)
        #(if %
          (assoc
           %
           :total (+ (:total %) (:minutes-asleep next))
           :minutes (concat (:minutes %) (:asleep next)))
          {:total (:minutes-asleep next)
           :minutes (:asleep next)}))
       result))
   {}
   records))

(defn frequencies
  [entry]

  (loop [minutes      (:minutes (second entry))
         frequencies  {}]
    (if (empty? minutes)
      {:guard-number      (first entry)
       :most-asleep       (first (last (sort-by second frequencies)))
       :most-asleep-count (second (last (sort-by second frequencies)))}
      (recur
        (rest minutes)
        (update
         frequencies
         (first minutes)
         #(if % (inc %) 1))))))

(defn calc-result
  [item]
  (* (Integer/parseInt (:guard-number item)) (:most-asleep item)))

(defn strategy-1
  [raw-records]

  (->>
   raw-records
   sort
   (map parse-record)
   enhance-records
   count-minutes-asleep
   (sort-by #(:total (second %)))
   last
   frequencies
   calc-result))

(defn strategy-2
  [raw-records]

  (->>
   raw-records
   sort
   (map parse-record)
   enhance-records
   count-minutes-asleep
   (map frequencies)
   (sort-by :most-asleep-count)
   last
   calc-result))
