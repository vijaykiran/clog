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

(defmigration add-users-table
  ;; code be executed when migrating the schema "up" using "migrate"
  (up [] (create clogdb
           (table :users (integer :id :primary-key )
             (varchar :username 100 :unique )
             (varchar :password 100 :not-null )
             (varchar :email 255))))
  ;; Code to be executed when migrating schema "down" using "rollback"
  (down [] (drop (table :users ))))

(defmigration add-posts-table
  (up [] (create clogdb
           (table :posts (integer :id :primary-key )
             (varchar :title 250)
             (text :content )
             (boolean :status (default false))
             (timestamp :created (default (now)))
             (timestamp :published )
             (integer :user [:refer :users :id] :not-null))))
  (down [] (drop (table :posts ))))

