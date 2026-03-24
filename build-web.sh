#!/usr/bin/env bash
# Builds docs/game.jar from game.java (no MySQL JAR — DB code is skipped in the browser).
set -euo pipefail
DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$DIR"
mkdir -p docs
javac -encoding UTF-8 game.java
cat > manifest-web.mf << 'EOF'
Manifest-Version: 1.0
Main-Class: game

EOF
jar cfm docs/game.jar manifest-web.mf game.class 'game$1.class'
rm -f manifest-web.mf
echo "Built docs/game.jar — deploy the docs/ folder (e.g. GitHub Pages) and open index.html over HTTP."
