@echo off
cd /d "%~dp0"
title Discovery Server :8761
call ..\..\run-mvn.bat spring-boot:run
