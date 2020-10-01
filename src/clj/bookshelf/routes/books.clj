(ns bookshelf.routes.books
  (:require
   [bookshelf.layout :as layout]
   [bookshelf.db.core :as db]
   [bookshelf.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(def books
  [{:id 1 :title "book 1" :author "author 1"}
   {:id 2 :title "book 2" :author "author 2"}
   {:id 3 :title "book 3" :author "author 3"}])

(defn index [request]
  (layout/render request "books/index.html" {:books books}))

(defn show [{:keys [path-params] :as request}]
  (let [id-param (:id path-params)
        id (try
             (Integer/parseInt id-param)
             (catch Exception e nil))]
    (if (nil? id)
      (layout/error-page {:status 404, :title "404 - Book not found"})
      (layout/render request "books/show.html" {:book {:id id}}))))

(defn new [request]
  (layout/render request "books/new.html"))

(defn book-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/books" {:get index}]
   ["/books/new" {:get new}]
   ["/books/:id" {:get show}]

   ])

