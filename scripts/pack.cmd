@echo off
jar cfm bin\%PROJECT%.jar manifest\MANIFEST.MF -C build .
