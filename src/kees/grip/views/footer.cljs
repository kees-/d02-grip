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
  (ul "The shape of the state and method of calling is very different even though the app still looks identical."
      "The state db is initialized with an 8×8 2D array of false values."
      "Fanciness from the coords is stripped down to just [0,7] to allow direct lookups by index."
      [:<> "Toggling a state only needs " [:code "not"] " applied to the index at the coord position (x,y)!"]))

(defn- questions
  []
  [:article.qa
   (qa "Where is this going?"
       "What if I could toggle a button from outside the grid? What if something outside the grid could be watching for a specific toggle?")])

(defn afterword
  []
  [:details.afterword
   [:summary "Notes"]
   [changes]
   [questions]])
