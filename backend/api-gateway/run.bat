@echo off
cd /d "%~dp0"
title API Gateway :8080

:: Config
set "REDIS_HOST=localhost"
set "REDIS_PORT=6379"
set "EUREKA_SERVER_URL=http://localhost:8761/eureka/"
set "SPRING_DATA_REDIS_HOST=localhost"
set "SPRING_DATA_REDIS_PORT=6379"

call ..\..\run-mvn.bat spring-boot:run
