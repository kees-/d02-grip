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
         x (range 97 105)
         :let [id (str (char x) y)]]
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
    [:ul
     [:li "There is no state."]
     [:li "Button activation is controlled by toggling a class onto and off of elements by id."]
     [:li "The button components are fed ids with the notation of a chessboard."]
     [:li "The ids are created with list comprehension."]
     [:li "The grid is laid out with CSS grid."]]]])
