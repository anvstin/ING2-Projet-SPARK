package org.peaceland.drone.simulator

import org.peaceland.drone.models._
import org.peaceland.drone.simulator.FakeDrone.{firstNamesList, lastNamesList}

import java.util.{Calendar, UUID}
import scala.util.Random

class FakeDrone(id: DroneId, location: Location) {
  def generateFakeState(): DroneReport = DroneReport(
    this.id,
    Calendar.getInstance().getTimeInMillis,
    generateListFakeCitizenInfo(Random.nextInt(10)),
    "Nominal",
    location
  )

  def generateListFakeCitizenInfo(n: Int): List[CitizenInfo] = {
    (1 to n).map(i => generateFakeCitizenInfo()).toList
  }

  def generateFakeCitizenInfo(): CitizenInfo = CitizenInfo(
    citizenId = UUID.randomUUID().toString,
    firstName = firstNamesList(Random.nextInt(firstNamesList.size)),
    lastName = lastNamesList(Random.nextInt(lastNamesList.size)),
    peaceScore = Random.nextInt(101), // 0-100
  )

}

object FakeDrone {
  val firstNamesList: List[CitizenName] = List(
    "Jean",
    "Michel",
    "Pierre",
    "Paul",
    "Jacques",
    "Marie",
    "Jacqueline",
    "Sophie",
    "Luc",
    "Lucie",
    "Pauline",
    "Sylvie",
    "Jeanne",
    "Marc",
    "Martin",
    "Jean-Pierre",
    "Jean-Luc",
    "Jean-Jacques",
    "Jean-Paul",
    "Marie-Jeanne",
    "Valerie",
  )

  val lastNamesList: List[CitizenName] = List(
    "Dupont",
    "Martin",
    "Durand",
    "Moreau",
    "Leroy",
    "Girard",
    "Bonnet",
    "Larue",
    "Lefevre",
    "Lemaire",
    "Lopez",
    "Leroux",
    "Lefort",
  )
}
