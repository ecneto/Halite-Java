#!/bin/bash
rm *.class
rm *.java
cp -a ../src/. ./
javac MyBot.java
javac RandomBot.java
./halite -d "30 30" "java MyBot" "java RandomBot"