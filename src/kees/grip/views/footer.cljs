(ns kees.grip.views.footer)

;; ========== SETUP ============================================================
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
  "Include a styled list by adding strings to (ul ...)."
  []
  (ul "I introduced a new state array and multimethod which turns parameters into a series of rules."
      "Each rule is applied to the value matrix when time is updated."
      "Currently the only way to progress the state, applying the rules, is with the Tick button: 1 step forward."
      "No rules are specified in the default state, so Tick does not do anything currently."))

(defn- questions
  "Include styled questions and answers with a sequence of (qa str str) forms."
  []
  [:article.qa
   (qa "What's implemented so far?"
       "Most of the basic logic gates. See the dropdown for list")
   (qa "What goes in the params field?"
       [:<> "Single input rules accept two " [:code "[x y]"] " coordinate vectors for in and out coords. Gates with multiple inputs take format " [:code "{:ins [[x y] [x y]] :out [x y]}"] ", which I will standardize."])
   (qa "What's the point?"
       "Add a UI for creation, ordering, and deletion of logic gates with specified parameters and you can build circuitry.")])

(defn afterword
  "Acts like a note section or changelog. Add your content inline via the included components."
  []
  [:details.afterword
   [:summary "Notes"]
   [changes]
   [questions]])
