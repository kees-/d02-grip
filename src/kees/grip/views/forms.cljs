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
      [submit handle-submit]])])

(defn led
  [x y]
  (let [on? (<sub [::rf/on? [(int x) (int y)]])]
    [:div.led {:class (when on? "bright")}]))

(defn single-button-status
  []
  (let [x (r/atom 0)
        y (r/atom 0)]
    (fn []
      [:div.panel
       [:p "Status of a single button"]
       [:span
        [:label {:name "x"} "X:"]
        [:input.w3r {:name "x"
                     :value @x
                     :on-change #(->> % .-target .-value (reset! x))}]
        [:label {:name "y"} "Y:"]
        [:input.w3r {:name "y"
                     :value @y
                     :on-change #(->> % .-target .-value (reset! y))}]]
       [led @y @x]])))

(defn control-panel
  []
  [:div.control-panel
   [toggle-single-button-form]
   [single-button-status]])
