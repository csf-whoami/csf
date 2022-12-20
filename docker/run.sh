#!/bin/bash

SOURCE=/var/transaction/source

# Build source code.
cd $SOURCE
./gradlew build -x test
echo "complete build"

java -jar $SOURCE/build/libs/$WAR_NAME -Dspring.datasource.url=$DATASOURCE_URL