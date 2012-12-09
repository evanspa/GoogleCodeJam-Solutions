(ns name.paulevans.googlecodejam.storecredit.transform.from_file
  (:import (java.io BufferedReader FileReader))
  (:use [clojure.string :only (split)]
        [name.paulevans.googlecodejam.storecredit.transform.common :only (parse-raw-testcase)]))

(defn read-testcases-from-file [fileName]
  "Reads-in the contents of a file, fileName, parses the contents and returns
  the data from the file in the form of:

  ( {:test-case-num N1, :store-credit C1, :items [i1 i2 i3...]},
    {:test-case-num N2, :store-credit C2, :items [i1 i2 i3 i4 i5...]},
    {...}, {...}, ...)

  The contents of the file would be of the form ('//' comments added):

  3                     // the number of test cases in this file
  100                   // the store credit value of the 1st test case 
  3                     // the count of items of the 1st test case
  5 75 25               // the items of the 1st test case
  200                   // the store credit value of the 2nd test case
  7                     // the count of items of the 2nd test case
  150 24 79 50 88 345 3 // the items of the 2nd test case
  8                     // the store credit value of the 3rd test case
  8                     // the count of items of the 3rd test case
  2 1 9 4 4 56 90 3     // the items of the 3rd test case

  The purpose of this function is to transform the data, as it is structured
  and formatted in the file, to a generic format that is suitable for
  further processing."
  (with-open [rdr (BufferedReader. (FileReader. fileName))]
    (let [fileContents (line-seq rdr)] 
      ; Here's what fileContents now contains (following the example in the
      ; docs of this function):
      ; (3 100 3 5 75 25 200 7 150 24 79 50 88 345 3 8 8 2 1 9 4 4 56 90 3)
      (let [rawTestCases (partition 3 (rest fileContents))]
        (loop [count 0 testCase (first rawTestCases) parsedTestCases '()]
          (if (nil? testCase)
            parsedTestCases
            (recur 
              (inc count)
              (nth rawTestCases (inc count) nil)
              (conj parsedTestCases (parse-raw-testcase 
                                      (inc count) 
                                      testCase)))))))))
