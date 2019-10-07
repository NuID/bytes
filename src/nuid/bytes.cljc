(ns nuid.bytes
  (:refer-clojure :exclude [bytes? str concat])
  (:require
   [nuid.exception :as exception]
   #?@(:cljs [["buffer" :as b]])))

(defprotocol Bytesable
  (from [x] [x charset]))

(defprotocol Bytes
  (str [b] [b charset]))

(def charsets
  "Intersection of [node's buffer encodings](https://nodejs.org/api/buffer.html#buffer_buffers_and_character_encodings)
  and the [jvm's charsets](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html)"
  {:ascii   #?(:clj "US-ASCII"   :cljs "ascii")
   :utf8    #?(:clj "UTF-8"      :cljs "utf8")
   :utf16le #?(:clj "UTF-16LE"   :cljs "utf16le")
   :ucs2    #?(:clj "UTF-16LE"   :cljs "utf16le")
   :latin1  #?(:clj "ISO-8859-1" :cljs "latin1")
   :binary  #?(:clj "ISO-8859-1" :cljs "binary")
   :utf16   #?(:clj "UTF-16"     :cljs "utf16le")
   :utf16be #?(:clj "UTF-16BE"   :cljs nil)})

(defn unsupported! [charset]
  (let [msg (clojure.core/str "Unsupported charset: " charset)]
    (exception/throw! {:message msg})))

(defn bytes? [x] (satisfies? Bytes x))

(defn concat
  [& bs]
  #?(:clj (with-open [out (java.io.ByteArrayOutputStream.)]
            (doseq [b bs]
              (.write out b 0 (count b)))
            (.toByteArray out))
     :cljs (exception/throw!
            {:message "bytes/concat not implemented in CLJS"})))

#?(:clj
   (extend-protocol Bytesable
     java.lang.String
     (from
       ([x] (from x :utf8))
       ([x charset]
        (if-let [cs (charsets charset)]
          (.getBytes x cs)
          (unsupported! charset))))))

#?(:clj
   (extend-protocol Bytes
     (type (byte-array 0))
     (str
       ([b] (str b :utf8))
       ([b charset]
        (if-let [cs (charsets charset)]
          (String. b cs)
          (unsupported! charset))))))

#?(:cljs
   (extend-protocol Bytesable
     string
     (from
       ([x] (from x :utf8))
       ([x charset]
        (if-let [cs (charsets charset)]
          (b/Buffer.from x cs)
          (unsupported! charset))))

     default
     (from
       ([x]   (b/Buffer.from x))
       ([x _] (b/Buffer.from x)))))

#?(:cljs
   (extend-protocol Bytes
     b/Buffer
     (str
       ([b] (str b :utf8))
       ([b charset]
        (if-let [cs (charsets charset)]
          (.toString b cs)
          (unsupported! charset))))))

#?(:cljs (def exports #js {}))
