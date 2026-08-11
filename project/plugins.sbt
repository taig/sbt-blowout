addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.12.0")
addSbtPlugin("io.taig" % "sbt-houserules" % "0.12.0")

// sbt-blowout generates its own GitHub Action workflows, but an sbt 2 meta build can only load
// _sbt2_3 plugins and the first such release is the one this build produces. Compile the plugin
// sources right here instead of resolving a published artifact; blowoutCheck then always validates
// against HEAD. Keep the dependencies below in sync with the `core` and `yamlCirce` modules.
libraryDependencies ++=
  "io.github.java-diff-utils" % "java-diff-utils" % "4.17" ::
    "io.circe" %% "circe-yaml" % "0.15.2" ::
    Nil

Compile / unmanagedSourceDirectories ++= {
  val modules = baseDirectory.value.getParentFile / "modules"
  Seq(
    modules / "core" / "src" / "main" / "scala",
    modules / "yaml-circe" / "src" / "main" / "scala"
  )
}
