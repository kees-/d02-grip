(ns kees.grip.views
  (:require [kees.grip.rf :as rf :refer [<sub >evt]]))

(defn toggle-class
  [id]
  (let [el (.getElementById js/document id)
        classes (.-classList el)]
    (if (some #{"on"} (seq classes))
      (.remove classes "on")
      (.add classes "on"))))

(defn cell
  [id]
  [:div.cell
   [:div.block
    {:id id
     :on-click #(toggle-class id)}]])

(defn grid
  []
  (into
   [:div.container]
   (for [y (range 8 0 -1)
         x (range 97 105)
         :let [id (str (char x) y)]]
     [cell id])))

(defn main []
  [:<>
   [:header
    [:h1 "A ready-to-use re-frame template"]
    [:hr]]
   [:main
    [grid]]
   [:footer]])
