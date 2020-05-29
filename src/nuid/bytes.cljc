(ns nuid.bytes
  (:refer-clojure :exclude [bytes? str])
  (:require
   [nuid.bytes.impl]
   [nuid.bytes.proto :as proto]
   #?@(:clj  [[clojure.alpha.spec :as s]]
       :cljs [[clojure.spec.alpha :as s]])))

(s/def ::bytes ::proto/bytes)

(def bytes?
  (partial s/valid? ::bytes))

(defn from
  ([x]         (proto/from x))
  ([x charset] (proto/from x charset)))

(defn str
  ([b]         (proto/str b))
  ([b charset] (proto/str b charset)))
