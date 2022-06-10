#!/bin/bash
set -ex

(cd confluent && docker compose create) # Must be first to create the network

(cd docker-hadoop && docker compose create)

(cd drones && sbt assembly && docker compose create)

echo "Installing hdfs-connect and creating topics..."


(cd confluent && docker compose up connect -d)

sleep 10

docker exec broker kafka-topics --bootstrap-server "broker:29092" --list
docker exec broker kafka-topics --create --if-not-exists --topic "drone-states" --bootstrap-server "broker:29092"
docker exec broker kafka-topics --create --if-not-exists --topic "emergency-situations" --bootstrap-server "broker:29092"

CONNECTOR_DATA="$(cat ./confluent/connector_HDFS-Drones_config.json)"

for i in 1 2 3 4 5
do
docker exec connect curl --retry-connrefused --retry 6 -X POST -H "Content-Type: application/json" --data "${CONNECTOR_DATA}" http://connect:8083/connectors && break || sleep 10
done

(cd confluent && docker compose stop)
