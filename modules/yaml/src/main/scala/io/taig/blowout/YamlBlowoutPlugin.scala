package io.taig.blowout

import sbt.plugins.JvmPlugin
import sbt.{AutoPlugin, PluginTrigger, Plugins}

object YamlBlowoutPlugin extends AutoPlugin {
  object autoImport extends BlowoutKeys {
    val YamlBlowoutGenerator = io.taig.blowout.YamlBlowoutGenerator
  }

  override def requires: Plugins = JvmPlugin

  override def trigger: PluginTrigger = allRequirements
}
