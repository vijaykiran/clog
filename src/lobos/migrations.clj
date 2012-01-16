(comment "This file defines the migrations for the Clog database.
          To run the migrations open the Clojure REPL and run the following code:
          (use 'lobos.core 'lobos.connectivity 'lobos.migration 'lobos.migrations)
          (open-global clogdb)
          (migrate)
          ")
(ns lobos.migrations
  ;; exclude some clojure built-in symbols so we can use the lobos' symbols
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  ;; use only defmigration macro from lobos
  (:use (lobos [migration :only [defmigration]]
          core
          schema)))

;;; Defines the database for lobos migrations
(def clogdb
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "clogdb"
   :user "clog"
   :password "clog"})


