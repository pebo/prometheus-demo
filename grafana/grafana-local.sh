#!/bin/bash
set -e
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

docker run --rm --name=grafana -p 3000:3000 grafana/grafana:6.0.1
