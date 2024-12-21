import io.circe.Json
import io.circe.syntax._

object GitHubActionsGenerator {
  object Step {
    def setupJava(version: String): Json = Json.obj(
      "name" := "Setup Java JDK",
      "uses" := "actions/setup-java@v4",
      "with" := Json.obj(
        "distribution" := "temurin",
        "java-version" := version,
        "cache" := "sbt"
      )
    )

    val Checkout: Json = Json.obj(
      "name" := "Checkout",
      "uses" := "actions/checkout@v4",
      "with" := Json.obj(
        "fetch-depth" := 0
      )
    )
  }

  object Job {
    def lint(javaVersion: String): Json = Json.obj(
      "name" := "Fatal warnings and code formatting",
      "runs-on" := "ubuntu-latest",
      "steps" := List(
        Step.Checkout,
        Step.setupJava(javaVersion),
        Json.obj(
          "name" := "Workflows",
          "run" := "sbt blowoutCheck"
        ),
        Json.obj(
          "name" := "Code formatting",
          "run" := "sbt scalafmtCheckAll"
        ),
        Json.obj(
          "name" := "Linting",
          "run" := "sbt scalafixCheckAll"
        )
      )
    )

    def test(javaVersion: String): Json = Json.obj(
      "name" := "Unit tests",
      "runs-on" := "ubuntu-latest",
      "steps" := List(
        Step.Checkout,
        Step.setupJava(javaVersion),
        Json.obj(
          "name" := "Tests",
          "run" := "sbt scripted"
        )
      )
    )

    def deploy(javaVersion: String): Json = Json.obj(
      "name" := "Deploy",
      "runs-on" := "ubuntu-latest",
      "needs" := List("test", "lint"),
      "steps" := List(
        Step.Checkout,
        Step.setupJava(javaVersion),
        Json.obj(
          "name" := "Release",
          "run" := "sbt ci-release",
          "env" := Json.obj(
            "PGP_PASSPHRASE" := "${{secrets.PGP_PASSPHRASE}}",
            "PGP_SECRET" := "${{secrets.PGP_SECRET}}",
            "SONATYPE_PASSWORD" := "${{secrets.SONATYPE_PASSWORD}}",
            "SONATYPE_USERNAME" := "${{secrets.SONATYPE_USERNAME}}"
          )
        )
      )
    )
  }

  def main(javaVersion: String): Json = Json.obj(
    "name" := "CI",
    "on" := Json.obj(
      "push" := Json.obj("branches" := List("main"))
    ),
    "env" := Json.obj(
      "SBT_TPOLECAT_CI" := "true"
    ),
    "jobs" := Json.obj(
      "lint" := Job.lint(javaVersion),
      "test" := Job.test(javaVersion),
      "deploy" := Job.deploy(javaVersion)
    )
  )

  def tag(javaVersion: String): Json = Json.obj(
    "name" := "CD",
    "on" := Json.obj(
      "push" := Json.obj("tags" := List("*.*.*"))
    ),
    "env" := Json.obj(
      "SBT_TPOLECAT_RELEASE" := "true"
    ),
    "jobs" := Json.obj(
      "lint" := Job.lint(javaVersion),
      "test" := Job.test(javaVersion),
      "deploy" := Job.deploy(javaVersion)
    )
  )

  def pullRequest(javaVersion: String): Json = Json.obj(
    "name" := "CI",
    "on" := Json.obj(
      "pull_request" := Json.obj(
        "branches" := List("main")
      )
    ),
    "env" := Json.obj(
      "SBT_TPOLECAT_CI" := "true"
    ),
    "jobs" := Json.obj(
      "lint" := Job.lint(javaVersion),
      "test" := Job.test(javaVersion)
    )
  )
}
