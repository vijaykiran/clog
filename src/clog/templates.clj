(ns clog.templates
  (:use net.cgrand.enlive-html))

(deftemplate home-page  "home.html" [posts]
  [:title] (content "Clog - the clojure blog engine!")
  [:div.post] (clone-for [post posts]
                [:span.title] (content (:title post))
                [:div.content (html-content (:content post))]))
