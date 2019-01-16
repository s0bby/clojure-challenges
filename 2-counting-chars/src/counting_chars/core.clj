(ns counting-chars.core
  (:use [seesaw core color border dev])
  (:gen-class))

(defn fn-read-input
  []
  (println "What is input string?")
  (def v-string-input (read-line))
  (if (= 0 (count v-string-input))
    (do (println "You MUST enter something")
        (fn-read-input))
  )
  v-string-input)

(defn simple
  []
  (def v-string-input (fn-read-input))
  (def v-output (str v-string-input " has " (count v-string-input) " characters."))
  (println v-output))

(defn gui
  []
  (def frame-root (frame :title "Char counter" :width 500 :height 500))
  (def input-txt-field (text :id :input-txt))
  (def char-txt-field (text :id :char-txt-field :editable? false))
  (show-events input-txt-field)
  (listen input-txt-field :key-released
          (fn [e]
            (def typed-txt (.getText input-txt-field))
            (.setText char-txt-field (String/valueOf (count typed-txt)))))

  (config! frame-root :content
           (horizontal-panel :items [
                                     (vertical-panel :items [
                                                             (border-panel :border "Input" :center input-txt-field)
                                                             (border-panel :border "Count" :center char-txt-field)
                                                             ])]))
  (-> frame-root pack! show!)
  )
  


(defn -main
  [& args]
  (simple)
  (gui)
  )
  
