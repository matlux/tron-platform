(ns tron.bots
  (:require [tron.core :as tron]))

; Implement a strategy similar to Buzz!!

(def turn-right
  {[0 1] [1 0]
   [1 0] [0 -1]
   [0 -1] [-1 0]
   [-1 0] [0 1]})

(def turn-left
  (into {} (for [[key val] turn-right] [val key])))

(defn turn
  [look {pos :pos dir :dir}]
  (let [dir (or dir [0 1])
        dirs (take 4 (iterate turn-right dir))
        free-dirs (filter #(nil? (look (map + pos %))) dirs)
        new-dir (first (sort free-dirs))]
    (if new-dir
      {:pos (map + pos new-dir) :dir new-dir}
      (println "goodbye cruel world..."))))

(defn team3
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})
(defn buzz2
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})

(defn getforward [dir]
  (dir {:e (fn [[x y]] [(inc x) y])
        :n (fn [[x y]] [x (dec y)])
        :w (fn [[x y]] [(dec x) y])
        :s (fn [[x y]] [x (inc y)])
        }))

(defn left [dir]
  (dir {:e :n
    :n :w
    :w :s
        :s :e}))
(defn rigth [dir]
  (dir {:e :s
    :n :e
    :w :n
    :s :w}))

(defn wall? [dir [x y] look]
  (look ((getforward dir) [x y])))

(defn newpos [dir [x y] look]
  (if (wall? dir [x y] look)
    (let [newdir (left dir)]
      {:pos ((getforward newdir) [x y]) :dir newdir})
    {:pos ((getforward dir) [x y]) :dir dir})
  )

(defn team32
  "To infinity and beyond!"
  [look {[x y] :pos dir :dir}]
  (if dir
    (newpos dir [x y] look)
    (let [dir :e]
      (newpos dir [x y] look))))

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
    (tron/spawn-biker buzz2 yellow)
    (tron/spawn-biker team32 red)
    (tron/spawn-biker turn yellow)    
    )
  )

(defn -main []
  (start))


