(ns dev.ruivieira.valis.tests.kserve
  (:require [clojure.test :refer :all]
            [dev.ruivieira.valis.ml.kserve :refer :all]))

;(deftest test-create-payload-and-post
;  (let [model-name "simple"
;        version "v0.1.0"
;        host-port "192.168.0.47:8080"
;        inputs [{:name "input1" :datatype "FP32" :data [1 2 3 4 5]}]
;        response (create-payload-and-post model-name version host-port inputs)]
;    (is (= (:status response) 200))
;    (is (= (:body response) "{\"outputs\":{\"output1\":{\"datatype\":\"FP32\",\"data\":[1.0,2.0,3.0,4.0,5.0],\"shape\":[1,5]}}}"))))

(deftest test-count-dims-simple
  (testing
  (let [data '[1 2 3 4 5]]
       (is (= (count-dims data) '[5])))))

(deftest test-count-dims-uni
  (testing
  (let [data '[[1] [2] [3] [4] [5]]]
       (is (= (count-dims data) '[1 5])))))

(deftest test-count-dims-multi
  (testing
  (let [data '[[1 2 3] [2 3 4] [3 4 5] [4 5 6] [5 6 7]]]
       (is (= (count-dims data) '[3 5])))))
