import play.PlayImport.PlayKeys._
import scoverage.ScoverageSbtPlugin.ScoverageKeys._

name := "%%NAME%%"

scalaVersion in ThisBuild := "%%SCALA_VERSION%%"

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
    routesImport += "%%PACKAGE_NAME%%.Bindables._",
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
    routesImport += "%%PACKAGE_NAME%%.Bindables._",
    libraryDependencies ++= Seq(
      "org.webjars" %% "webjars-play" % "2.3.0-3",
      "org.webjars" % "bootstrap" % "3.3.4"
    )
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  name <<= name("%%NAME%%-" + _),
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.0" % "test"
  ),
  scalacOptions += "-feature",
  coverageHighlighting := true
)
