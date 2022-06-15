name := """testing-project"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val routesV2 = (project in file("conf/routes_v2")).enablePlugins(PlayScala)

lazy val testingProject = (project in file(".")).enablePlugins(PlayJava)
  .settings(PlayKeys.playDefaultPort := 5511)

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  guice,
  javaWs,
  javaJdbc,
  "com.typesafe.akka" %% "akka-actor" % "2.6.19"
)
