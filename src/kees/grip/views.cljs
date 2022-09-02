(ns kees.grip.views
  (:require [kees.grip.rf :as rf :refer [<sub >evt]]
            [kees.grip.views.footer :as footer]
            [kees.grip.views.forms :as forms]))

(defn cell
  [coord]
  (let [color (<sub [::rf/button-color coord])]
    [:div.cell
     [:div.block
      {:style {:background-color color}
       :on-click #(>evt [::rf/toggle-button coord])}]]))

(defn grid
  []
  (into
   [:div.board]
   (for [y (range 8) x (range 8)]
     [cell [x y]])))

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
     ; Span keywords are necessary for nav rewrite script
     [:span>a#previous {:href "{{PREVIOUS}}" :class nil} "Previous"]
     [:span>a#index {:href "../grip.html"} "Index"]
     [:span>a#next {:href "{{NEXT}}" :class nil} "Next"]]
    [footer/afterword]]])
