import io.circe.Json
import io.circe.syntax._

object GitHubActionsGenerator {
  object Step {
    val SetupJava: Json = Json.obj(
      "name" := "Setup Java JDK",
      "uses" := "actions/setup-java@v2.3.1",
      "with" := Json.obj(
        "distribution" := "temurin",
        "java-version" := "11"
      )
    )

    val Checkout: Json = Json.obj(
      "name" := "Checkout",
      "uses" := "actions/checkout@v2.4.0"
    )

    val Cache: Json = Json.obj(
      "name" := "Cache",
      "uses" := "coursier/cache-action@v6.3"
    )
  }

  object Job {
    val Lint: Json = Json.obj(
      "name" := "Fatal warnings and code formatting",
      "runs-on" := "ubuntu-latest",
      "steps" := List(
        Step.SetupJava,
        Step.Checkout,
        Step.Cache,
        Json.obj(
          "name" := "Workflows",
          "run" := "sbt -Dmode=strict blowoutCheck"
        ),
        Json.obj(
          "name" := "Code formatting",
          "run" := "sbt -Dmode=strict scalafmtCheckAll"
        ),
        Json.obj(
          "name" := "Fatal warnings",
          "run" := "sbt -Dmode=strict compile"
        )
      )
    )

    val Test: Json = Json.obj(
      "name" := "Unit tests",
      "runs-on" := "ubuntu-latest",
      "steps" := List(
        Step.SetupJava,
        Step.Checkout,
        Step.Cache,
        Json.obj(
          "name" := "Tests",
          "run" := "sbt scripted"
        )
      )
    )
  }

  val main: Json = Json.obj(
    "name" := "CI & CD",
    "on" := Json.obj(
      "push" := Json.obj(
        "branches" := List("main"),
        "tags" := List("*.*.*")
      )
    ),
    "jobs" := Json.obj(
      "lint" := Job.Lint,
      "test" := Job.Test,
      "deploy" := Json.obj(
        "name" := "Deploy",
        "runs-on" := "ubuntu-latest",
        "needs" := List("test", "lint"),
        "steps" := List(
          Step.SetupJava,
          Step.Checkout,
          Step.Cache,
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
    )
  )

  val branches: Json = Json.obj(
    "name" := "CI",
    "on" := Json.obj(
      "pull_request" := Json.obj(
        "branches" := List("main")
      )
    ),
    "jobs" := Json.obj(
      "lint" := Job.Lint,
      "test" := Job.Test
    )
  )
}
