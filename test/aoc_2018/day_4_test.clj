(ns aoc-2018.day-4-test
  (:require [midje.sweet :refer :all]
            [aoc-2018.input-helper :refer :all]
            [aoc-2018.day-4 :refer :all]))

(def task-1-test-input (read-input-as-strings "inputs/day_4_test_input.txt"))

(def task-1-puzzle-input (read-input-as-strings "inputs/day_4_puzzle_input.txt"))

(facts
  "Aoc Day 4"

  (facts
   "Task 1 Sample data"

   (fact
    "Stategy 1 output"
    (-> task-1-test-input
        strategy-1) => 240))

  (fact
    "Task 1"

   (prn
    "Result:"
    (-> task-1-puzzle-input
        strategy-1)))

 (facts
  "Task 2 Sample data"

  (fact
   "Stategy 2 output"
   (-> task-1-test-input
       strategy-2) => 4455))

 (fact
  "Task 2"

  (prn
   "Result:"
   (-> task-1-puzzle-input
       strategy-2))))

