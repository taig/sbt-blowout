package io.taig.blowout

import io.circe.Json
import io.circe.yaml.Printer
import sbt.File

object BlowoutYamlGenerator {
  def apply(target: File, content: () => Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutGenerator(target, () => printer.pretty(content()))

  def strict(target: File, content: Json, printer: Printer = Printer.spaces2): BlowoutGenerator =
    BlowoutYamlGenerator(target, () => content, printer)
}
