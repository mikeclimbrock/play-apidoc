import play.PlayImport.PlayKeys._
import scoverage.ScoverageSbtPlugin.ScoverageKeys._
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

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
      "com.typesafe.play" %% "anorm" % "2.5.0",
      "org.postgresql" % "postgresql" % "9.4-1207",
      "org.scalatestplus" %% "play" % "%%SCALATESTPLUS_VERSION%%" % "test"
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
      "org.webjars" %% "webjars-play" % "2.4.0-2",
      "org.webjars" % "bootstrap" % "3.3.6",
      "org.webjars" % "jquery" % "2.1.4"
    )
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  name <<= name("%%NAME%%-" + _),
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.6" % "test"
  ),
  scalacOptions += "-feature",
  coverageHighlighting := true
)

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference( AlignSingleLineCaseStatements, true )
  .setPreference( DoubleIndentClassDeclaration, true )
  .setPreference( IndentSpaces, 2 )
