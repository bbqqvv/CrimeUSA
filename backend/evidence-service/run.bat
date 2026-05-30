@echo off
cd /d "%~dp0"
title evidence-service :8084

if "%SPRING_DATASOURCE_USERNAME%"=="" set "SPRING_DATASOURCE_USERNAME=root"
if "%SPRING_DATASOURCE_PASSWORD%"=="" set "SPRING_DATASOURCE_PASSWORD=123456"

set "SERVER_PORT=8084"
set "SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/Evidence_Service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
set "SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:29092"

call ..\..\run-mvn.bat spring-boot:run
