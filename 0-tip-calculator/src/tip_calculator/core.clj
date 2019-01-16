(ns tip-calculator.core
  (:use [seesaw core color border dev])
  (:gen-class))

(defn parse-number
  "Reads a number from string. Returns nil if not a number"
  [s]
  (if (re-find #"^-?\d+\.?\d*$" s)
    (read-string s)))

(defn get-number
  ""
 []
  (def result (parse-number (read-line)))
  (if (or (nil? result) (neg? result))
    (do (println "You entered wrong number! Try again.")
     (get-number))
    (do 
    result)))

(defn get-bill-amount
  ""
  []
  (println "What is the bill amount?")
  (def result (get-number))
  (println (str "Bill amount: " result))
  result)

(defn get-tip-rate
  ""
  []
  (println "What is the tip rate?")
  (def result (get-number))
  (println (str "Tip rate: " result))
  result)

(defn calculate-all
  ""
  []
  (def bill-amount (get-bill-amount))
  (def tip-rate (get-tip-rate))

  (def tip (* bill-amount (float (/ tip-rate 100)) ) )
  (def total (+ tip bill-amount))
  
  (println (str "Tip: " (format "%.2f" tip)))
  (println (str "Total: " (format "%.2f" total)))
  )

(def gui-frame-root (frame :title "Tip calculator" :width 500 :height 500))
(def bill-amount-txt (text :id :bill-amount-text :text "0.00" ))
(def tip-txt (text :id :tip-text :text "0.00" :editable? false)) 
(def total-txt (text :id :total-text :text "0.00" :editable? false))
(def gui-slider (slider :id :tip-slider :min 5 :max 25))

(defn set-gui-values
  []
  (let [bill-text (.getText bill-amount-txt)]
    (def bill-amount (parse-number bill-text))
    (def tip (/ (.getValue gui-slider) 100))
    (if (not (or (nil? bill-amount) (neg? bill-amount)))
      (.setText total-txt (String/valueOf (* (.doubleValue tip) bill-amount))))))

(defn -main
  [& args]
  (config! gui-frame-root :content 
           (horizontal-panel :items [ 
                                     (vertical-panel :items [
                                                             (border-panel :border "Bill amount" :center bill-amount-txt)
                                                             (border-panel :border "Your satisfactory " :center gui-slider)
                                                             (border-panel :border "Tip " :center tip-txt)
                                                             (border-panel :border "Total " :center total-txt)
          ])]))
  (listen bill-amount-txt :key-typed
          (fn [e]
            (set-gui-values)))
  (listen gui-slider :state-changed
          (fn [e]
              (def tip (/ (.getValue gui-slider) 100))
              (.setText tip-txt (String/valueOf (.doubleValue tip)))
              (set-gui-values)
              ))
  (-> gui-frame-root pack! show!)
  (show-events gui-slider)
  (show-events bill-amount-txt)
)


