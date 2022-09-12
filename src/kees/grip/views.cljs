(ns kees.grip.views
  (:require [kees.grip.rf :as rf :refer [<sub >evt]]
            [kees.grip.views.footer :as footer]
            [kees.grip.views.forms :as forms]))

(defn cell
  [coord id]
  (let [on? (<sub [::rf/on? coord])]
    [:div.cell
     [:div.block
      {:id id
       :style {:background-color (if on? "black" "white")}
       :on-click #(>evt [::rf/toggle-button coord])}]]))

(defn grid
  []
  (into
   [:div.container]
   (for [x (range 8) y (range 8)]
     [cell [x y] (str x "," y)])))

(defn main []
  [:<>
   [:header
    [:h1 "Nice grid"]
    [:hr]]
   [:main
    [grid]
    [forms/control-panel]]
   [:footer
    [:hr]
    [:nav
     [:span>a#previous {:href "../003"} "Previous"]
     [:span>a#index {:href "../grid.html"} "Index"]
     [:span>a#next {:href "../005"} "Next"]]
    [footer/afterword]]])
