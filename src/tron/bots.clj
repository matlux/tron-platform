(ns tron.bots
  (:require [tron.core :as tron]))

; Implement a strategy similar to Buzz!!
(defn buzz
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})

(defn new-pos
  ([dir old-pos] (new-pos dir old-pos 1))
  ([dir [x y] distance]
      (cond
       (= dir :east) [(+ x distance) y]
       (= dir :west) [(- x distance) y]
       (= dir :north) [x (- y distance)]
       (= dir :south) [x (+ y distance)]
       :true [x y])))

(defn safe-pos? [look position]
  (not (look position)))

(def look-fn (atom nil))

(defn get-dirs []
  [:east :west :north :south])

(defn turner
  "I'll just turn around"
  [look {[x y] :pos}]
  ;(reset! look-fn look)
  (let [dirs (get-dirs)
        move-options (map new-pos dirs (cycle [[x y]]))
        new-pos (first
                 (filter #(safe-pos? look %)
                         move-options))]
    {:pos new-pos}))

(defn order-dirs [dir]
  (if (or (= dir :south) (= dir :north))
    [:east :west :north :south]
    [:north :south :east :west]))

(defn smart-turner [look {[x y] :pos dir :dir :or {dir :east}}]
  (let [dirs (order-dirs dir)
        move-options (map (fn [d p] [d (new-pos d p)]) dirs (cycle [[x y]]))
        [new-dir new-pos] (first
                           (filter (fn [[d p]] (safe-pos? look p))
                                   move-options))]
    {:pos new-pos
     :dir new-dir}))


;;next recursive walker, looks few steps ahead
;; makes one decision and then re-evaluates based on outcome of
;; move from this future step
(defn farseer
  [look {old-pos :pos}]
  (let [dirs (get-dirs)
        dist [1 5]
        options (filter second
                        (for [dir dirs
                              d dist]
                          [dir (safe-pos? look
                                          (new-pos dir old-pos d))]))
        [best-dir _] (last
                      (sort-by second
                               (map (fn [[k v]] [k (count v)])
                                    (group-by first options))))]

    {:pos (new-pos best-dir old-pos)}))

(defn get-cross-dirs [dir]
  (if (or (= dir :south) (= dir :north))
    [:east :west]
    [:north :south]))

(defn south-walker [look {[x y] :pos}]
  {:pos [x (inc y)]})

; Choose a TEAM colour
(def red 1)
(def orange 25)
(def yellow 50)
(def green 100)
(def blue 150)
(def purple 200)

; Start the battle
;(tron/spawn-biker buzz red)

; Stop the battle
;(tron/stop!)

; Reset the arena
;(tron/blank-arena)


(defn start []
  (do
    (tron/stop!)
    (tron/blank-arena)
    (tron/spawn-biker buzz red)
    (tron/spawn-biker south-walker green)
    (tron/spawn-biker turner orange)
    (tron/spawn-biker smart-turner blue)
    )
  )

(defn -main []
  (start))
