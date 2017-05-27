import sbtassembly.AssemblyPlugin.autoImport._

name := "remainder"
version := "0.1-SNAPSHOT"
scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "net.liftweb" %% "lift-json" % "2.5.1"
)
