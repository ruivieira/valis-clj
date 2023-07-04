(ns valis.backup
  (:require [clojure.string :as str]
            [babashka.process :as p]
         [valis.log :as log]))

(defn read-wasabi-variables []
  (let [env-keys ["WASABI_KOPIA_BUCKET" "WASABI_KOPIA_ACCESS_KEY" "WASABI_KOPIA_SECRET_KEY" "WASABI_KOPIA_ENDPOINT" "KOPIA_PASSWORD"]]
    (into {} (map (fn [k] [(keyword (str/lower-case k)) (System/getenv k)]) env-keys))))

(defn connect-wasabi [env]
  (log/ok "connecting to Wasabi")
  ;; you will need to replace this with the actual kopia connect command
  (p/shell {:inherit true} "kopia" "repository" "connect" "s3" "--bucket" (:wasabi_kopia_bucket env)
           "--access-key" (:wasabi_kopia_access_key env)
           "--secret-access-key" (:wasabi_kopia_secret_key env)
           "--endpoint" (:wasabi_kopia_endpoint env)
           "--password" (:kopia_password env)))


(defn kopia-snapshot [folder]
  (log/ok (str "creating snapshot for " folder))
  ;; you will need to replace this with the actual kopia snapshot create command
  (p/shell {:inherit true} "kopia" "snapshot" "create" folder)
  )