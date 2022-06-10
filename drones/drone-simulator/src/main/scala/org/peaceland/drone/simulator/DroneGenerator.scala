package org.peaceland.drone.simulator

import com.sksamuel.avro4s.{AvroSchema, BinaryFormat, DataFormat, JsonFormat}
import com.sksamuel.avro4s.kafka.GenericSerde
import io.circe.generic.auto._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import org.peaceland.drone.models._
import org.peaceland.drone.simulator.DroneGenerator.randomDrones
import org.slf4j.LoggerFactory

import java.util.{Properties, UUID}
import scala.annotation.tailrec
import scala.util.Random

class DroneGenerator(producerConfig: Properties, amount: Int = 10) {
  val drones: List[FakeDrone] = randomDrones(amount)
  private val logger = LoggerFactory.getLogger(classOf[DroneGenerator])

  // Never closed since the loop is infinite. Lazy to avoid creating it when not used.
  lazy val producer: KafkaProducer[DroneId, DroneReport] = generateProducer()

  @tailrec
  final def startInfiniteReport(topic: String, sleepRange: (Long, Long)): Unit = {
    val reports = drones.map(drone => drone.generateFakeState())
    reports.foreach(droneReport => {

      producer.send(new ProducerRecord(topic, null, droneReport.timestamp, droneReport.droneId, droneReport))
    })

    val numberOfCitizens = reports.map(_.identifiedCitizens.length).sum
    val sleepMillis = Random.between(sleepRange._1, sleepRange._2)
    logger.info(s"Sent ${drones.size} reports with ${numberOfCitizens} citizens, sleeping for ${sleepMillis} ms...")
    Thread.sleep(sleepMillis)

    startInfiniteReport(topic, sleepRange)
  }

  def generateProducer(): KafkaProducer[DroneId, DroneReport] = new KafkaProducer(
    producerConfig,
    new StringSerializer(),
    Serializer.serde[DroneReport].serializer,
  )

}

object DroneGenerator {
  def randomDrones(amount: Int): List[FakeDrone] = {
    List.range(0, amount).map(e =>
      new FakeDrone(
        UUID.randomUUID().toString,
        (Random.nextDouble(), Random.nextDouble())
      )
    )
  }
}
