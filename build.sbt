name := "Akka-Assignment-03"

version := "1.0"

scalaVersion := "2.11.8"

val akkaVersion = "2.4.17"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1",
  "log4j" % "log4j" % "1.2.17",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.17"
)
    