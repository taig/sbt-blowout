package io.taig.blowout

import io.circe.Json
import io.circe.yaml.Printer
import sbt.File

object BlowoutYamlGenerator {
  val DefaultPrinter: Printer = Printer.spaces2.copy(preserveOrder = true, splitLines = false)

  def apply(target: File, content: () => Json, printer: Printer = DefaultPrinter): BlowoutGenerator =
    BlowoutGenerator(target, () => printer.pretty(content()))

  def lzy(target: File, content: => Json, printer: Printer = DefaultPrinter): BlowoutGenerator =
    BlowoutGenerator(target, () => printer.pretty(content))

  def strict(target: File, content: Json, printer: Printer = DefaultPrinter): BlowoutGenerator =
    BlowoutYamlGenerator(target, () => content, printer)
}
