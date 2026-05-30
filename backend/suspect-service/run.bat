@echo off
cd /d "%~dp0"
title suspect-service :9001

if "%SPRING_DATASOURCE_USERNAME%"=="" set "SPRING_DATASOURCE_USERNAME=root"
if "%SPRING_DATASOURCE_PASSWORD%"=="" set "SPRING_DATASOURCE_PASSWORD=123456"

set "USERNAME=%SPRING_DATASOURCE_USERNAME%"
set "PASSWORD=%SPRING_DATASOURCE_PASSWORD%"
set "DB_URL=jdbc:mysql://localhost:3306/suspect_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"

if "%CLOUD_NAME%"=="" set "CLOUD_NAME=dummy_cloud"
if "%CLOUD_API_KEY%"=="" set "CLOUD_API_KEY=1234567890"
if "%CLOUD_API_SECRET%"=="" set "CLOUD_API_SECRET=dummy_secret"

call ..\..\run-mvn.bat spring-boot:run
