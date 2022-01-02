import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "scala-design-patterns",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "commons-codec" % "commons-codec" % "1.15",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
      "org.json4s" %% "json4s-native" % "4.0.3"
    
  )
)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
