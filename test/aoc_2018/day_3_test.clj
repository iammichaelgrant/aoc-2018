(ns aoc-2018.day-3-test
  (:require [midje.sweet :refer :all]
            [aoc-2018.input-helper :refer :all]
            [aoc-2018.day-3 :refer :all]))

(def task-1-test-input (read-input-as-strings "inputs/day_3_test_input.txt"))

(def task-1-puzzle-input (read-input-as-strings "inputs/day_3_puzzle_input.txt"))

(facts
  "Aoc Day 3"

  (facts
    "Task 1 Sample data"

    (fact
      "Overlapping Inches"
      (count-overlapping-inches task-1-test-input) => 4))

  (fact
    "Task 1"

    (prn
      "Result:"
      (count-overlapping-inches task-1-puzzle-input)))

  (facts
    "Task 2 Sample data"

    (fact
      "Unique Claim"
      (find-unique-claim task-1-test-input) => "3"))

  (fact
    "Task 2"

    (prn
      "Result:"
      (find-unique-claim task-1-puzzle-input))))
