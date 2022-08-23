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
  (ul "State is managed by re-frame."
      "Click dispatches a toggle event which adds or removes the id to a list."
      "Performance feels much worse (events lock up the view)."
      "Button display is an inline style that depends if the id is in the list."
      "id name is changed to cartesian coordinates."))

(defn- questions
  []
  [:article.qa
   (qa "Why is the state global instead of local to each button?"
       "I plan to do more state manipulation. I don't really know how easy it would be to access local state when effecting changes.")
   (qa "How can state management be improved?"
       "I am going to try turning the state into a 2D boolean array. It can do coordinate lookups instead of scanning the entire list each time.")])

(defn afterword
  []
  [:details.afterword
   [:summary "Notes"]
   [changes]
   [questions]])
