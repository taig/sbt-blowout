package io.taig.blowout

import sbt.Keys.state
import sbt.io.IO
import sbt.plugins.JvmPlugin
import sbt.{io => _, _}

import java.io.FileNotFoundException

object BlowoutPlugin extends AutoPlugin {
  object autoImport extends BlowoutKeys {
    type BlowoutGenerator = io.taig.blowout.BlowoutGenerator
    val BlowoutGenerator = io.taig.blowout.BlowoutGenerator
  }

  import autoImport._

  override def requires: Plugins = JvmPlugin

  override def trigger: PluginTrigger = allRequirements

  override def projectSettings: Seq[Def.Setting[_]] = Def.settings(
    blowoutGenerators := Nil,
    blowoutHeader := { file =>
      val message = List(
        "This file was automatically generated by sbt-blowout and should not be edited manually.",
        "Instead, run blowoutGenerate after making the desired changes to your build definition."
      )

      PartialFunction.condOpt((file.name, file.ext)) { case ("Dockerfile", "") | (_, "conf" | "yml" | "yaml") =>
        message.map("# " + _).mkString("\n")
      }
    },
    blowoutRender := { generator =>
      blowoutHeader.value(generator.target).map(_ + "\n").getOrElse("") + generator.content()
    },
    blowoutGenerate := {
      blowoutGenerators.value.map { generator =>
        IO.write(generator.target, blowoutRender.value(generator))
        generator.target
      }
    },
    blowoutCheck := {
      val log = state.value.log

      blowoutGenerators.value.foreach { generator =>
        try {
          val expected = blowoutRender.value(generator)
          val actual = IO.read(generator.target)

          if (actual != expected) {
            log.error(s"Expected:\n$expected")
            log.error(s"Actual:\n$actual")
            sys.error(
              s"${generator.target} does not contain contents that would have been generated by sbt-blowout; try running blowoutGenerate"
            )
          }
        } catch {
          case _: FileNotFoundException => sys.error(s"${generator.target} does not exist; try running blowoutGenerate")
        }
      }
    }
  )
}
