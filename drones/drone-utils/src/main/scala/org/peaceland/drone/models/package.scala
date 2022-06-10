package org.peaceland.drone

package object models {
  /**
   * This package contains the models of the simulator.
   * All the types used are defined below.
   */
  type DroneId = String
  type Timestamp = Long
  type PeaceScore = Long
  type DroneStatus = String
  type Location = (Double, Double) // latitude, longitude
  type CitizenId = String
  type CitizenName = String

}
