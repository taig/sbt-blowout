package io.taig.blowout

import sbt.File

final case class BlowoutGenerator(target: File, content: () => String)

object BlowoutGenerator {
  def lzy(target: File, content: => String): BlowoutGenerator = BlowoutGenerator(target, () => content)

  def strict(target: File, content: String): BlowoutGenerator = BlowoutGenerator(target, () => content)
}
