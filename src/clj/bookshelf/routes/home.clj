(ns bookshelf.routes.home
  (:require
   [bookshelf.layout :as layout]
   [bookshelf.db.core :as db]
   [bookshelf.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]])

