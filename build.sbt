import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "scala-design-patterns",
    libraryDependencies ++= Seq(
      scalaTest                     % Test,
      "commons-codec"               % "commons-codec"   % "1.15",
      "com.typesafe.scala-logging" %% "scala-logging"   % "3.9.4",
      "org.json4s"                 %% "json4s-jackson"  % "4.0.3",
      "com.github.tototoshi"       %% "scala-csv"       % "1.3.10",
      "org.scalaz"                 %% "scalaz-core"     % "7.3.6",
      "dev.optics"                 %% "monocle-core"    % "3.1.0",
      "dev.optics"                 %% "monocle-macro"   % "3.1.0",
      "com.h2database"              % "h2"              % "1.3.148" % Test,
      "org.typelevel"              %% "cats-core"       % "2.7.0",
      "org.slf4j"                   % "slf4j-api"       % "1.7.5",
      "ch.qos.logback"              % "logback-classic" % "1.0.9"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
