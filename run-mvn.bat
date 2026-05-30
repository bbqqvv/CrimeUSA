@echo off
:: Maven execution helper that falls back to global mvn if wrapper fails
setlocal

:: Check if mvnw.cmd is in the current directory
set "MVN_EXE="
if exist "mvnw.cmd" (
    set "MVN_EXE=mvnw.cmd"
) else if exist "..\mvnw.cmd" (
    set "MVN_EXE=..\mvnw.cmd"
) else if exist "..\..\mvnw.cmd" (
    set "MVN_EXE=..\..\mvnw.cmd"
)

if not "%MVN_EXE%"=="" (
    echo [INFO] Attempting to run Maven Wrapper: %MVN_EXE%
    call "%MVN_EXE%" %*
    if %ERRORLEVEL% equ 0 (
        goto end
    )
    echo [WARNING] Maven Wrapper failed with error code %ERRORLEVEL%.
)

echo [INFO] Falling back to global 'mvn' command...
where mvn >nul 2>&1
if %ERRORLEVEL% equ 0 (
    call mvn %*
    if %ERRORLEVEL% equ 0 (
        goto end
    )
)

echo [ERROR] Failed to execute Maven. Please ensure Java 21+ and Maven are installed.
pause

:end
endlocal
