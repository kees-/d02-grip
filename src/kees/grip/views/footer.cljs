(ns kees.grip.views.footer)

(defn- ul
  [& li]
  (into
   [:ul]
   (for [i li]
     [:li i])))

(defn- qa
  ([q] [:p.q q])
  ([q a] [:<> [:p.q q] [:p.a a]]))

;; ========== CONTENT ==========================================================
(defn- changes
  []
  (ul [:<> "First steps are taken for " [:strong "actions"] " and " [:strong "behavior"] " that interact with the grid."]
      [:<> [:a {:href "https://github.com/luciodale/fork" :target "_blank"} "fork"] " is introduced to create modular panels."]
      "Panels are external to the grid and can adjust state without interacting directly with buttons."
      "The simple concept will allow for more interesting actions than repeating what can already be done."))

(defn- questions
  []
  [:article.qa
   (qa "What panels could be introduced next?"
       "How about toggling multiple buttons at once, or toggling one button's neighbors.")
   (qa "And then?..."
       "A sensor that lights when a specific button is on.")])

(defn afterword
  []
  [:details.afterword
   [:summary "Notes"]
   [changes]
   [questions]])
