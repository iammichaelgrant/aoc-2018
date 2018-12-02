(ns aoc-2018.day-2
  (:require [clojure.string :as string]))

(defn appears-n
  [input-string n]
  (loop [char-counts    {}
         current-string input-string]
    (if (empty? current-string)
      (contains? (set (vals char-counts)) n)
      (recur
        (update
          char-counts
          (first current-string)
          #(if (number? %) (inc %) 1))
        (rest current-string)))))

(defn find-checksum
  [ids]
  (let [twice-counts  (map #(appears-n % 2) ids)
        twice-total   (count (filter true? twice-counts))
        thrice-counts (map #(appears-n % 3) ids)
        thrice-total  (count (filter true? thrice-counts))]
    (* twice-total thrice-total)))


(defn compare-strings
  [string-1 string-2]
  (loop [chars-1        (string/split string-1 #"")
         chars-2        (string/split string-2 #"")
         matching-chars []]
    (or
      (when
        (and
          (empty? chars-1)
          (empty? chars-2))
        matching-chars)
      (recur
        (rest chars-1)
        (rest chars-2)
        (conj
          matching-chars
          (when (= (first chars-1) (first chars-2))
            (first chars-1)))))))

(defn some-with-n-differences
  [strings n]
  (loop [current-strings  strings
         comparisons      []]
    (or
      (some #(when (= n (count (filter nil? %))) %) comparisons)
      (recur
        (rest current-strings)
        (map #(compare-strings (first current-strings) %) strings)))))

(defn find-common-letters
  [input-strings]
  (string/join (keep identity (some-with-n-differences input-strings 1))))
