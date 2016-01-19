// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "%%PLAY_VERSION%%")

addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "1.0.1")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")
