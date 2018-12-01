(ns aoc-2018.day-1)

(defn find-frequency
  [numbers]
  (reduce + numbers))


(defn find-repeat-frequency
  [numbers]

  (loop [found-frequencies  #{}
         current-frequency  0
         cycled-numbers     (cycle numbers)]
    (if
      (contains? found-frequencies current-frequency)
      current-frequency
      (recur
        (conj found-frequencies current-frequency)
        (+ current-frequency (first cycled-numbers))
        (rest cycled-numbers)))))
