(ns valis.backup-test
  (:require [clojure.test :refer :all])
  (:require [valis.backup :refer [read-wasabi-variables]]))

(deftest read-wasabi-variables-test
  (read-wasabi-variables)
  )
