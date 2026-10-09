enablePlugins(BlowoutPlugin)

blowoutGenerators += BlowoutGenerator.strict(file("foobar.yml"), content = "foobar")

scalaVersion := "3.9.0"