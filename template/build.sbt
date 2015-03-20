import play.PlayImport.PlayKeys._
import scoverage.ScoverageSbtPlugin.ScoverageKeys._

name := "activator-gilt-play"

organization := "com.gilt.activator-gilt-play"

scalaVersion in ThisBuild := "2.11.5"

// required because of issue between scoverage & sbt
parallelExecution in Test in ThisBuild := true

lazy val generated = project
  .in(file("generated"))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      ws
    )
  )

lazy val api = project
  .in(file("api"))
  .dependsOn(generated)
  .aggregate(generated)
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    // routesImport += "com.gilt.xxx.v0.Bindables._",
    libraryDependencies ++= Seq(
      ws,
      jdbc,
      anorm,
      "org.postgresql" % "postgresql"    % "9.3-1101-jdbc4",
      "org.scalatestplus" %% "play" % "1.2.0" % "test"
    )
  )

lazy val www = project
  .in(file("www"))
  .dependsOn(generated)
  .aggregate(generated)
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    // routesImport += "com.gilt.xxx.v0.Bindables._"
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  name <<= name("apidoc-" + _),
  organization := "com.gilt.apidoc",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.0" % "test"
  ),
  scalacOptions += "-feature",
  coverageHighlighting := true
)
