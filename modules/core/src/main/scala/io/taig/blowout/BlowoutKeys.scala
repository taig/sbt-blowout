package io.taig.blowout

import sbt._

trait BlowoutKeys {
  lazy val blowoutHeader = settingKey[Map[String, String]](
    "A comment header that is added to installed files, depending on their file extension"
  )

  lazy val blowoutGenerators = settingKey[List[BlowoutGenerator]](
    "Generators that describe where files should be installed to by blowoutGenerate and what content they should have"
  )

  lazy val blowoutGenerate = taskKey[List[File]]("Install the generator definitions from blowoutGenerators")

  lazy val blowoutCheck =
    taskKey[Unit]("Check if the current installations are up to date and fail if they have to be regenerated")
}

object BlowoutKeys extends BlowoutKeys
