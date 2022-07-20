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

// add custom routes directory FIXME doesn't work
Compile / unmanagedResourceDirectories += baseDirectory.value / "conf" / "routes_v2" / "conf"

// https://stackoverflow.com/a/72594171/6784881
Compile / routes := {
  val value = (Compile / routes).value
  System.out.print("Compile / routes: " + value)
  value
}
Compile / unmanagedResourceDirectories := {
  val value = (Compile / unmanagedResourceDirectories).value
  System.out.print("Compile / unmanagedResourceDirectories: " + value)
  value
}

addCommandAlias(
  "validateCode",
  List(
    "javafmtCheckAll",
  ).mkString(";")
)
