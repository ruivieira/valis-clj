(ns dev.ruivieira.valis.ml.mlserver
   (:require [cheshire.core :as json]
            [clojure.java.io :as io]))

(defn write-model-settings
  "Writes the model settings to the given directory."
  [data dir]
  (let [file (io/file dir "model-settings.json")]
    (with-open [wtr (io/writer file)]
      (json/generate-stream data wtr))))

(defn read-model-settings
  "Reads the model settings from the given directory and writes them to a file."
  [dir]
  (let [file (io/file dir "model-settings.json")]
    (with-open [rdr (io/reader file)]
      (json/parse-stream rdr true))))
