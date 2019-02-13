(ns mad-lib.core
  (:gen-class))

(defn readarg
  [arg]
  (println (str "Enter a " arg ":"))
  (read-line))

(defn -main
  [& args]
  (def noun   (readarg "noun"))
  (def verb   (readarg "verb"))
  (def adj    (readarg "adjective"))
  (def adverb (readarg "adverb"))
  (def quest  (readarg "question"))

  (if (= "kill" verb)
    (println "Kill? Really?")
    (println (str "Do you " verb " your " adj " " noun " " adverb "? That's hilarious! But " quest "?"))))
