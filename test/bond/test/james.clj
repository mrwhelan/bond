(ns bond.test.james
  (:require [clojure.test :refer (deftest is)]
            [bond.james :as bond]))


(defn foo [x] (* 2 x))

(defn bar [x] (println "bar!") (* 2 x))

(deftest spy-works []
  (bond/with-spy [foo]
    (foo 1)
    (foo 2)
    (is (= 2 (-> foo bond/calls count)))))

(deftest stub-works []
  (is (= ""
         (with-out-str
           (bond/with-stub [bar]
             (bar 3))))))