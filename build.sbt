val Version = new {
  val Circe = "0.14.1"
  val CirceYaml = "0.14.1"
  val Scala = "2.12.15"
}

ThisBuild / developers := List(Developer("taig", "Niklas Klein", "mail@taig.io", url("https://taig.io/")))
ThisBuild / dynverVTagPrefix := false
ThisBuild / homepage := Some(url("https://github.com/taig/sbt-blowout/"))
ThisBuild / licenses := List("MIT" -> url("https://raw.githubusercontent.com/taig/sbt-blowout/main/LICENSE"))
ThisBuild / scalaVersion := Version.Scala
ThisBuild / versionScheme := Some("early-semver")

blowoutGenerators ++= {
  val workflows = file(".github") / "workflows"
  BlowoutYamlGenerator.strict(workflows / "main.yml", GithubActionsGenerator.main) ::
    BlowoutYamlGenerator.strict(workflows / "branches.yml", GithubActionsGenerator.branches) ::
    Nil
}

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
