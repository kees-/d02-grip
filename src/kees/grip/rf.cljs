(ns kees.grip.rf
  (:require
   [re-frame.core :as re-frame :refer [reg-event-db reg-event-fx reg-sub reg-fx reg-cofx path ->interceptor]]))

;; ========== SETUP ============================================================
(def <sub (comp deref re-frame/subscribe))
(def <sub-lazy re-frame/subscribe)
(def >evt re-frame/dispatch)
(def >evt-now re-frame/dispatch-sync)

(def default-db
  {:on (vec (repeat 8 (vec (repeat 8 false))))})

(def vec->ints
  (->interceptor
   :id :vec->ints
   :before (fn [context]
             (update-in context [:coeffects :event 1] #(mapv int %)))))

;; ========== EFFECTS ==========================================================
(reg-event-fx
 ::boot
 (fn [_ _]
   {:db default-db}))

(reg-event-db
 ::toggle-button
 [(path :on) vec->ints]
 (fn [on [_ coord]]
   (update-in on coord not)))

(reg-event-db
 ::multi-toggle-button
 (path :on)
 (fn [on [_ & coords]]
   (reduce #(update-in %1 %2 not) on coords)))


;; ========== SUBSCRIPTIONS ====================================================
(reg-sub
 ::on?
 (fn [db [_ [x y]]]
   (get-in db [:on x y])))

(reg-sub
 ::button-color
 (fn [db [_ [x y]]]
   (if (get-in db [:on x y])
     "black"
     "white")))
