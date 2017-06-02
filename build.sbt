import sbtassembly.AssemblyPlugin.autoImport._

name := "remainder"
version := "0.1-SNAPSHOT"
scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "net.liftweb" %% "lift-json" % "2.5.1",
  "com.github.nscala-time" %% "nscala-time" % "2.16.0",
  "com.beachape" %% "enumeratum" % "1.4.13",
  "com.github.scopt" %% "scopt" % "3.4.0",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
