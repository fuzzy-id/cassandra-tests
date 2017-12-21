/* 
 * Copyright 2017 Midas Technologies AG
 */
lazy val Versions = new {
  val finagle = "17.11.0"
  val phantom = "2.16.4"
  val scalacheck = "1.13.4"
  val scalatest = "3.0.4"
  val phantomUtil = "0.38.0"

  val scala212 = "2.12.4"
}

val commonSettings: Seq[Def.Setting[_]] = Seq(
  organization := "de.babibo",
  scalaVersion := Versions.scala212,
  libraryDependencies ++= Seq(
    "com.outworkers" %% "phantom-finagle" % Versions.phantom,
    "com.twitter"    %% "util-collection" % Versions.finagle,
    "org.scalatest"  %% "scalatest"       % Versions.scalatest,
    "com.outworkers" %% "util-testing"    % Versions.phantomUtil % Test,
  ),
)

lazy val cassandraProblems = (project in file("cassandra-problems"))
  .settings(
    commonSettings,
  ).settings(
    name := "cassandra-problems",
  )
