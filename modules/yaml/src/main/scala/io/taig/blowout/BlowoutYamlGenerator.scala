package io.taig.blowout

import io.circe.Json
import io.circe.yaml.syntax._
import sbt.File

object BlowoutYamlGenerator {
  def apply(target: File, content: () => Json): BlowoutGenerator =
    BlowoutGenerator(target, () => content().asYaml.spaces2)

  def strict(target: File, content: Json): BlowoutGenerator = BlowoutYamlGenerator(target, () => content)
}