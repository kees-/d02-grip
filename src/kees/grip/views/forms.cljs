(ns kees.grip.views.forms
  (:require [fork.re-frame :as fork]
            [reagent.core :as r]
            [kees.grip.rf :as rf :refer [<sub >evt]]))

(defn- submit
  [handler]
  [:button
   {:on-click handler}
   (char 10173)])

(defn- toggle-single-button-form
  []
  [fork/form
   {:initial-values {:x 0
                     :y 0}
    :on-submit #(>evt [::rf/toggle-single-button-form %])
    :keywordize-keys true}
   (fn [{:keys [handle-submit handle-change]
         {:keys [x y]} :values}]
     [:div.panel
      [:p "Toggle a single button"]
      [:span
       [:label {:name "x"} "X:"]
       [:input.w3r {:name "x"
                    :placeholder "X"
                    :value x
                    :on-change handle-change}]
       [:label {:name "y"} "Y:"]
       [:input.w3r {:name "y"
                    :placeholder "Y"
                    :value y
                    :on-change handle-change}]]
      [submit handle-submit]])])

(defn led
  [coord]
  (let [on? (<sub [::rf/on? coord])]
    [:div.led {:class (when on? "bright")}]))

(defn single-button-status
  []
  (let [coord (r/atom [0 0])]
    (fn []
      [:div.panel
       [:p "Status of a single button"]
       [:span
        [:label {:name "x"} "X:"]
        [:input.w3r {:name "x"
                     :value (@coord 0)
                     :on-change #(->> % .-target .-value int (swap! coord assoc 0))}]
        [:label {:name "y"} "Y:"]
        [:input.w3r {:name "y"
                     :value (@coord 1)
                     :on-change #(->> % .-target .-value int (swap! coord assoc 1))}]]
       [led @coord]])))

(defn control-panel
  []
  [:div.control-panel
   [toggle-single-button-form]
   [single-button-status]])
