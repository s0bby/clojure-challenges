(ns retirement-calc.core
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

(defn -main
  [& args]
  (println "What is your current age?")
  (def age (get-number))
  (println "At what age you would like to retire?")
  (def retire-age (get-number))
  (if (< retire-age age)
    (do (println "Retire age cannot be smaller that your current age!")
        (java.lang.System/exit 1)))
  (def current-year (.get (java.util.Calendar/getInstance) (java.util.Calendar/YEAR)))
  (def retire-year (+ (- retire-age age) current-year))
  (println (str "It's " current-year ", so you can retire in " retire-year))
  )
