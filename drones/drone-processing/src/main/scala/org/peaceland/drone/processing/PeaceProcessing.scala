package org.peaceland.drone.processing

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import io.circe.{Decoder, Encoder}
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.scala.serialization.Serdes._
import org.peaceland.drone.models._
import org.slf4j.LoggerFactory


object PeaceProcessing {

  val MIN_PEACESCORE = 10
  private val logger = LoggerFactory.getLogger(getClass)

  // Serializers and Deserializers
  implicit def serde[A >: Null : Decoder : Encoder]: Serde[A] = {
    val serializer = (a: A) => a.asJson.noSpaces.getBytes
    val deserializer = (aAsBytes: Array[Byte]) => {
      val aAsString = new String(aAsBytes)
      val aOrError = decode[A](aAsString)
      aOrError match {
        case Right(a) => Option(a)
        case Left(error) =>
          println(s"There was an error converting the message $aOrError, $error")
          Option.empty
      }
    }
    Serdes.fromFn[A](serializer, deserializer)
  }

  def peaceStreamProcessingBuilder(droneReportTopic: String, emergencyTopic: String): StreamsBuilder = {
    // Kafka Streams Configuration
    val builder = new StreamsBuilder()

    // Streams, one for all drones statuses and one for emergencies only
    val droneStateStream: KStream[DroneId, DroneReport] = builder.stream[DroneId, DroneReport](droneReportTopic)

    // Keeping only the emergencies
    val emergencySituations: KStream[DroneId, EmergencySituation] = emergencyOnlyStream(droneStateStream)

    emergencySituations.foreach(
      (_, situation) => logger.info(s"Found an emergency: ${situation.droneState.droneId}")
    )

    // Sending the emergencies to the emergency topic in kafka
    emergencySituations.to(emergencyTopic)

    builder
  }

  def emergencyOnlyStream(stream: KStream[DroneId, DroneReport]): KStream[DroneId, EmergencySituation] = {
    stream
      .filter((_, droneReport) =>
        droneReport
          .identifiedCitizens
          .exists(citizen => citizen.peaceScore <= MIN_PEACESCORE)
      ).mapValues(droneReport => EmergencySituation(droneReport, MIN_PEACESCORE))
  }
}
