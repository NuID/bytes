(ns nuid.bytes.proto
  (:refer-clojure :exclude [bytes? str])
  (:require
   #?@(:clj  [[clojure.alpha.spec :as s]]
       :cljs [[clojure.spec.alpha :as s]])))

(defprotocol Bytesable
  (from
    [x]
    [x charset]))

(defprotocol Bytes
  (str
    [b]
    [b charset]))

(s/def ::bytes
  (fn [x] (satisfies? Bytes x)))
