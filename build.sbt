enablePlugins(JavaAppPackaging)

name := "tokiztoki-api"

version := "0.0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers += "spray repo" at "http://repo.spray.io"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

val sprayVersion = "1.3.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.8",
  "com.typesafe.akka" %% "akka-persistence-experimental" % "2.3.8",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0-M2",
  "io.spray" %% "spray-routing" % sprayVersion,
  "io.spray" %% "spray-client" % sprayVersion,
  "io.spray" %% "spray-caching" % sprayVersion,
  "io.spray" %% "spray-testkit" % sprayVersion % "test",
  "io.reactivex" %% "rxscala" % "0.23.1",
  "org.json4s" %% "json4s-native" % "3.2.11",
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "org.json4s" %% "json4s-ext" % "3.2.11",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "org.mockito" % "mockito-core" % "2.0.8-beta" % "test",
  "com.github.nscala-time" %% "nscala-time" % "1.6.0",
  "com.github.tototoshi" %% "scala-csv" % "1.1.2"
)

Revolver.settings
