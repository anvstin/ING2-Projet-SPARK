package org.peaceland.drone.processing


import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import org.peaceland.drone.Topics
import org.peaceland.drone.processing.PeaceProcessing.peaceStreamProcessingBuilder

import java.util.Properties
import scala.sys.env

object Main {
  def main(args: Array[String]): Unit = {
    val builder = peaceStreamProcessingBuilder(Topics.DroneStates, Topics.EmergencySituations)

    // We build our topology
    val topology = builder.build()

    // Loading config in resources/kafka-streams.properties (kafka server, ...)
    val props = new Properties()
    props.load(getClass.getResourceAsStream("/kafka-streams.properties"))
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env.getOrElse("BOOTSTRAP_SERVER", "localhost:9092"))

    // Create application instance
    val application: KafkaStreams = new KafkaStreams(topology, props)
    application.start()
  }
}
