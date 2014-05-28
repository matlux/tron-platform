# London Clojure Dojo - Tron

A Tron Light Cycle amphitheatre.. the strategy is up to you.  In the future video game battles will be a matter of life or death.

This code was kindly donated by the good folks of http://lambdanext.eu clojure training

Thankyou to Sam Aaron, Christophe Grand, Edmund Jackson, Meikel Brandmeyer

## Install

    git clone https://github.com/matlux/tron-platform.git
    lein run

You should see a graphic windows opening with two colored lines drawing (they  are  the trons or snakes).

## Usage

Implement a function in [bots.clj](https://github.com/matlux/tron-platform/blob/master/src/tron/bots.clj).

### Start a battle in the repl with

    (use '[tron.bots] :reload)
    (start)

### Stop the play

    (tron/stop!)

### Optional

Only needed if you need core fuctions

        (require '[tron.core :as tron])

## License

Distributed under the Eclipse Public License, the same as Clojure.
