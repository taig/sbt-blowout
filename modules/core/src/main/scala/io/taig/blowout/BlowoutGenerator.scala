package io.taig.blowout

import sbt.File

final case class BlowoutGenerator(
    target: File,
    content: () => String,
    comment: BlowoutComment = BlowoutComment.Disabled
)

object BlowoutGenerator {
  def lzy(target: File, content: => String, comment: BlowoutComment = BlowoutComment.Disabled): BlowoutGenerator =
    BlowoutGenerator(target, () => content, comment)

  def strict(target: File, content: String, comment: BlowoutComment = BlowoutComment.Disabled): BlowoutGenerator =
    BlowoutGenerator(target, () => content, comment)
}
