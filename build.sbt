val Version = new {
  val CirceYaml = "0.14.1"
  val Scala = "2.12.15"
}

ThisBuild / developers := List(Developer("taig", "Niklas Klein", "mail@taig.io", url("https://taig.io/")))
ThisBuild / dynverVTagPrefix := false
ThisBuild / homepage := Some(url("https://github.com/taig/scala-linguist/"))
ThisBuild / licenses := List("MIT" -> url("https://raw.githubusercontent.com/taig/scala-linguist/main/LICENSE"))
ThisBuild / scalaVersion := Version.Scala
ThisBuild / versionScheme := Some("early-semver")

lazy val core = project
  .in(file("modules/core"))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-blowout-core",
    scriptedBufferLog := false,
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    }
  )

lazy val yaml = project
  .in(file("modules/yaml"))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-blowout-yaml",
    libraryDependencies ++=
      "io.circe" %% "circe-yaml" % Version.CirceYaml ::
        Nil
  )
  .dependsOn(core)
