#!/bin/bash
find ./src -name "*.java" > sources.txt && javac -d build @sources.txt
