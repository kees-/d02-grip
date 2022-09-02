(ns kees.grip.rf
  (:require
   [re-frame.core :as re-frame :refer [reg-event-db reg-event-fx reg-sub reg-fx reg-cofx path ->interceptor]]
   [cljs.reader :as reader]
   [cljs.pprint :refer [pprint]]
   [kees.grip.logic :as logic]))

;; ========== SETUP ============================================================
(def <sub (comp deref re-frame/subscribe))
(def <sub-lazy re-frame/subscribe)
(def >evt re-frame/dispatch)
(def >evt-now re-frame/dispatch-sync)

(def default-db
  {:on (vec (repeat 8 (vec (repeat 8 false))))
   :rules (list)})

; Ensure maps of coordinates passed to handlers are ints
(def vec->ints
  (->interceptor
   :id :vec->ints
   :before (fn [context]
             (update-in context [:coeffects :event 1] #(mapv int %)))))

;; ========== EFFECTS ==========================================================
(reg-fx
 :console
 (fn [arg]
   (-> arg js/console.info)))

(reg-fx
 :obj->clipboard
 (fn [text]
   (->> text pprint with-out-str (.writeText js/navigator.clipboard))))

(reg-event-fx
 ::state->console
 (fn [{:keys [db]} _]
   {:fx [[:console db]]}))

(reg-event-fx
 ::state->clipboard
 (fn [{:keys [db]} _]
   {:fx [[:obj->clipboard db]]}))

(reg-event-fx
 ::boot
 (fn [_ _]
   {:db default-db}))

(reg-event-fx
 ::read-state
 (fn [db [_ new-db]]
   (->> new-db reader/read-string (merge db))))

; Take every rule present and apply it to the grid back-to-front
(reg-event-fx
 ::tick
 (fn [{:keys [db]} _]
   {:db (update db :on (apply comp (mapv logic/rules (:rules db))))}))

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

; Redundant for sake of data hints
(reg-event-db
 ::add-rule
 (fn [db [_ {:keys [type params]}]]
   (update db :rules conj {:type type :params params})))

(reg-event-db
 ::clear-rules
 (fn [db _]
   (assoc db :rules (list))))

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

(reg-sub
 ::active-rules
 (fn [{:keys [rules]} _]
   rules))
