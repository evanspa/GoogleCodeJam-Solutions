(ns name.paulevans.googlecodejam.storecredit.main
  (:use [name.paulevans.googlecodejam.storecredit.core :only (process-testcases)]
        [name.paulevans.googlecodejam.storecredit.transform.from_file :only (read-testcases-from-file)]))

(def
  ^{:doc "The full path to the small test cases file."}
  testcases-small-file "/Users/evansp2/Documents/software-projects/Google-Code-Jam/data/testcases/testcases.small.txt")

(def
  ^{:doc "The full path to the medium test cases file."}
  testcases-medium-file "/Users/evansp2/Documents/software-projects/Google-Code-Jam/data/testcases/testcases.medium.txt")

(def
  ^{:doc "The full path to the large test cases file."}
  testcases-large-file "/Users/evansp2/Documents/software-projects/Google-Code-Jam/data/testcases/testcases.large.txt")

(defn print-processed-test-case [testCase]
  "Prints the processed test case to standard out."
  (println
   (str "Case #" (str (:test-case-num testCase))
        ": "
        (clojure.string/replace
         (clojure.string/replace
          (str (:indexes-of-items-to-buy testCase)) "[" "") "]" ""))) nil)

(def
  ^{:doc "The processed [small] test cases."}
  processed-small (process-testcases (read-testcases-from-file testcases-small-file)))

(def
  ^{:doc "The processed [medium] test cases."}
  processed-medium (process-testcases (read-testcases-from-file testcases-medium-file)))

(def
  ^{:doc "The processed [large] test cases."}
  processed-large (process-testcases (read-testcases-from-file testcases-large-file)))

(defn run 
  "Prints each processed test case to standard out."
  ([] (run processed-small))
  ([inputSet]
     (map print-processed-test-case inputSet)))
