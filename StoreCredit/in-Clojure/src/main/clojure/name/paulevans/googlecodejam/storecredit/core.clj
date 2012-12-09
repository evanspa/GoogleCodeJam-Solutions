(ns name.paulevans.googlecodejam.storecredit.core)

(defn calculate-items-to-buy [storeCredit items]
  "Takes as input a test case map of the form:

  {:test-case-num N, :store-credit N, :items [N1 N2 N3 ...]}

  and calculates which 2 items add-up to the value of the store credit.  What is 
  returned is a vector of the indexes (one-based) of the 2 items. If there
  aren't 2 items that add-up to store-credit, then nil is returned."
  (let [numItems (count items)]
    (loop [outer 0 inner 1]
      (cond
        (= outer (dec numItems)) nil
        (= storeCredit (+ (nth items outer) (nth items inner))) [(inc outer) (inc inner)]
        :else
          (cond (= inner (dec numItems)) (recur (inc outer) (inc (inc outer)))
          :else (recur outer (inc inner)))))))

(defn process-testcases [testCases]  
  "Takes as input a list of test cases in the form:

  ({:test-case-num 1, :store-credit 100, :items [5 75 25]}
   {:test-case-num 2, :store-credit 200, :items [150 24 79 50 88 345 3]}
   {:test-case-num 3, :store-credit 8,   :items [2 1 9 4 4 56 90 3]})
  
    and returns as output:

  ({:test-case-num 1, :store-credit 100, :items [5 75 25]
    :indexes-of-items-to-buy [2 3]}
   {:test-case-num 2, :store-credit 200, :items [150 24 79 50 88 345 3]
    :indexes-of-items-to-buy [1 4]}
   {:test-case-num 3, :store-credit 8,   :items [2 1 9 4 4 56 90 3] 
    :indexes-of-items-to-buy [4 5]})"
  (loop [loop 0 count (count testCases) processedTestCases '()]
    (if (= loop count)
      processedTestCases
      (let [currentTestCase (nth testCases loop)]
        (recur 
          (inc loop)
          count
          (conj processedTestCases
                (assoc currentTestCase :indexes-of-items-to-buy
                       (calculate-items-to-buy 
                         (:store-credit currentTestCase)
                         (:items currentTestCase)))))))))
