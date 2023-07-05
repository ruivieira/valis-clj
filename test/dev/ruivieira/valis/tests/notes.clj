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
