package io.taig.blowout

import io.circe.Json
import io.circe.yaml.Printer
import sbt.File

object BlowoutYamlGenerator {
  val DefaultPrinter: Printer = Printer.spaces2.copy(preserveOrder = true, splitLines = false)

  def apply(
      target: File,
      content: () => Json,
      printer: Printer = DefaultPrinter,
      comment: BlowoutComment = BlowoutComment.Hash
  ): BlowoutGenerator = BlowoutGenerator(target, () => printer.pretty(content()), comment)

  def lzy(
      target: File,
      content: => Json,
      printer: Printer = DefaultPrinter,
      comment: BlowoutComment = BlowoutComment.Hash
  ): BlowoutGenerator = BlowoutGenerator(target, () => printer.pretty(content), comment)

  def strict(
      target: File,
      content: Json,
      printer: Printer = DefaultPrinter,
      comment: BlowoutComment = BlowoutComment.Hash
  ): BlowoutGenerator = BlowoutGenerator.strict(target, printer.pretty(content), comment)
}
