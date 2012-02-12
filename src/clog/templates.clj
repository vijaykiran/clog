(ns clog.templates
  (:use net.cgrand.enlive-html))

(deftemplate home-page  "home.html" [posts]
  [:title] (content "Clog - the clojure blog engine!")
  [:div.post] (clone-for [post posts]
                [:a.title] (do->
                             (set-attr :href (str "/" (:id post)))
                             (content (:title post)))
                [:div.content (html-content (:content post))]))

(deftemplate post-page "post.html" [post]
  [:title] (content (str "Clog - " (:title post)))
  [:span.title] (content (:title post))
  [:div.content] (html-content (:content post)))
