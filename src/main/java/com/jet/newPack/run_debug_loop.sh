#!/bin/bash

JAVA_BIN="/home/annaivanova/.jdks/corretto-17.0.13/bin/java"
CLASS_NAME="com.jet.newPack.A"
CLASSPATH="/home/annaivanova/IdeaProjects/lambda_maven/target/classes"

COUNTER=1

while true; do
    echo "===== Run #$COUNTER ====="
    "$JAVA_BIN" -agentlib:jdwp=transport=dt_socket,server=n,address=localhost:5007,suspend=y \
      -classpath "$CLASSPATH" "$CLASS_NAME"
    echo "Process exited. Restarting in 10 seconds..."
    sleep 1
    ((COUNTER++))
done