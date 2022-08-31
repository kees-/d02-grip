(ns kees.grip.logic)

(defmulti rules :type)
(defmethod rules :default
 [_]
 nil)

; Set out to in
(defmethod rules :buffer
  [{:keys [params]}]
  (let [[in out] params]
    (fn [m]
      (assoc-in m out (get-in m in)))))

; If in -> toggle out
(defmethod rules :t-flip-flop
  [{:keys [params]}]
  (let [[in out] params]
    (fn [m]
      (if (get-in m in)
        (update-in m out not)
        m))))

;; Examples of what would go in global state :logic
; {:type :buffer :params [[0 0] [1 1]]}
; {:type :t-flip-flop :params [[0 0] [1 1]]}
