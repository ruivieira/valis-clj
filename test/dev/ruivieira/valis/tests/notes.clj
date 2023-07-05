(ns dev.ruivieira.valis.tests.notes
  (:require [clojure.test :refer :all]
            [dev.ruivieira.valis.notes :refer :all]
            [flatland.ordered.map :refer :all]))
(deftest test-find-files
  (testing
  (let [files (find-files "resources/markdown")]
       (is (= (count files) 2)))))

(deftest test-read-file
  (testing
  (let [file (read-file "resources/markdown/TestFile1.md")]
    (is (= (:path file) "resources/markdown/TestFile1.md"))
    (is (= (:title file) "TestFile1"))
    (is (= (:frontmatter file) (ordered-map :title "Test file 1"))))))

(deftest test-find-publishable-files
  (testing
  (let [files (find-publishable-files "resources/markdown")]
       (is (= (count files) 1)))))

(deftest test-remaining-days
  (testing "task without due or scheduled date has no remaining days"
    (is (nil? (remaining-days {:task "test task"}))))
  (testing "task with due date has remaining days"
    (let [today (java.time.LocalDate/now)
          due-date (.format (.plusDays today 1)
                            (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd"))
          task {:task "test task" :due due-date}]
      (is (= 1 (remaining-days task)))))
  (testing "task with scheduled date but no due date has remaining days"
    (let [today (java.time.LocalDate/now)
          scheduled-date (.format (.plusDays today 1)
                                  (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd"))
          task {:task "test task" :scheduled scheduled-date}]
      (is (= 1 (remaining-days task)))))
  (testing "task with due date and scheduled date uses due date"
    (let [today (java.time.LocalDate/now)
          due-date (.format (.plusDays today 1)
                            (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd"))
          scheduled-date (.format (.plusDays today 2)
                                  (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd"))
          task {:task "test task" :due due-date :scheduled scheduled-date}]
      (is (= 1 (remaining-days task))))))