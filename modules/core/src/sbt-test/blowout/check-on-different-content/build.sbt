TaskKey[Unit]("alterGeneratedFile") := {
  IO.write(file("foobar.yml"), "fizbuz")
}

enablePlugins(BlowoutPlugin)

blowoutGenerators += BlowoutGenerator.strict(file("foobar.yml"), content = "foobar")

scalaVersion := "3.1.0"