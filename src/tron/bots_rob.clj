(ns tron.bots-rob
  (:require [tron.core :as tron]))

(defn isSafe [look pos]
  ;(println "is safe: " pos)
  (not (look pos)))

(defn go-west [[x y]]
  [(dec x) y]
  )

(defn go-east [[x y]]
  [(inc x) y]
  )

 (defn go-north [[x y]]
  [x (dec y)]
  )

 (defn go-south [[x y]]
  [x (inc y)]
  )

(defn rob-rider [look {pos :pos}]
  ;(println "!!!!!!!!!!!!!!" pos)
  (let [new-pos (cond 
      (isSafe look (go-west pos)) (go-west pos)
      (isSafe look (go-east pos)) (go-east pos)
      (isSafe look (go-north pos)) (go-north pos)
      (isSafe look (go-south pos)) (go-south pos)
      :default [3 3]
    )]
  ;(println "new pos " new-pos)
  {:pos new-pos}
  ))


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
    (tron/spawn-biker buzz red)
    ;(tron/spawn-biker buzz2 yellow)
    ;(tron/spawn-biker rob purple)
    (tron/spawn-biker rob-rider green)
    )
  )

(defn -main []
  (start))

(defn stop []
  (do
    (tron/stop!)
    )
  )
