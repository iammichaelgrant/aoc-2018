(ns aoc-2018.day-5-test
  (:require [midje.sweet :refer :all]
            [aoc-2018.input-helper :refer :all]
            [aoc-2018.day-5 :refer :all]))

(def task-1-test-input (first (read-input-as-strings "inputs/day_5_test_input.txt")))
(def task-1-puzzle-input (first (read-input-as-strings "inputs/day_5_puzzle_input.txt")))

(facts
  "Aoc Day 5"

  (facts
    "Task 1 Sample data"

    (fact
      "Reacting"
      (react-polymer  task-1-test-input nil) => 10))

  (fact
    "Task 1"

    (prn
      "Result:"
      (react-polymer  task-1-puzzle-input nil)))

  (facts
    "Task 2 Sample data"

    (fact
      "Reacting "
      (react-polymer-without-problematic
        task-1-test-input) => 4)

    (fact
      "Task 2"

      (prn
        "Result:"
        (react-polymer-without-problematic
          task-1-puzzle-input)))))
