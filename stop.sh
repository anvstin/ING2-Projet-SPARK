#!/bin/bash
set -ex

(cd confluent && docker compose stop)

(cd drones && docker compose stop)

(cd docker-hadoop && docker compose stop)
