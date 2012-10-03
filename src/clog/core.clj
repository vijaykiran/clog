(ns clog.core
  (:use ring.adapter.jetty
        ring.middleware.resource
        ring.middleware.reload
        ring.middleware.file
        ring.middleware.params
        ring.middleware.session
        ring.middleware.session.cookie
        net.cgrand.moustache
        clog.controller))


;; Routes definition
(def routes
  (app
   (wrap-file "resources/public")
   (wrap-params)
   (wrap-session {:cookie-name "clog-session" :store (cookie-store)})
   ["login"] (delegate login)
   ["logout"] (delegate logout)
   ["admin"] (delegate admin)
   [""] (delegate index)
   [id] (delegate post id)))


;;; start function for starting jetty
(defn start [port]
  (run-jetty #'routes {:port (or port 8080) :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))
