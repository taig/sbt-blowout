package io.taig.blowout

import sbt.plugins.JvmPlugin
import sbt.{AutoPlugin, PluginTrigger, Plugins}

object BlowoutJsonPlugin extends AutoPlugin {
  object autoImport {
    val BlowoutJsonGenerator = io.taig.blowout.BlowoutJsonGenerator
  }

  override def requires: Plugins = JvmPlugin

  override def trigger: PluginTrigger = allRequirements
}
