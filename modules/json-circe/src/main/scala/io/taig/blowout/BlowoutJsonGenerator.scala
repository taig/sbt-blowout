package io.taig.blowout

import io.circe.Json
import io.circe.Printer
import sbt.File

object BlowoutJsonGenerator {
  def apply(target: File, content: () => Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutGenerator(target, () => printer.print(content()), comment = BlowoutComment.Disabled)

  def lzy(target: File, content: => Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutGenerator(target, () => printer.print(content), comment = BlowoutComment.Disabled)

  def strict(target: File, content: Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutGenerator.strict(target, printer.print(content), comment = BlowoutComment.Disabled)
}
