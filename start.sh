#!/bin/bash
clear
./gradlew build
java -jar ./build/libs/parking-meter-0.0.1-SNAPSHOT.jar
