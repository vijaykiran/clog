(ns clog.controller
  (:use  clog.templates
         clog.models
         ring.util.response
         korma.core))

(defn index
  "Index page handler"
  [req]
  (->> (select posts) home-page response))
