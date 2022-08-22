(ns kees.grip.views
  (:require [kees.grip.rf :as rf :refer [<sub >evt]]))

(defn main []
  [:<>
   [:header
    [:h1 "A ready-to-use re-frame template"]
    [:hr]]
   [:main]
   [:footer]])
