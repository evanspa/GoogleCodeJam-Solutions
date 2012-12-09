(ns name.paulevans.googlecodejam.storecredit.main
  (:use [name.paulevans.googlecodejam.storecredit.core 
         :only (process-testcases)]
        [name.paulevans.googlecodejam.storecredit.transform.from_file 
         :only (read-testcases-from-file)]))

(def 
  ^{:doc "Root folder containing the test cases.  Yeah, this is a little
sloppy; but not worrying about making this perfect."}
  test-cases-folder "/Users/evansp2/Documents/GitHub-repos/GoogleCodeJam-Solutions/StoreCredit/in-Clojure/data/testcases/")  

(def
  ^{:doc "The full path to the small test cases file."}
  testcases-small-file (str test-cases-folder "testcases.small.txt"))

(def
  ^{:doc "The full path to the medium test cases file."}
  testcases-medium-file (str test-cases-folder "testcases.medium.txt"))

(def
  ^{:doc "The full path to the large test cases file."}
  testcases-large-file (str test-cases-folder "testcases.large.txt"))

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
  processed-small (process-testcases
                    (read-testcases-from-file testcases-small-file)))

(def
  ^{:doc "The processed [medium] test cases."}
  processed-medium (process-testcases 
                     (read-testcases-from-file testcases-medium-file)))

(def
  ^{:doc "The processed [large] test cases."}
  processed-large (process-testcases 
                    (read-testcases-from-file testcases-large-file)))

(defn run 
  "Prints each processed test case to standard out."
  ([] (run processed-small))
  ([inputSet]
     (map print-processed-test-case inputSet)))
