(ns aoc-2018.day-7-test
  (:require [midje.sweet :refer :all]
            [aoc-2018.input-helper :refer :all]
            [aoc-2018.day-7 :refer :all]))

(def task-1-test-input (read-input-as-strings "inputs/day_7_test_input.txt"))

(def task-1-puzzle-input (read-input-as-strings "inputs/day_7_puzzle_input.txt"))

(facts
  "Aoc Day 7"

  (facts
   "Task 1 Sample data"

   (fact
    "Largest Contained Area"
    (order task-1-test-input) => "CABDFE"))

  (fact
    "Task 1"

   (prn
    "Result:"
    (order task-1-test-input)))

 )
