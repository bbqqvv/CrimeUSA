@echo off
cd /d "%~dp0"
title Auth Service :8090

if "%SPRING_DATASOURCE_USERNAME%"=="" set "SPRING_DATASOURCE_USERNAME=root"
if "%SPRING_DATASOURCE_PASSWORD%"=="" set "SPRING_DATASOURCE_PASSWORD=123456"
set "EUREKA_SERVER_URL=http://localhost:8761/eureka/"
set "DB_AUTH_URL=jdbc:mysql://localhost:3306/auth_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"

call ..\..\run-mvn.bat spring-boot:run
