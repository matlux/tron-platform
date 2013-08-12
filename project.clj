(defproject tron "0.1.0-SNAPSHOT"
  :description "London Forward Clojure Dojo August 2013 TRON bot"
  :url "https://github.com/christianblunden/ldnclj-2013-aug-fwd-tron"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main tron.bots
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [quil "1.6.0"]
                 [org.jeromq/jeromq "0.2.0"]
                 ;; comment next form if java >= 7
                 [org.codehaus.jsr166-mirror/jsr166y "1.7.0"]
                 ])
