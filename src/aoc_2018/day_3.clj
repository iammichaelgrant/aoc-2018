(ns aoc-2018.day-3
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(defn format-claim
  [claim-string]
  (let [[claim-id
         left
         top
         width
         height] (remove string/blank? (string/split claim-string #"[#|@|,|:|x| ]"))]
    {:claim-id  claim-id
     :left      (read-string left)
     :top       (read-string top)
     :width     (read-string width)
     :height    (read-string height)}))

(defn get-xs
  [claim]
  (range (:left claim)
         (+ (:left claim)
            (:width claim))))

(defn get-ys
  [claim]
  (range (:top claim)
         (+ (:top claim)
            (:height claim))))

(defn string-coord
  [x y]
  (str x y))

(defn get-coords
  [claim]
  (let [xs  (get-xs claim)
        ys  (get-ys claim)]
    (apply
      set/union
      (map
        #(set
          (map
            (fn [x] (str x "x" %))
            xs))
        ys))))

(defn count-overlapping-inches
  [claims]
  (loop [intersection #{}
         union        #{}
         claims       (map
                        #(-> %
                          format-claim
                          get-coords)
                        claims)]
    (if (empty? claims)
        (count intersection)
        (recur
          (set/union intersection (set/intersection union (first claims)))
          (set/union union (first claims))
          (rest claims)))))

(defn coord-map
  [mapping claim]
  (reduce
    #(update %1
      (keyword %2)
      (fn [x] (conj x (:claim-id claim))))
    mapping
    (:coords claim)))

(defn find-unique-claim
  [raw-claims]

  (let [claims-with-coords  (map #(assoc
                                   (format-claim %)
                                   :coords
                                   (get-coords
                                     (format-claim %)))
                                 raw-claims)

        claim-mapping       (reduce
                              coord-map
                              (sorted-map)
                              claims-with-coords)]
    (:claim-id
      (some
        #(when
          (every?
            (fn [x] (= (count (get claim-mapping (keyword x))) 1))
            (:coords %)) %)
        claims-with-coords))))
