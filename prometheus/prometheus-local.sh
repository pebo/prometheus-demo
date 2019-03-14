#!/bin/bash
set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

docker run --rm -p 9090:9090 -v $DIR/prometheus-local.yml:/etc/prometheus/prometheus.yml prom/prometheus:v2.8.0
