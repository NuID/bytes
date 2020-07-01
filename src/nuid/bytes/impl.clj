(ns nuid.bytes.impl
  (:require
   [nuid.bytes.lib :as lib]
   [nuid.bytes.proto :as proto]))

(extend-protocol proto/Bytesable
  java.lang.String
  (from
    ([x] (proto/from x :charset/utf8))
    ([x charset]
     (if-let [cs (lib/charsets charset)]
       (.getBytes x cs)
       (lib/unsupported! charset)))))

(extend-protocol proto/Bytes
  (type (byte-array 0))
  (str
    ([b] (proto/str b :charset/utf8))
    ([b charset]
     (if-let [cs (lib/charsets charset)]
       (String. b cs)
       (lib/unsupported! charset)))))
