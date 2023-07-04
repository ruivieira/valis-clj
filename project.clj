(defproject dev.ruivieira/valis "0.1.0-SNAPSHOT"
  :description "Valis is a Clojure library for my personal workflows."
  :url "https://github.com/ruivieira/valis-clj"
  :license {:name "GPLv3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html#license-text"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [cheshire "5.10.1"]
                 [org.clj-commons/pretty "2.0-beta-1"]
                 [babashka/process "0.5.21"]]
  :repl-options {:init-ns dev.ruivieira.valis.core}
  :plugins [[lein-codox "0.10.8"]]
  :codox {:output-path "docs"})
