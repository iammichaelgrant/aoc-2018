(ns aoc-2018.input-helper
  (:require [clojure.string :as string]))

(defn read-input-as-strings
  [file-name]
  (string/split-lines (slurp file-name)))
