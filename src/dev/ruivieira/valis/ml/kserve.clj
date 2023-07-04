(ns dev.ruivieira.valis.ml.kserve)

(require '[dev.ruivieira.valis.ml.mlserver :as mlserver]
         '[org.httpkit.client :as http]
         '[cheshire.core :as json])
(defn count-dims [coll]
  "Recursive function to calculate the shape of the data."
  (if (coll? coll)
    (let [first-elem (first coll)]
      (if (coll? first-elem)
        (conj (count-dims first-elem) (count coll))
        [(count coll)]))
    []))

(defn create-payload-and-post
  "This function takes a model name, a version, a host with port and a list of maps with
  a name, a datatype, and an array of data. It automatically calculates the shape from the
  data size, with the first dimension always being the size of the outer list, creates a JSON payload and sends it as a POST to a given URL."
  [model-name version host-port inputs]
  (let [url (str "http://" host-port "/v2/models/" model-name "/versions/" version "/infer")
        inputs-with-shape (map #(assoc % :shape (count-dims (:data %))) inputs)
        payload {:inputs inputs-with-shape}
        json-payload (json/generate-string payload)]
    @(http/post url {:body json-payload :headers {"Content-Type" "application/json"}})))


(defn use-read-model-settings
  "This function takes a path to a JSON file, reads the JSON file using read-model-settings,
  extracts the name and version fields from the map, and returns them."
  [json-path]
  (let [{:keys [name parameters]} (mlserver/read-model-settings json-path)
        {:keys [version]} parameters]
    [name version]))
