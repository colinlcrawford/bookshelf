(ns bookshelf.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[bookshelf started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[bookshelf has shut down successfully]=-"))
   :middleware identity})
