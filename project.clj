(defproject valis "0.1.0-SNAPSHOT"
  :description "Valis is a Clojure library for my personal workflows."
  :url "https://github.com/ruivieira/valis-clj"
  :license {:name "GPLv3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html#license-text"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [cheshire "5.10.1"]]
  :repl-options {:init-ns valis.core}
  :plugins [[lein-codox "0.10.8"]]
  :codox {:output-path "docs"})
