# nuid.bytes

Cross-platform byte (de)serialization and conversion.

## Requirements

[`jvm`](https://www.java.com/en/download/), [`node + npm`](https://nodejs.org/en/download/), [`clj`](https://clojure.org/guides/getting_started), [`shadow-cljs`](https://shadow-cljs.github.io/docs/UsersGuide.html#_installation)

## From Clojure and ClojureScript

### tools.deps:

`{nuid/bytes {:git/url "https://github.com/nuid/bytes" :sha "..."}`

### usage:

```
$ clj # or shadow-cljs node-repl
=> (require '[nuid.bytes :as bytes])
=> (def b (bytes/from "🐴")) ;; defaults to utf8
=> (bytes/to b) ;; => "🐴"
=> (bytes/to b :utf16le) ;; => "鿰뒐"
=> (def b2 (bytes/from "🐴" :utf16le))
=> (bytes/to b2 :utf16le) ;; => "🐴"
```

## Notes

Because this library exists as a common interface over exception facilities, it may only be useful as a functional API to the underlying facilities in the host platform. The below is included just in case.

## From JavaScript

In JavaScript, this library relies upon and mimics a subset of the `Buffer` API in both [node](https://nodejs.org/api/buffer.html) and the [browser](https://github.com/feross/buffer)

### node:

```
$ shadow-cljs release node
$ node
> var B = require('./target/node/nuid_bytes');
> var b = B.from("🐴");
> B.toString(b);
> B.toString(b, "utf16le");
```

### browser:

```
$ shadow-cljs release browser
## go use ./target/browser/nuid_bytes.js in a browser script
```

## From Java

In Java, this library relies on the [`String`](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) and [`Charset`](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html) classes. To call `nuid.bytes` from Java or other JVM languages, use one of the recommended interop strategies ([var/IFn](https://clojure.org/reference/java_interop#_calling_clojure_from_java) or [uberjar/aot](https://push-language.hampshire.edu/t/calling-clojure-code-from-java/865)). Doing so may require modifications or additions to the API for convenience.

## From CLR

Coming soon.

## Notes

The purpose of `nuid.bytes` and sibling `nuid` libraries is to abstract over platform-specific differences and provide a common interface to fundamental dependencies. This allows us to express dependent logic once in pure Clojure(Script), and use it from each of the host platforms (Java, JavaScript, CLR). Along with [`tools.deps`](https://clojure.org/guides/deps_and_cli), this approach yields the code-sharing, circular-dependency avoidance, and local development benefits of a monorepo, with the modularity and orthogonality of an isolated library.

## Contributing

Install [`git-hooks`](https://github.com/icefox/git-hooks) and fire away. Make sure not to get bitten by [`externs`](https://clojurescript.org/guides/externs) if modifying `npm` dependencies.

### formatting:

```
$ clojure -A:cljfmt            # check
$ clojure -A:cljfmt:cljfmt/fix # fix
```

### dependencies:

```
## check
$ npm outdated 
$ clojure -A:depot

## update
$ npm upgrade -s
$ clojure -A:depot:depot/update
```
