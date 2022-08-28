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
  "Include a styled list by adding strings to (ul ...)."
  []
  (ul "Now, the subscriptions are expanded and events are contracted with interceptors to simplify logic."
      "A status light panel indicates whether a button is on using local state and the same event as each button."
      [:<> [:a {:href "https://github.com/luciodale/fork" :target "_blank"} "fork"] " is overkill when no typical form submissions occur."]))

(defn- questions
  "Include styled questions and answers with a sequence of (qa str str) forms."
  []
  [:article.qa
   (qa "What's intercepted?"
       "I originally split logic across events because the form handled submission in a nested map. The input on-change handler pares the values and the interceptor coerces coordinates in a vector to ints.")])

(defn afterword
  "Acts like a note section or changelog. Add your content inline via the included components."
  []
  [:details.afterword
   [:summary "Notes"]
   [changes]
   [questions]])
