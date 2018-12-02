(ns aoc-2018.day-2-test
  (:require [midje.sweet :refer :all]
            [aoc-2018.input-helper :refer :all]
            [aoc-2018.day-2 :refer :all]))

(def task-1-test-input-1 (read-input-as-strings "inputs/day_2_test_input_1.txt"))
(def task-1-test-input-2 (read-input-as-strings "inputs/day_2_test_input_2.txt"))

(def task-1-puzzle-input (read-input-as-strings "inputs/day_2_puzzle_input.txt"))

(facts
  "Aoc Day 2"

  (facts
    "Task 1 Sample data"

    (fact
      "1"
      (appears-n (nth task-1-test-input-1 0) 2) => false
      (appears-n (nth task-1-test-input-1 0) 3) => false)

    (fact
      "2"
      (appears-n (nth task-1-test-input-1 1) 2) => true
      (appears-n (nth task-1-test-input-1 1) 3) => true)

    (fact
      "3"
      (appears-n (nth task-1-test-input-1 2) 2) => true
      (appears-n (nth task-1-test-input-1 2) 3) => false)

    (fact
      "4"
      (appears-n (nth task-1-test-input-1 3) 2) => false
      (appears-n (nth task-1-test-input-1 3) 3) => true)

    (fact
      "5"
      (appears-n (nth task-1-test-input-1 4) 2) => true
      (appears-n (nth task-1-test-input-1 4) 3) => false)

    (fact
      "6"
      (appears-n (nth task-1-test-input-1 5) 2) => true
      (appears-n (nth task-1-test-input-1 5) 3) => false)

    (fact
      "7"
      (appears-n (nth task-1-test-input-1 6) 2) => false
      (appears-n (nth task-1-test-input-1 6) 3) => true)

    (fact
      "Checksum"
      (find-checksum task-1-test-input-1) => 12))

  (fact
    "Task 1"

    (prn
      "Result:"
      (find-checksum task-1-puzzle-input)))


  (facts
    "Task 2 Sample data"

    (fact
      "Commons letters"
      (find-common-letters task-1-test-input-2) => "fgij"))

  (fact
    "Task 2"

    (prn
      "Result:"
      (find-common-letters task-1-puzzle-input)))

  )

