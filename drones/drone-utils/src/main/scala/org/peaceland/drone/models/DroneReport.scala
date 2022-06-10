package org.peaceland.drone.models

/**
 * A Peacewatcher drone report.
 *
 * @param droneId            The drone id.
 * @param timestamp          The timestamp of the report.
 * @param identifiedCitizens A set of identified citizens.
 * @param status             The status of the drone.
 * @param location           The location of the drone.
 */
case class DroneReport(
                        droneId: DroneId,
                        timestamp: Timestamp,
                        identifiedCitizens: List[CitizenInfo],
                        status: DroneStatus,
                        location: Location
                      )

