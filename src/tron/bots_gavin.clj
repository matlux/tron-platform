(ns gavin.bots
  (:require [tron.core :as tron]))

(defn goWest [[x y]]
     [(inc x) y]
 )

(defn goEast [[x y]]
     [(dec x) y]
 )

(defn goNorth [[x y]]
     [x (dec y)]
 )

(defn goSouth [[x y]]
     [x (inc y)]
 )

; Implement a strategy similar to Buzz!!
(defn buzz
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos (goWest [x y])})

(defn buzz2
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})
 
(defn free? [look pos]
   (not(look pos))
 )
  
(defn dude 
  "white russian"
  [look {pos :pos}]
 
  {:pos (cond
          (free? look (goWest pos)) (goWest pos)
          (free? look (goSouth pos)) (goSouth pos)
          (free? look (goNorth pos)) (goNorth pos)
          :else (goEast pos)
        )
   }
)

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
    (tron/spawn-biker dude purple)
    (tron/spawn-biker dude blue)
    (tron/spawn-biker buzz2 yellow)
    )
  )

(defn -main []
  (start))


