(ns kees.grip.views
  (:require [kees.grip.rf :as rf :refer [<sub >evt]]))

(defn cell
  [id]
  (let [buttons (<sub [::rf/buttons-high])]
    [:div.cell
     [:div.block
      {:id id
       :style {:background-color (if (some #{id} buttons) "black" "white")}
       :on-click #(>evt [::rf/button-toggle id])}
      #_[:article id]]]))

(defn grid
  []
  (into
   [:div.container]
   (for [y (range 8 0 -1)
         x (range 1 9)
         :let [id (str x "," y)]]
     [cell id])))

(defn main []
  [:<>
   [:header
    [:h1 "Nice grid"]
    [:hr]]
   [:main
    [grid]]
   [:footer
    [:hr]
    [:div.afterword
     [:ul
      [:li "State is managed by re-frame."]
      [:li "Click dispatches a toggle event which adds or removes the id to a list."]
      [:li "Performance feels much worse (events lock up the view)."]
      [:li "Button display is an inline style that depends if the id is in the list."]
      [:li "id name is changed to cartesian coordinates."]]]]])
