#!/bin/bash
set -ex
SLEEP_DURATION=15

(cd docker-hadoop && docker compose up -d)

(cd confluent && docker compose up -d)

echo "sleeping for $SLEEP_DURATION seconds" && sleep "$SLEEP_DURATION"
(cd drones && docker compose up -d)
