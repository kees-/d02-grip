(ns kees.grip.rf
  (:require
   [re-frame.core :as re-frame :refer [reg-event-db reg-event-fx reg-sub reg-fx reg-cofx path]]))

;; ========== SETUP ============================================================
(def <sub (comp deref re-frame/subscribe))
(def <sub-lazy re-frame/subscribe)
(def >evt re-frame/dispatch)
(def >evt-now re-frame/dispatch-sync)

(def default-db
  {:buttons-high (list)})

;; ========== EFFECTS ==========================================================
(reg-event-fx
 ::boot
 (fn [_ _]
   {:db default-db}))

(reg-event-db
 ::button-toggle
 (fn [db [_ id]]
   (let [f (if (some #{id} (:buttons-high db)) #(remove #{%2} %1) conj)]
     (update db :buttons-high f id))))

;; ========== SUBSCRIPTIONS ====================================================
(reg-sub
 ::buttons-high
 (fn [db _]
   (:buttons-high db)))
