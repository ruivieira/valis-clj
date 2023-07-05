(ns dev.ruivieira.valis.tests.core
  (:require [clojure.test :refer :all]
            [dev.ruivieira.valis.core :refer :all]))

(deftest test-date-difference-in-days
  (testing "difference between the same date is zero"
    (is (= 0 (date-difference-in-days "2023-07-05" "2023-07-05"))))
  (testing "difference between consecutive dates is one"
    (is (= 1 (date-difference-in-days "2023-07-05" "2023-07-06"))))
  (testing "difference is negative"
    (is (= -1 (date-difference-in-days "2023-07-06" "2023-07-05")))))