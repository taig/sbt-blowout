package io.taig.blowout

import sbt.{AutoPlugin, PluginTrigger, Plugins}

object BlowoutYamlPlugin extends AutoPlugin {
  object autoImport {
    val BlowoutYamlGenerator = io.taig.blowout.BlowoutYamlGenerator
  }

  override def requires: Plugins = BlowoutPlugin

  override def trigger: PluginTrigger = noTrigger
}
