import sbt._

object Dependencies {
  lazy val kafkaDeps: Seq[ModuleID] = Seq(
    "org.apache.kafka" % "kafka-clients" % Ver.kafka,
    "org.apache.kafka" % "kafka-streams" % Ver.kafka,
    "org.apache.kafka" %% "kafka-streams-scala" % Ver.kafka,
  )
  lazy val circeDeps: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core" % Ver.circe,
    "io.circe" %% "circe-generic" % Ver.circe,
    "io.circe" %% "circe-parser" % Ver.circe,
  )
  // To avoid logging errors from slf4j
  lazy val slf4jDeps: Seq[ModuleID] = Seq(
    "org.slf4j" % "slf4j-api" % Ver.slf4j,
    "org.slf4j" % "slf4j-simple" % Ver.slf4j,
  )

  lazy val avroDeps: Seq[ModuleID] = Seq(
    "com.sksamuel.avro4s" %% "avro4s-core" % Ver.avro4s,
    "com.sksamuel.avro4s" %% "avro4s-kafka" % Ver.avro4s,
  )

  object Ver {
    val kafka = "3.1.0"
    val scalaTest = "3.2.12"
    val circe = "0.14.1"
    val slf4j = "1.7.36"
    val avro4s = "4.0.13"
  }
}
