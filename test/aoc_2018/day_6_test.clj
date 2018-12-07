(ns aoc-2018.day-6-test
  (:require [midje.sweet :refer :all]
            [aoc-2018.input-helper :refer :all]
            [aoc-2018.day-6 :refer :all]))

(def task-1-test-input (read-input-as-strings "inputs/day_6_test_input.txt"))

(def task-1-puzzle-input (read-input-as-strings "inputs/day_6_puzzle_input.txt"))

(facts
  "Aoc Day 6"

  (facts
   "Task 1 Sample data"

   (fact
    "Largest Contained Area"
    (largest-contained-area task-1-test-input) => 17))

  (fact
    "Task 1"

   (prn
    "Result:"
    (largest-contained-area task-1-puzzle-input)))

 (facts
  "Task 2 Sample data"

  (fact
   "Largest Region"
   (largest-region-less-n 32 task-1-test-input) => 16))


 (fact
  "Task 2"

  (prn
   "Result:"
   (largest-region-less-n 10000 task-1-puzzle-input))))
