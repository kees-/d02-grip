(ns kees.grip.logic)

(def method-string-names
  {:buffer "Buffer"
   :not "Not"
   :t-flip-flop "T Flip-Flop"
   :and "And"
   :or "Or"
   :nand "Nand"
   :nor "Nor"})

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

(defmethod rules :not
 [{:keys [params]}]
 (let [[in out] params]
   (fn [m]
     (assoc-in m out (not (get-in m in))))))

(defmethod rules :and
  [{:keys [params]}]
  (let [[{:keys [ins out]}] params]
    (fn [m]
      (assoc-in m out (every? true? (map #(get-in m %) ins))))))

(defmethod rules :or
  [{:keys [params]}]
  (let [[{:keys [ins out]}] params]
    (fn [m]
      (assoc-in m out (some true? (map #(get-in m %) ins))))))


(defmethod rules :nand
  [{:keys [params]}]
  (let [[{:keys [ins out]}] params]
    (fn [m]
      (assoc-in m out (some false? (map #(get-in m %) ins))))))

(defmethod rules :nor
  [{:keys [params]}]
  (let [[{:keys [ins out]}] params]
    (fn [m]
      (assoc-in m out (every? false? (map #(get-in m %) ins))))))

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
