(ns saying-hello.core
  (:gen-class))

(defn simple
  []
  (println "What is your name?")
  (def input (read-line))
  (def output (str "Hello, " input ", nice to meet you!"))
  (println output))

(defn no-variables
  []
  (println "What is your name?")
  (println (str "Hello, " (read-line) ", nice to meet you!")))

(defn different-greetings
  []
  (println "What is your name?")
  (def input (read-line))
  (if (= "valera" input)
    (println (str "WOW! THIS IS " input))
    (println (str "Hello, " input ", nice to meet you!")))) 

(defn -main
  [& args]

  (simple)
  (no-variables)
  (different-greetings) 
  )
