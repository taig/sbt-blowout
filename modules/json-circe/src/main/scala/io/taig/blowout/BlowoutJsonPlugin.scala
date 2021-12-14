package io.taig.blowout

import sbt.{AutoPlugin, PluginTrigger, Plugins}

object BlowoutJsonPlugin extends AutoPlugin {
  object autoImport {
    val BlowoutJsonGenerator = io.taig.blowout.BlowoutJsonGenerator
  }

  override def requires: Plugins = BlowoutPlugin

  override def trigger: PluginTrigger = noTrigger
}
