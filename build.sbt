import Dependencies._

/*
*  Main Build Flow
* */
updateOptions := updateOptions.value.withCachedResolution(true)
triggeredMessage := Watched.clearWhenTriggered

val rootPackage = s"com.$mark"

inThisBuild _ apply
  Seq(
    fork in test := true,

    scalaVersion := "2.12.8",

    scalacOptions := Seq(
      "-target:jvm-1.8",
      "-deprecation",
      "-unchecked",
      "-encoding", "utf8",
      "-feature",
      "-Ywarn-dead-code",
      "-Xlint",
      "-language:postfixOps",
      "-language:implicitConversions",
      "-language:existentials",
      "-language:reflectiveCalls",
      "-language:higherKinds",
      "-Ypartial-unification",
      "-Xcheckinit",
      "-Xmax-classfile-name", "143"
    ),

    libraryDependencies ++=
        jooq ++
        tests ++
        Seq(
          "com.typesafe" % "config" % "1.3.2",
          "com.github.pureconfig" %% "pureconfig" % "0.10.1",
          compilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)
        )
  )

def setup(project: Project): Project =
  project
    .settings(
      name ~= (s"$mark-" + _),
      description := name.value
    )

def named(id: String): Project = Project(id, file(s"modules/$id"))

def module(id: String): Project = setup(named(id))

def service(id: String): Project =
  module(id)
    .dependsOn(Common % "compile->compile;test->test")

lazy val Common = module("common")
  .settings(
    description ~= (_ + " shared library")
  )

lazy val Base = service("base")
  .settings(
    description ~= (_ + " core")
  )

lazy val services = setup(project in file("."))
  .aggregate(Common, Base)

/*
*  jOOQ Codegen
* */
enablePlugins(JooqCodegen)

jooqVersion := Version.jooq

jooqCodegenStrategy := CodegenStrategy.IfAbsent

jooqCodegenConfig := {
  val srcPath = (Base / Compile / scalaSource).value

  <configuration>
    <jdbc>
      <driver>org.postgresql.Driver</driver>
      <url>jdbc:postgresql://postgres.local:5432/test</url>
      <user>postgres</user>
      <password>postgres</password>
    </jdbc>
    <generator>
      <name>org.jooq.codegen.ScalaGenerator</name>
      <database>
        <name>org.jooq.meta.postgres.PostgresDatabase</name>
        <inputSchema>public</inputSchema>
        <includes>.*</includes>
        <excludes></excludes>
      </database>
      <target>
        <packageName>com.test.db.generated</packageName>
        <directory>{srcPath.getAbsolutePath}</directory>
      </target>
    </generator>
  </configuration>
}

libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5" % "jooq"
