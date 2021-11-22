# sbt blowout

[![CI & CD](https://github.com/taig/sbt-blowout/actions/workflows/main.yml/badge.svg)](https://github.com/taig/sbt-blowout/actions/workflows/main.yml)
[![sbt-blowout-core Scala version support](https://index.scala-lang.org/taig/sbt-blowout/sbt-blowout-core/latest-by-scala-version.svg?targetType=Sbt)](https://index.scala-lang.org/taig/sbt-blowout/sbt-blowout-core)

> sbt plugin to generate configuration files at build time

## Installation

```scala
addSbtPlugin("io.taig" % "sbt-blowout-core" % "[version]")
addSbtPlugin("io.taig" % "sbt-blowout-json" % "[version]")
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

2. Install generators via sbt

   ```shell
   sbt blowoutGenerate
   ```

3. Commit and push generated files, ideally verifying in your CI that your sbt-blowout installations are up to date

   ```shell
   sbt blowoutCheck
   ```
   
## Guide

sbt-blowout allows generating arbitrary file formats and extensions with the `sbt-blowout-core` module. Other modules (like `sbt-blowout-yaml`) provide additional helpers on top of that to ease generation of specific file formats.

### YAML

The YAML module uses [circe-yaml](https://github.com/circe/circe-yaml) in order to provide the convenient circe JSON builders before converting the AST to YAML.

```scala
blowoutGenerators += BlowoutYamlGenerator.strict(
   file("my-config.yml"),
   content = Json.obj("version" := scalaVersion.value)
)
```

### JSON

The JSON module uses [circe](https://github.com/circe/circe) in order to provide the convenient circe JSON builders.

```scala
blowoutGenerators += BlowoutJsonGenerator.strict(
   file("my-config.json"),
   content = Json.obj("version" := scalaVersion.value)
)
```

## Acknowledgements

This plugin is highly inspired by [sbt-github-actions](https://github.com/djspiewak/sbt-github-actions) which provides a very similar workflow, but is limited to generating GitHub Actions workflow configuration files.