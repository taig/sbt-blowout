package io.taig.blowout

import sbt.plugins.JvmPlugin
import sbt.{AutoPlugin, PluginTrigger, Plugins}

object BlowoutYamlPlugin extends AutoPlugin {
  object autoImport {
    val YamlBlowoutGenerator = io.taig.blowout.BlowoutYamlGenerator
  }

  override def requires: Plugins = JvmPlugin

  override def trigger: PluginTrigger = allRequirements
}
