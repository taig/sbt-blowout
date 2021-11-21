# sbt blowout

[![CI & CD](https://github.com/taig/sbt-blowout/actions/workflows/main.yml/badge.svg)](https://github.com/taig/sbt-blowout/actions/workflows/main.yml)
[![sbt-blowout Scala version support](https://index.scala-lang.org/taig/sbt-blowout/sbt-blowout-core/latest-by-scala-version.svg)](https://index.scala-lang.org/taig/sbt-blowout/sbt-blowout-core)

> An sbt plugin to generate configuration files at build time

## Installation

```scala
addSbtPlugin("io.taig" % "sbt-blowout-core" % "[version]")
addSbtPlugin("io.taig" % "sbt-blowout-yaml" % "[version]")
```

## Quickstart

1. Register generators

   ```scala
   blowoutGenerators += BlowoutGenerator.strict(
     file("my-config.yml"),
     content = s"""version: ${scalaVersion.value}""".stripMargin
   )
   ```

2. Install generators in sbt

   ```shell
   sbt blowoutGenerate
   ```

3. Commit and push generated files, ideally verifying in your CI that your sbt-blowout installations are up to date

   ```shell
   sbt blowoutCheck
   ```