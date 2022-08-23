(ns kees.grip.main
  (:require
   [reagent.dom :as rdom]
   [kees.grip.views :as views]
   [kees.grip.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (let [root (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root)
    (rdom/render [views/main] root)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn init []
  (dev-setup)
  (mount-root))
