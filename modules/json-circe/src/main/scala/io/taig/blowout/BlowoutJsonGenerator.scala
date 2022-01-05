package io.taig.blowout

import io.circe.{Json, Printer}
import sbt.File

object BlowoutJsonGenerator {
  def apply(target: File, content: () => Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutGenerator(target, () => printer.print(content()))

  def lzy(target: File, content: => Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutGenerator(target, () => printer.print(content))

  def strict(target: File, content: Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutJsonGenerator(target, () => content, printer)
}
