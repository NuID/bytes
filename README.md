<p align="right"><a href="https://nuid.io"><img src="https://nuid.io/svg/logo.svg" width="20%"></a></p>

# nuid.bytes

Cross-platform byte (de)serialization and conversion.

## Requirements

[`jvm`](https://www.java.com/en/download/), [`node + npm`](https://nodejs.org/en/download/), [`clj`](https://clojure.org/guides/getting_started), [`shadow-cljs`](https://shadow-cljs.github.io/docs/UsersGuide.html#_installation)

## Clojure and ClojureScript

### tools.deps:

`{nuid/bytes {:git/url "https://github.com/nuid/bytes" :sha "..."}}`

### usage:

```
$ clj # or shadow-cljs node-repl
=> (require '[nuid.bytes :as bytes])
=> (def b (bytes/from "🐴"))                   ;; defaults to utf8
=> (bytes/str b)                               ;; => "🐴"
=> (bytes/str b :charset/utf16le)              ;; => "鿰뒐"
=> (def b2 (bytes/from "🐴" :charset/utf16le))
=> (bytes/str b2 :charset/utf16le)             ;; => "🐴"
```

## Licensing

Apache v2.0 or MIT
