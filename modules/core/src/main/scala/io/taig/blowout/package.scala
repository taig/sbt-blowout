package io.taig

package object blowout {
  type BlowoutComment = List[String] => Option[String]

  object BlowoutComment {
    val Disabled: BlowoutComment = _ => None

    def prefixed(prefix: String): BlowoutComment = lines =>
      Some(lines.map(line => s"$prefix$line").mkString("", "\n", "\n"))

    val Hash: BlowoutComment = prefixed("# ")
    val Slash: BlowoutComment = prefixed("// ")
  }
}
