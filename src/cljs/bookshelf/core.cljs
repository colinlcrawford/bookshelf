(ns bookshelf.core)

(defn ^:dev/after-load mount-components []
  (println "cljs loaded!"))

(defn init! []
  (mount-components))
