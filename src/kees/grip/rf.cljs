(ns kees.grip.rf
  (:require
   [re-frame.core :as re-frame :refer [reg-event-db reg-event-fx reg-sub reg-fx reg-cofx path]]))

;; ========== SETUP ============================================================
(def <sub (comp deref re-frame/subscribe))
(def <sub-lazy re-frame/subscribe)
(def >evt re-frame/dispatch)
(def >evt-now re-frame/dispatch-sync)

(def default-db
  {:on (vec (repeat 8 (vec (repeat 8 false))))})

;; ========== EFFECTS ==========================================================
(reg-event-fx
 ::boot
 (fn [_ _]
   {:db default-db}))

(reg-event-db
 ::toggle-button
 (path :on)
 (fn [on [_ coord]]
   (update-in on coord not)))

;; ========== SUBSCRIPTIONS ====================================================
(reg-sub
 ::on?
 (fn [db [_ [x y]]]
   (get-in db [:on x y])))
