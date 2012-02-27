(ns clog.controller
  (:use clog.templates
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


(defn login
  "Login Handler"
  [req]
  (let [params (:params req)]
    (if (empty? params)
      (response (login-page))
      (if (= (get params "username") (get params "password"))
        (assoc (redirect "/admin") :session {:username (get params "username")})
        (response (login-page "Invalid username or password"))))))

(defn logout
  "Logout handler"
  [req]
  (assoc (redirect "/") :session nil))

(defn admin
  "Admin handler"
  [req]
  (let [username (:username (:session req))
        params (:params req)]
    (if (nil? username)
      (redirect "/login")
      (do
        (if-not (empty? params)
          (let [id (inc (count (select posts)))
                author-id (:id (first (select authors (fields :id) (where {:username username}))))]
            (insert posts (values (assoc params
                                    :id id
                                    :author author-id)))))
        (response (admin-page))))))
