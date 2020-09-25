(defproject bookshelf "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[buddy/buddy-auth "2.2.0"]
                 [buddy/buddy-core "1.6.0"]
                 [buddy/buddy-hashers "1.4.0"]
                 [buddy/buddy-sign "3.1.0"]
                 [cheshire "5.10.0"]
                 [clojure.java-time "0.3.2"]
                 [com.cognitect/transit-clj "1.0.324"]
                 [com.google.javascript/closure-compiler-unshaded "v20191027" :scope "provided"]
                 [conman "0.8.6"]
                 [cprop "0.1.16"]
                 [expound "0.8.4"]
                 [funcool/struct "1.4.0"]
                 [luminus-immutant "0.2.5"]
                 [luminus-migrations "0.6.7"]
                 [luminus-transit "0.1.2"]
                 [markdown-clj "1.10.2"]
                 [metosin/muuntaja "0.6.6"]
                 [metosin/reitit "0.4.2"]
                 [metosin/ring-http-response "0.9.1"]
                 [mount "0.1.16"]
                 [nrepl "0.7.0"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597" :scope "provided"]
                 [org.clojure/core.async "1.1.582"]
                 [org.clojure/google-closure-library "0.0-20191016-6ae1f72f" :scope "provided"]
                 [org.clojure/tools.cli "1.0.194"]
                 [org.clojure/tools.logging "1.0.0"]
                 [org.postgresql/postgresql "42.2.11"]
                 [org.webjars.npm/bulma "0.8.1"]
                 [org.webjars.npm/material-icons "0.3.1"]
                 [org.webjars/webjars-locator "0.39"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-defaults "0.3.2"]
                 [selmer "1.12.19"]
                 [thheller/shadow-cljs "2.8.93" :scope "provided"]]

  :min-lein-version "2.0.0"
  
  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :test-paths ["test/clj"]
  :resource-paths ["resources" "target/cljsbuild"]
  :target-path "target/%s/"
  :main ^:skip-aot bookshelf.core

  :plugins [[lein-shadow "0.1.7"]
            [lein-immutant "2.1.0"]] 
  :clean-targets ^{:protect false}
  [:target-path "target/cljsbuild"]
  :shadow-cljs
  {:nrepl {:port 7002}
   :builds
   {:app
    {:target :browser
     :output-dir "target/cljsbuild/public/js"
     :asset-path "/js"
     :modules {:app {:entries [bookshelf.app]}}
     :devtools {:watch-dir "resources/public"}}
    :test
    {:target :node-test
     :output-to "target/test/test.js"
     :autorun true}}}
  
  :npm-deps [[shadow-cljs "2.8.93"]]
  :npm-dev-deps [[xmlhttprequest "1.8.0"]]

  :profiles
  {:uberjar {:omit-source true
             :prep-tasks ["compile" ["shadow" "release" "app"]]
             
             :aot :all
             :uberjar-name "bookshelf.jar"
             :source-paths ["env/prod/clj"  "env/prod/cljs" ]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn" ]
                  :dependencies [[binaryage/devtools "1.0.0"]
                                 [cider/piggieback "0.4.2"]
                                 [pjstadig/humane-test-output "0.10.0"]
                                 [prone "2020-01-17"]
                                 [ring/ring-devel "1.8.0"]
                                 [ring/ring-mock "0.4.0"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.24.1"]
                                 [jonase/eastwood "0.3.5"]] 
                  
                  
                  :source-paths ["env/dev/clj"  "env/dev/cljs" "test/cljs" ]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user
                                 :timeout 120000}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn" ]
                  :resource-paths ["env/test/resources"] 
                  
                  
                  }
   :profiles/dev {}
   :profiles/test {}})
