(ns aoc-2018.day-6
  (:require [clojure.string :as string]))

(defn parse-record
  [line]
  (let [split (string/split line #", ")]
    {:x (Integer/parseInt (first split))
     :y (Integer/parseInt (second split))}))

(defn difference
  [a b]
  (if (> a b) (- a b) (- b a)))

(defn keep-closest [list]
  (when
    (not= (:distance (first list))
          (:distance (second list)))
    (first list)))

(defn add-distances
  [places]
  (->> places
       :distance
       (map :distance)
       (reduce +)))

(defn enhance-grid
  [xs ys places]
  (map
   (fn [x]
     (map
      (fn [y]
        {:x x
         :y y
         :edge (or
                (= x (first xs))
                (= x (last xs))
                (= y (first ys))
                (= y (first ys)))
         :distance (->> places
                        (map (fn [place]
                               {:place place
                                :distance (+ (difference x (:x place))
                                             (difference y (:y place)))})))
         :closest (->> places
                        (map (fn [place]
                               {:place place
                                :distance (+ (difference x (:x place))
                                             (difference y (:y place)))}))
                        (sort-by :distance)
                        keep-closest)})
      ys))
   xs))

(defn create-grid
  [locations]
  (let [lowest-x  (:x (first  (sort-by :x locations)))
        highest-x (:x (last   (sort-by :x locations)))
        lowest-y  (:y (first  (sort-by :y locations)))
        highest-y (:y (last   (sort-by :y locations)))]

    (apply
     concat
     (enhance-grid
      (range (dec lowest-x) (inc (inc highest-x)))
      (range (dec lowest-y) (inc (inc highest-y)))
      locations))))

(defn largest-contained-area
  [input]
  (let [grid      (create-grid (map parse-record input))
        infinites (set (map #(-> % :closest :place) (filter :edge grid)))]
    (->> grid
         (keep :closest)
         (remove :edge)
         (remove #(contains? infinites (:place %)))
         (map :place)
         frequencies
         (sort-by second)
         last
         last)))

(defn largest-region-less-n
  [n input]
  (let [grid (create-grid (map parse-record input))]
    (->> (create-grid (map parse-record input))
         (filter #(> n (add-distances %)))
         count)))
