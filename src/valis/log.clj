(ns valis.log
  (:require [clj-commons.ansi :as ansi]))

(defn ok [x]
    (println (str "🤖, ok: " (ansi/compose [:green x]))))