import Dependencies._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "org.peaceland"
ThisBuild / organizationName := "peaceland"

lazy val assemblyGlobalSettings = Seq(
  assembly / assemblyMergeStrategy := {
    case PathList("META-INF", xs@_*) => MergeStrategy.discard
    case x => MergeStrategy.first
  },
)

lazy val confluentRepo = Seq(
  resolvers += "confluent.io" at "https://packages.confluent.io/maven/"
)

lazy val root = (project in file("."))
  .aggregate(droneSimulator, droneProcessing)
  .settings(
    name := "drone",
  )

lazy val droneSimulator = (project in file("drone-simulator"))
  .dependsOn(droneUtils)
  .settings(
    assemblyGlobalSettings,
    confluentRepo,
    assembly / mainClass := Some("org.peaceland.drone.simulator.Main"),

    assembly / assemblyJarName := "drone-simulator.jar",

    libraryDependencies ++= kafkaDeps ++ circeDeps ++ slf4jDeps ++ avroDeps,
  )

lazy val droneProcessing = (project in file("drone-processing"))
  .dependsOn(droneUtils)
  .settings(
    assemblyGlobalSettings,
    confluentRepo,
    assembly / assemblyJarName := "drone-processing.jar",

    libraryDependencies ++= kafkaDeps ++ circeDeps ++ slf4jDeps,

  )

lazy val droneUtils = (project in file("drone-utils"))
  .settings(
    confluentRepo,
    //    libraryDependencies ++= kafkaDeps ++ circeDeps ++ slf4jDeps,
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
