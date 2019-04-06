(ns nuid.bytes
  (:require
   [nuid.exception :as exception]
   #?@(:cljs [["buffer" :as b]])))

(def charsets
  "Intersection of [node's Buffer encodings](https://nodejs.org/api/buffer.html#buffer_buffers_and_character_encodings)
  and the [JVM's charsets](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html)"
  {:ascii #?(:clj "US-ASCII" :cljs "ascii")
   :utf8 #?(:clj "UTF-8" :cljs "utf8")
   :utf16le #?(:clj "UTF-16LE" :cljs "utf16le")
   :ucs2 #?(:clj "UTF-16LE" :cljs "utf16le")
   :latin1 #?(:clj "ISO-8859-1" :cljs "latin1")
   :binary #?(:clj "ISO-8859-1" :cljs "binary")
   :utf16 #?(:clj "UTF-16" :cljs "utf16le")
   :utf16be #?(:clj "UTF-16BE" :cljs nil)})

(defn unsupported! [charset]
  (exception/throw! {:message (str "Unsupported charset: " charset)}))

(defn from
  ([s] (from s :utf8))
  ([s charset]
   (if-let [cs (charsets charset)]
     #?(:clj (.getBytes s cs)
        :cljs (b/Buffer.from s cs))
     (unsupported! charset))))

(defn to
  ([b] (to b :utf8))
  ([b charset]
   (if-let [cs (charsets charset)]
     #?(:clj (String. b cs)
        :cljs (.toString b cs))
     (unsupported! charset))))

#?(:cljs (def exports #js {:toString #(to %1 (or (keyword %2) :utf8))
                           :from #(from %1 (or (keyword %2) :utf8))
                           :unsupported (comp unsupported! keyword)
                           :charsets (clj->js charsets)}))
