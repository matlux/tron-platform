(ns tron.bots
  (:require [tron.core :as tron]))

; Implement a strategy similar to Buzz!!
(defn buzz
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})

(defn new-pos [dir [x y]]
  (cond
   (= dir :east) [(inc x) y]
   (= dir :west) [(dec x) y]
   (= dir :north) [x (dec y)]
   (= dir :south) [x (inc y)]
   :true [x y]))

(defn safe-pos? [look position]
  (not (look position)))

(def look-fn (atom nil))

(defn turner
  "I'll just turn around"
  [look {[x y] :pos}]
  (reset! look-fn look)
(let [dirs [:east :west :north :south]
        move-options (map new-pos dirs (cycle [[x y]]))
        new-pos (first
                 (filter #(safe-pos? look %)
                         move-options))]
    {:pos
     new-pos}))

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
    )
  )

(defn -main []
  (start))
