(ns kees.grip.views.forms
  (:require [fork.re-frame :as fork]
            [kees.grip.rf :as rf :refer [>evt]]))

(defn- submit
  [handler]
  [:button
   {:on-click handler}
   (char 10173)])

(defn- toggle-single-button-form
  []
  [fork/form
   {:initial-values {:x "0"
                     :y "0"}
    :on-submit #(>evt [::rf/toggle-single-button-form %])
    :keywordize-keys true}
   (fn [{:keys [values handle-submit handle-change]}]
     [:div.panel
      [:p "Toggle a single button"]
      [:span
       [:label {:name "x"} "X:"]
       [:input.w3r {:name "x"
                    :value (:x values)
                    :on-change handle-change}]
       [:label {:name "y"} "Y:"]
       [:input.w3r {:name "y"
                    :value (:y values)
                    :on-change handle-change}]]
      [:span
       [submit handle-submit]]])])

(defn control-panel
  []
  [:div.control-panel
   [toggle-single-button-form]])
