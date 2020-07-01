(ns nuid.bytes.impl
  (:require
   [nuid.bytes.lib :as lib]
   [nuid.bytes.proto :as proto]
   ["buffer" :as b]))

(extend-protocol proto/Bytesable
  string
  (from
    ([x] (proto/from x :charset/utf8))
    ([x charset]
     (if-let [cs (lib/charsets charset)]
       (b/Buffer.from x cs)
       (lib/unsupported! charset))))

  default
  (from
    ([x]   (b/Buffer.from x))
    ([x _] (b/Buffer.from x))))

(extend-protocol proto/Bytes
  b/Buffer
  (str
    ([b] (proto/str b :charset/utf8))
    ([b charset]
     (if-let [cs (lib/charsets charset)]
       (.toString b cs)
       (lib/unsupported! charset)))))
