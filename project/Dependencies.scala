import sbt._

object Dependencies {

  var mark = "test"

  object Version {

    object Akka {
      val version = "2.5.12"
      val `actor-typed` = version
      val stream = version
      val http = "10.1.5"
    }

    object Sangria {
      val version = "1.4.2"
      val circe = "1.2.1"
    }

    object Circe {
      val version = "0.10.0"
      val optics = version
      val parser = version
    }

    object Enumeratum {
      val version = "1.5.13"
      val circe = "1.5.18"
    }

    val jooq = "3.11.7"

    object Cats {
      val version = "1.5.0"
      val mtl = "0.4.0"
      val `meow-mtl` = "0.1.3"
      val effect = "1.1.0"
      val kittens = "1.2.0"
    }

    object PureConfig {
      val version = "0.10.1"
    }

    val shapeless = "2.3.3"

    object Log4j {
      val version = "2.11.1"
      val akka = "1.6.1"
      val disruptor = "3.4.2"
    }

  }

  lazy val akka: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-actor-typed" % Version.Akka.`actor-typed`,
    "com.typesafe.akka" %% "akka-stream" % Version.Akka.stream,
    "com.typesafe.akka" %% "akka-http" % Version.Akka.http
  )

  lazy val sangria: Seq[ModuleID] = Seq(
    "org.sangria-graphql" %% "sangria" % Version.Sangria.version,
    "org.sangria-graphql" %% "sangria-circe" % Version.Sangria.circe
  )

  lazy val jooq: Seq[ModuleID] = Seq(
    "org.flywaydb" % "flyway-maven-plugin" % "5.2.4" exclude ("org.slf4j", "slf4j-nop") exclude ("org.slf4j", "slf4j-jdk14"),
    "org.postgresql" % "postgresql" % "42.2.5",
    "com.zaxxer"     % "HikariCP"   % "3.2.0",

    "org.jooq" % "jooq" % Version.jooq,
    "org.jooq" % "jooq-meta" % Version.jooq,
    "org.jooq" %% "jooq-scala" % Version.jooq
  )

  lazy val circe: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core" % Version.Circe.version,
    "io.circe" %% "circe-optics" % Version.Circe.optics,
    "io.circe" %% "circe-parser" % Version.Circe.parser
  )

  lazy val enumeratum: Seq[ModuleID] = Seq(
    "com.beachape" %% "enumeratum" % Version.Enumeratum.version,
    "com.beachape" %% "enumeratum-circe" % Version.Enumeratum.circe
  )

  lazy val cats: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-macros" % Version.Cats.version,
    "org.typelevel" %% "cats-kernel" % Version.Cats.version,
    "org.typelevel" %% "cats-core" % Version.Cats.version
  )

  lazy val shapeless: Seq[ModuleID] = Seq(
    "com.chuusai" %% "shapeless" % Version.shapeless
  )

  /**
    * Used in common module and models module
    */
  lazy val pureConfig: Seq[ModuleID] = Seq(
    "com.github.pureconfig" %% "pureconfig" % Version.PureConfig.version,
    "com.github.pureconfig" %% "pureconfig-enumeratum" % Version.PureConfig.version
  )

  val security: Seq[ModuleID] =
    Seq(
      "com.jason-goodwin" %% "authentikat-jwt" % "0.4.5" withSources(),
      "com.github.t3hnar" %% "scala-bcrypt" % "3.1",
      "commons-codec" % "commons-codec" % "1.11"
    )

  val logging: Seq[ModuleID] =
    Seq(
      "com.lmax" % "disruptor" % Version.Log4j.disruptor,
      "de.heikoseeberger" %% "akka-log4j" % Version.Log4j.akka,
      "org.apache.logging.log4j" % "log4j-core" % Version.Log4j.version,
      "org.apache.logging.log4j" % "log4j-api" % Version.Log4j.version
    )

  val tests: Seq[ModuleID] =
    Seq(
      "org.scalatest" % "scalatest_2.12" % "3.0.4" % Test,
      "org.scalamock" %% "scalamock" % "4.1.0" % Test
    ) map (_ exclude("ch.qos.logback", "logback-classic") exclude("log4j", "log4j") exclude("org.slf4j", "slf4j-log4j12"))

  // TODO: ActiveMQ
}
