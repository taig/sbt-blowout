ThisBuild / developers := List(Developer("taig", "Niklas Klein", "mail@taig.io", uri("https://taig.io/")))
ThisBuild / dynverVTagPrefix := false
ThisBuild / homepage := Some(uri("https://github.com/taig/sbt-blowout/"))
ThisBuild / licenses := List("MIT" -> uri("https://raw.githubusercontent.com/taig/sbt-blowout/main/LICENSE"))
ThisBuild / scalaVersion := Version.Scala
ThisBuild / versionScheme := Some("early-semver")

lazy val root = project
  .in(file("."))
  .aggregate(core, jsonCirce, yamlCirce)
  .enablePlugins(BlowoutYamlPlugin)
  .settings(noPublishSettings)
  .settings(
    blowoutGenerators ++= {
      val workflows = file(".github") / "workflows"
      BlowoutYamlGenerator.lzy(workflows / "main.yml", GitHubActionsGenerator.main(Version.Java)) ::
        BlowoutYamlGenerator.lzy(workflows / "pull-request.yml", GitHubActionsGenerator.pullRequest(Version.Java)) ::
        BlowoutYamlGenerator.lzy(workflows / "taig.yml", GitHubActionsGenerator.tag(Version.Java)) ::
        Nil
    },
    name := "sbt-blowout"
  )

lazy val core = project
  .in(file("modules/core"))
  .enablePlugins(SbtPlugin)
  .settings(
    libraryDependencies ++=
      "io.github.java-diff-utils" % "java-diff-utils" % Version.JavaDiffUtils ::
        Nil,
    name := "sbt-blowout-core",
    scriptedBufferLog := false,
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    }
  )

lazy val jsonCirce = project
  .in(file("modules/json-circe"))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-blowout-json-circe",
    libraryDependencies ++=
      "io.circe" %% "circe-core" % Version.Circe ::
        Nil
  )
  .dependsOn(core)

lazy val yamlCirce = project
  .in(file("modules/yaml-circe"))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-blowout-yaml-circe",
    libraryDependencies ++=
      "io.circe" %% "circe-yaml" % Version.CirceYaml ::
        Nil
  )
  .dependsOn(core)
