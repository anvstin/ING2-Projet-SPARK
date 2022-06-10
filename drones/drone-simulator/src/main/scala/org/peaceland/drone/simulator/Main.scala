package org.peaceland.drone.simulator

import com.sksamuel.avro4s.AvroSchema
import org.apache.kafka.streams.StreamsConfig
import org.peaceland.drone.Topics
import org.slf4j.LoggerFactory

import java.util.Properties
import scala.sys.env
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.peaceland.drone.models.DroneReport

object Main {
  val reportRange: (Long, Long) = (100, 1000)
  private val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    println(AvroSchema[DroneReport])

    val kafkaConfig = new Properties()
    kafkaConfig.load(getClass.getResourceAsStream("/kafka-producer.properties"))
    // The get here is on a map, not on an optional
    kafkaConfig.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env.getOrElse("BOOTSTRAP_SERVER", "localhost:9092"))
    val generator = new DroneGenerator(kafkaConfig)

    // Starting reporting
    logger.info("Starting reporting...")
    generator.startInfiniteReport(Topics.DroneStates, reportRange)
  }


}
