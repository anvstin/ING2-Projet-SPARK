package org.peaceland.drone.models

/**
 * Information detected about a citizen.
 *
 * @param citizenId  The id of the citizen. (Better than using firstName and lastName as the id)
 * @param firstName  The first name of the citizen.
 * @param lastName   The last name of the citizen.
 * @param peaceScore The computed peace score of the citizen.
 */
case class CitizenInfo(
                        citizenId: CitizenId,
                        firstName: CitizenName,
                        lastName: CitizenName,
                        peaceScore: PeaceScore
                      )
