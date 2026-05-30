@echo off
cd /d "%~dp0"
title investigation-service :9000

if "%SPRING_DATASOURCE_USERNAME%"=="" set "SPRING_DATASOURCE_USERNAME=root"
if "%SPRING_DATASOURCE_PASSWORD%"=="" set "SPRING_DATASOURCE_PASSWORD=123456"

set "SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/investigation_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"

call ..\..\run-mvn.bat spring-boot:run
