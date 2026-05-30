@echo off
cd /d "%~dp0"
title holiday-service :8081

if "%SPRING_DATASOURCE_USERNAME%"=="" set "SPRING_DATASOURCE_USERNAME=root"
if "%SPRING_DATASOURCE_PASSWORD%"=="" set "SPRING_DATASOURCE_PASSWORD=123456"

set "SERVER_PORT=8081"
set "DB_USERNAME=%SPRING_DATASOURCE_USERNAME%"
set "DB_PASSWORD=%SPRING_DATASOURCE_PASSWORD%"
set "DB_URL=jdbc:mysql://localhost:3306/holiday_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
set "KAFKA_BOOTSTRAP_SERVERS=localhost:29092"

call ..\..\run-mvn.bat spring-boot:run
