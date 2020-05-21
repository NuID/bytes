(ns nuid.bytes
  (:refer-clojure :exclude [bytes? str])
  (:require
   [nuid.bytes.impl]
   [nuid.bytes.proto :as proto]))

(def from   proto/from)
(def str    proto/str)
(def bytes? proto/bytes?)
