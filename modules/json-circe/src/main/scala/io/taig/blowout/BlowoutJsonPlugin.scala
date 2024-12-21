package io.taig.blowout

import sbt.AutoPlugin
import sbt.PluginTrigger
import sbt.Plugins

object BlowoutJsonPlugin extends AutoPlugin {
  object autoImport {
    val BlowoutJsonGenerator = io.taig.blowout.BlowoutJsonGenerator
  }

  override def requires: Plugins = BlowoutPlugin

  override def trigger: PluginTrigger = noTrigger
}
