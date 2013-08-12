(ns tron.bots
  (:require [tron.core :as tron]))

; Implement a strategy similar to Buzz!!
(defn team3
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})

(defn team32
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(dec x) y]})

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
    (tron/spawn-biker team32 red)
    (tron/spawn-biker team3 yellow)
    )
  )
