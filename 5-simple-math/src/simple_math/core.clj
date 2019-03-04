(ns simple-math.core
  (:use [seesaw core border])
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

(defn get-result-str
  [firstNumb secondNumb]
  (str firstNumb " + " secondNumb " = " (+ firstNumb secondNumb) "\n" 
                firstNumb " - " secondNumb " = " (- firstNumb secondNumb) "\n"
                firstNumb " * " secondNumb " = " (* firstNumb secondNumb) "\n"
                firstNumb " / " secondNumb " = " (/ firstNumb secondNumb) ))

(def gui-frame-root (frame :title "Simple math form" :width 500 :height 500))
(def first-num-txt (text :id :first-num-text :text "0"))
(def second-num-txt (text :id :second-num-text :text "0"))
(def result-panel (border-panel :id :result-panel :center "WTF"))
(def result-txt (text :id :result-text :text "" :editable? false))

(defn set-result
  []
  (def first-num (parse-number (.getText first-num-txt)))
  (def second-num (parse-number (.getText second-num-txt)))
  (if (not (or (nil? first-num) (neg? first-num) (nil? second-num) (neg? second-num) (= 0 first-num) (= 0 second-num)))
    (.setText result-txt (get-result-str first-num second-num))))


(defn initGUI
  "Initializes gui for task. Two text input fields with the third result subform"
  []
  (config! gui-frame-root :content
           (horizontal-panel :items [
                                     (vertical-panel :items [
                                                             (border-panel :border "First number" :center first-num-txt)
                                                             (border-panel :border "Second number" :center second-num-txt)
                                                             (border-panel :border "Result" :center result-txt)
                                                             ])]))
  (listen first-num-txt :key-typed
          (fn [e]
            (set-result)))
  (listen second-num-txt :key-typed
          (fn [e]
            (set-result)))
 (-> gui-frame-root pack! show!))

(defn initTextVersion
  "Text version of the program"
  []
  (println "What is the first number?")
  (def firstNumb (get-number))
  (println "What is the second number?")
  (def secondNumb (get-number))
  (println (get-result-str firstNumb secondNumb)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (if (= (nth args 0) "gui")
    (initGUI)
    (initTextVersion)))

