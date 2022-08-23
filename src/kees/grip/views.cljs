(ns kees.grip.views
  (:require [kees.grip.rf :as rf :refer [<sub >evt]]
            [kees.grip.views.footer :as footer]))

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
    [grid]]
   [:footer
    [:hr]
    [:nav
     [:a#previous {:href "#"} "Previous"]
     [:a#next {:href "#"} "Next"]]
    [footer/afterword]]])
