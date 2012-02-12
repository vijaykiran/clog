(ns clog.core
  (:use ring.adapter.jetty
        ring.middleware.resource
        ring.middleware.reload
        ring.util.response
        ring.middleware.file
        ring.middleware.params
        net.cgrand.moustache
        clog.controller))

;; Routes definition
(def routes
  (app
   (wrap-params)
   (wrap-file "resources/public")
    ["login"]  (delegate login)
    [""] (delegate index)
    [id] (delegate post id)))


;;; start function for starting jetty
(defn start [port]
  (run-jetty #'routes {:port (or port 8080) :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))
