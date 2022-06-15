name := """testing-project"""
organization := "com.example"

version := "1.0-SNAPSHOT"

// add custom routes directory FIXME doesn't work -- value Assets is not a member of object controllers.routes
Compile / unmanagedResourceDirectories += baseDirectory.value / "conf" / "routes_v2"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
  .settings(PlayKeys.playDefaultPort := 5511)

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  guice,
  javaWs,
  javaJdbc,
  "com.typesafe.akka" %% "akka-actor" % "2.6.19"
)

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
