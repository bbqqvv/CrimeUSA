@echo off
setlocal enabledelayedexpansion

:: ==============================================================
:: CONFIGURATION SECTION
:: Edit these values if you use different local credentials/ports
:: ==============================================================
set "SPRING_DATASOURCE_USERNAME=root"
set "SPRING_DATASOURCE_PASSWORD=123456"
set "REDIS_HOST=localhost"
set "REDIS_PORT=6379"
set "KAFKA_BOOTSTRAP_SERVERS=localhost:29092"
set "EUREKA_SERVER_URL=http://localhost:8761/eureka/"

:: Dummy credentials for Cloudinary
set "CLOUD_NAME=dummy_cloud"
set "CLOUD_API_KEY=1234567890"
set "CLOUD_API_SECRET=dummy_secret"
:: ==============================================================

:menu
cls
echo =====================================================================
echo  CRIMINAL INVESTIGATION MANAGEMENT SYSTEM - DEVELOPER LAUNCHER
echo =====================================================================
echo.
echo  [1] Start Docker Infrastructure (ZooKeeper, Kafka, Kafka-UI)
echo  [2] Build and Install common-service (Run this first)
echo.
echo  --- CORE SERVICES ---
echo  [3] Launch Eureka Discovery Server (Port 8761)
echo  [4] Launch API Gateway (Port 8080)
echo  [5] Launch Auth Service (Port 8090)
echo.
echo  --- WEB FRONTEND ---
echo  [6] Launch Next.js Web Frontend (Port 3000)
echo.
echo  --- BUSINESS MICROSERVICES ---
echo  [7] Enter Business Microservices Menu (Suspect, Evidence, Case...)
echo.
echo  --- AUTO-RUN ALL CORE ---
echo  [8] Launch All Core Systems (Docker + 3 Core Services + Web)
echo.
echo  --- UTILITIES ---
echo  [9] Check Ports and Services Status
echo  [10] Exit
echo.
echo =====================================================================
set /p choice="Enter choice (1-10): "

if "%choice%"=="1" goto docker_infra
if "%choice%"=="2" goto build_common
if "%choice%"=="3" goto start_eureka
if "%choice%"=="4" goto start_gateway
if "%choice%"=="5" goto start_auth
if "%choice%"=="6" goto start_frontend
if "%choice%"=="7" goto submenu_services
if "%choice%"=="8" goto start_all_core
if "%choice%"=="9" goto check_status
if "%choice%"=="10" goto exit
goto menu

:docker_infra
echo.
echo [INFO] Starting ZooKeeper and Kafka via Docker Compose...
docker compose up -d
echo.
echo [OK] Docker containers started. You can view Kafka-UI at http://localhost:8080
pause
goto menu

:build_common
echo.
echo [INFO] Building and installing common-service to local maven repository...
cd backend\common-service
call mvnw clean install -DskipTests
cd ..\..
echo.
echo [OK] Completed building common-service!
pause
goto menu

:start_eureka
echo.
echo [INFO] Launching Discovery Server (Eureka)...
start "Discovery Server (Eureka:8761)" cmd /k "backend\discovery-server\run.bat"
echo [OK] Launched Eureka Server in a new window.
timeout /t 2 > nul
goto menu

:start_gateway
echo.
echo [INFO] Launching API Gateway (8080)...
start "API Gateway (8080)" cmd /k "backend\api-gateway\run.bat"
echo [OK] Launched API Gateway in a new window.
timeout /t 2 > nul
goto menu

:start_auth
echo.
echo [INFO] Launching Auth Service (8090)...
start "Auth Service (8090)" cmd /k "backend\auth-service\run.bat"
echo [OK] Launched Auth Service in a new window.
timeout /t 2 > nul
goto menu

:start_frontend
echo.
echo [INFO] Launching Next.js Frontend...
start "Next.js Frontend (3000)" cmd /k "cd frontend && title Next.js Frontend :3000 && npm install && npm run dev"
echo [OK] Launched Frontend in a new window.
timeout /t 2 > nul
goto menu

:submenu_services
cls
echo =====================================================================
echo  MICROSERVICES BUSINESS MENU
echo =====================================================================
echo.
echo  [1] Launch suspect-service (Suspects - Port 9001)
echo  [2] Launch holiday-service (Holidays - Port 8081)
echo  [3] Launch investigation-service (Investigations - Port 9000)
echo  [4] Launch evidence-service (Evidence - Port 8084)
echo  [5] Launch case-service-base (Cases - Port 8083)
echo  [6] Launch report-service-base (Reports - Port 8082)
echo.
echo  [7] Back to Main Menu
echo.
echo =====================================================================
set /p subchoice="Enter choice (1-7): "

if "%subchoice%"=="1" goto start_suspect
if "%subchoice%"=="2" goto start_holiday
if "%subchoice%"=="3" goto start_investigation
if "%subchoice%"=="4" goto start_evidence
if "%subchoice%"=="5" goto start_case
if "%subchoice%"=="6" goto start_report
if "%subchoice%"=="7" goto menu
goto submenu_services

:start_suspect
echo.
echo [INFO] Launching suspect-service...
start "suspect-service (9001)" cmd /k "backend\suspect-service\run.bat"
timeout /t 2 > nul
goto submenu_services

:start_holiday
echo.
echo [INFO] Launching holiday-service...
start "holiday-service (8081)" cmd /k "backend\holiday-service\run.bat"
timeout /t 2 > nul
goto submenu_services

:start_investigation
echo.
echo [INFO] Launching investigation-service...
start "investigation-service (9000)" cmd /k "backend\investigation-service\run.bat"
timeout /t 2 > nul
goto submenu_services

:start_evidence
echo.
echo [INFO] Launching evidence-service...
start "evidence-service (8084)" cmd /k "backend\evidence-service\run.bat"
timeout /t 2 > nul
goto submenu_services

:start_case
echo.
echo [INFO] Launching case-service-base...
start "case-service-base (8083)" cmd /k "backend\case-service-base\run.bat"
timeout /t 2 > nul
goto submenu_services

:start_report
echo.
echo [INFO] Launching report-service-base...
start "report-service-base (8082)" cmd /k "backend\report-service-base\run.bat"
timeout /t 2 > nul
goto submenu_services

:start_all_core
echo.
echo [INFO] Starting core services sequentially...
echo [1/5] Starting Docker Infrastructure...
docker compose up -d
timeout /t 3 > nul

echo [2/5] Starting Eureka Server...
start "Discovery Server (Eureka:8761)" cmd /k "backend\discovery-server\run.bat"
echo Waiting 10 seconds for Eureka Server to bind...
timeout /t 10 > nul

echo [3/5] Starting API Gateway...
start "API Gateway (8080)" cmd /k "backend\api-gateway\run.bat"
timeout /t 3 > nul

echo [4/5] Starting Auth Service...
start "Auth Service (8090)" cmd /k "backend\auth-service\run.bat"
timeout /t 3 > nul

echo [5/5] Starting Next.js Frontend...
start "Next.js Frontend (3000)" cmd /k "cd frontend && title Next.js Frontend :3000 && npm run dev"

echo.
echo [OK] Core services started in separate windows!
pause
goto menu

:check_status
echo.
echo === PORT STATUS CHECK ===
echo.
for %%p in (8761 8080 8090 3000 9000 9001 8081 8082 8083 8084) do (
    netstat -ano | findstr "LISTENING" | findstr ":%%p " > nul
    if !ERRORLEVEL! equ 0 (
        echo  [ONLINE]  Port %%p is listening
    ) else (
        echo  [OFFLINE] Port %%p is offline
    )
)
echo.
echo Running Docker containers:
docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
echo.
pause
goto menu

:exit
echo.
echo Thank you for using Launcher!
timeout /t 2 > nul
exit /b 0
