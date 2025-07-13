@echo off
dir /s /b src\*.java > sources.txt
javac -d build @sources.txt
