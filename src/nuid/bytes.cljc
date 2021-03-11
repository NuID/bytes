(ns nuid.bytes
  (:refer-clojure :exclude [bytes? str])
  (:require
   [clojure.spec.alpha :as s]
   [nuid.bytes.impl]
   [nuid.bytes.proto :as proto]))

(s/def ::bytes ::proto/bytes)

(def bytes?
  (partial s/valid? ::bytes))

(defn from
  ([x]         (proto/from x))
  ([x charset] (proto/from x charset)))

(defn str
  ([b]         (proto/str b))
  ([b charset] (proto/str b charset)))
