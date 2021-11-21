package io.taig.blowout

import io.circe.Json
import sbt.File

object BlowoutJsonGenerator {
  def apply(target: File, content: () => Json): BlowoutGenerator = BlowoutGenerator(target, () => content().spaces2)

  def strict(target: File, content: Json): BlowoutGenerator = BlowoutJsonGenerator(target, () => content)
}
