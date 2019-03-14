#!/bin/sh
# npm install http-server -g
set -e
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cp $DIR/metrics-template.txt $DIR/metrics
http-server -p 8082
