(defproject googlecodejam "0.1.0-SNAPSHOT"
  :description "Solutions to various Google Code Jam problems"
  :url "https://github.com/evanspa/googlecodejam.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src"]
  :test-paths ["test"]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.apache.commons/commons-lang3 "3.1"]]
  :plugins [[lein-swank "1.4.4"]
            [lein-pprint "1.1.1"]])
