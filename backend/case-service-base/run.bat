@echo off
cd /d "%~dp0"
title case-service-base :8083

if "%SPRING_DATASOURCE_USERNAME%"=="" set "SPRING_DATASOURCE_USERNAME=root"
if "%SPRING_DATASOURCE_PASSWORD%"=="" set "SPRING_DATASOURCE_PASSWORD=123456"

set "SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/case_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
set "SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:29092"

call ..\..\run-mvn.bat spring-boot:run
