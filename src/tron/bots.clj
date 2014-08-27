(ns tron.bots
  (:require [tron.core :as tron])
  (:require [tron.bot-mat :as mat])
  (:require [tron.bot-greg :as greg])
  (:require [tron.bots-rob :as rob]))

; Implement a strategy similar to Buzz!!
(defn buzz
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})


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
    (tron/spawn-biker mat/bot yellow)
    (tron/spawn-biker mat/bot2 red)
    (tron/spawn-biker greg/south-walker green)
    (tron/spawn-biker greg/turner orange)
    (tron/spawn-biker greg/smart-turner blue)
    (tron/spawn-biker rob/rob-rider purple)   
    )
  )

(defn -main []
  (start))
