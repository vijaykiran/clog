(ns clog.controller
  (:use  clog.templates
         clog.models
         ring.util.response
         korma.core))

(defn index
  "Index page handler"
  [req]
  (->> (select posts) home-page response))

(defn post
  "Post details page handler"
  [req id]
  (let [postId (Integer/parseInt id)]
    (->> (first (select posts (where {:id postId})))
      post-page response)))
