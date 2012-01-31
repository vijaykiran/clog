(ns clog.fixtures
  (:use clog.models
        korma.db
        korma.core)
  (:require [clj-yaml.core :as yaml]))

(defn load-fixtures
  "Loads YAML fixtures from 'resources/fixtures.yml' into databse "
  []
  (let [{u :authors p :posts} (yaml/parse-string (slurp "./resources/fixtures.yml"))]
    (insert authors (values u))
    (insert posts (values p))))
