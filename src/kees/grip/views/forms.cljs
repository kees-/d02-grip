(ns kees.grip.views.forms
  (:require [fork.re-frame :as fork]
            [reagent.core :as r]
            [clojure.edn :as edn]
            [kees.grip.rf :as rf :refer [<sub >evt]]
            [kees.grip.logic :as logic]))

(defn- submit
  [handler]
  [:button
   {:on-click handler}
   [:div (char 10148)]])

(defn- toggle-single-button-form
  []
  [fork/form
   {:initial-values {:x 0 :y 0}
    :on-submit #(>evt [::rf/toggle-button (-> % :values vals vec)])
    :keywordize-keys true}
   (fn [{:keys [handle-submit handle-change]
         {:keys [x y]} :values}]
     [:div.panel
      [:p "Toggle a single button"]
      [:span
       [:label {:name "x"} "X:"]
       [:input.numerical-input {:name "x"
                                :value x
                                :on-change handle-change}]
       [:label {:name "y"} "Y:"]
       [:input.numerical-input {:name "y"
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
        [:input.numerical-input {:name "x"
                                 :value (@coord 0)
                                 :on-change #(->> % .-target .-value (swap! coord assoc 0))}]
        [:label {:name "y"} "Y:"]
        [:input.numerical-input {:name "y"
                                 :value (@coord 1)
                                 :on-change #(->> % .-target .-value (swap! coord assoc 1))}]]
       [led @coord]])))

(defn add-rule
  []
  (let [new-rule-type (r/atom ":buffer")
        new-rule-params (r/atom "")]
    (fn []
      [:div.panel
       [:p "Add a new rule (FIFO)"]
       [:span
        [:label {:name "type"} "Type:"]
        (into
         [:select {:name "type"
                   :on-change #(->> % .-target .-value (reset! new-rule-type))}]
         (for [method (-> logic/rules methods keys)
               :when (not= method :default)]
           [:option {:value (str method)} (method logic/method-string-names)]))]
       [:span
        [:label {:name "params"} "Params:"]
        [:input.code-field {:name "params"
                            :value @new-rule-params
                            :on-change #(->> % .-target .-value (reset! new-rule-params))}]]
       [submit #(do
                  (>evt [::rf/add-rule
                         {:type (edn/read-string @new-rule-type)
                          :params (edn/read-string @new-rule-params)}])
                  (reset! new-rule-params ""))]])))

(defn active-rules
  []
  [:div.panel
   [:p "Currently active rules"]
   (into
    [:span>ol]
    (for [rule (reverse (<sub [::rf/active-rules]))]
      [:li>code (str rule)]))])

(defn tick-trigger
  []
  [:div.panel
   [:p "Tick"]
   [submit #(>evt [::rf/tick])]])

(defn utilities
  []
  [:div.panel
   [:p "Utilities"]
   [:span
    [submit #(>evt [::rf/state->clipboard])]
    [:aside "Copy state to clipboard"]]
   [:span
    [submit #(>evt [::rf/state->console])]
    [:aside "Print state to console"]]
   [:span
    [submit #(>evt [::rf/clear-rules])]
    [:aside "Remove all current rules"]]])

(defn control-panel
  []
  [:div.control-panel
   [toggle-single-button-form]
   [single-button-status]
   [add-rule]
   [tick-trigger]
   [active-rules]
   [utilities]])
