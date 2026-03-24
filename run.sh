#!/usr/bin/env bash
set -euo pipefail
DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$DIR"
JAR="mysql-connector-java-6.0.2-bin.jar"
if [[ ! -f "$JAR" ]]; then
  echo "Missing $JAR in this folder." >&2
  exit 1
fi
javac -encoding UTF-8 -cp ".:$JAR" game.java SwingFrame.java
java -cp ".:$JAR" game
