package io.taig.blowout

import sbt.File

final case class BlowoutGenerator(target: File, content: () => String)

object BlowoutGenerator {
  def strict(target: File, content: String): BlowoutGenerator = new BlowoutGenerator(target, () => content)
}
