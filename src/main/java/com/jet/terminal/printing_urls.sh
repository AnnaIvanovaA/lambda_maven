#!/bin/sh
# printing urls

for i in `seq 1 500`; do
  echo "https://example-$i.com"
  sleep 0.05
done