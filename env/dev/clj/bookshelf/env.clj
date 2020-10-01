(ns bookshelf.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [bookshelf.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[bookshelf started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[bookshelf has shut down successfully]=-"))
   :middleware wrap-dev})
