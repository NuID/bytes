(ns nuid.bytes.lib
  (:require
   [nuid.exception :as exception]))

(def charsets
  "Intersection of [node's buffer encodings](https://nodejs.org/api/buffer.html#buffer_buffers_and_character_encodings)
  and the [jvm's charsets](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html)"
  {:charset/ascii   #?(:clj "US-ASCII"   :cljs "ascii")
   :charset/utf8    #?(:clj "UTF-8"      :cljs "utf8")
   :charset/utf16le #?(:clj "UTF-16LE"   :cljs "utf16le")
   :charset/ucs2    #?(:clj "UTF-16LE"   :cljs "utf16le")
   :charset/latin1  #?(:clj "ISO-8859-1" :cljs "latin1")
   :charset/binary  #?(:clj "ISO-8859-1" :cljs "binary")
   :charset/utf16   #?(:clj "UTF-16"     :cljs "utf16le")
   :charset/utf16be #?(:clj "UTF-16BE"   :cljs nil)})

(defn unsupported!
  [charset]
  (let [msg (clojure.core/str "Unsupported charset: " charset)]
    (exception/throw! {::exception/message msg})))
