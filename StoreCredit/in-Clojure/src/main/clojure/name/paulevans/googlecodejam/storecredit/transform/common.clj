(ns name.paulevans.googlecodejam.storecredit.transform.common
  (:use [clojure.string :only (split)]))

(defstruct test-case-model :test-case-num :store-credit :items)

(def
  ^{:doc "The start index of the set of items."}
  start-index-of-items 2)

(defn parse-raw-testcase [testCaseNum rawTestCase]
  "Takes as input a test case number as well as a list of 'raw data' that
  represents the test case in the following format:

  (N1,  ; the store credit value
   N2,  ; the number of items (this value is ignored as it is not necessary)
   N3,  ; the 1st item of the set of items
   N4,  ; the 2nd item of the set of items
   NN)  ; the N-2 item of the set of items

  This function returns a map (conforming to test-case-model) of the following
  format:

  {:test-case-num testCaseNum,
   :store-credit N1,
   :items [N3 N4 NN]}"
  (let [testCase (struct test-case-model)]
    (assoc testCase 
           :test-case-num testCaseNum
           :store-credit (Integer/parseInt (first rawTestCase))
           :items (vec (map #(Integer/parseInt %)
                            (split (nth rawTestCase start-index-of-items) 
                                   #" "))))))

