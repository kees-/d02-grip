(ns kees.grip.views
  (:require [kees.grip.rf :as rf :refer [<sub >evt]]
            [kees.grip.views.footer :as footer]))

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
    [footer/afterword]]])
