(ns clog.core
  (:use ring.adapter.jetty
        ring.middleware.resource
        ring.util.response
        net.cgrand.moustache))

;;; A simple handler to show send some response to the client.
(defn index
  [req]
  (response "Welcome, to Clog - A Blog Engine written in Clojure"))

;; Routes definition
(def routes
  (app
    [""] index))

;;; start function for starting jetty
(defn start [port]
  (run-jetty #'routes {:port (or port 8080) :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))
