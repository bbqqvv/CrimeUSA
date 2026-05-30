$services = @(
    "discovery-server",
    "api-gateway",
    "auth-service",
    "case-service-base",
    "evidence-service",
    "holiday-service",
    "investigation-service",
    "report-service-base",
    "suspect-service"
)

$baseDir = $PSScriptRoot
if (-not $baseDir) {
    $baseDir = (Get-Item -Path ".").FullName
}

# Resolve JAVA_HOME, fallback to user's IntelliJ JBR if not defined
$javaHome = $env:JAVA_HOME
if (-not $javaHome -or -not (Test-Path $javaHome)) {
    $javaHome = "D:\ProgramStudy\IntelliJ IDEA 2025.3.2\jbr"
}

# Resolve Maven command, fallback to user's IntelliJ Maven if not in PATH
$mvnCmd = "mvn"
if (-not (Get-Command "mvn" -ErrorAction SilentlyContinue)) {
    $mvnCmd = "D:\ProgramStudy\IntelliJ IDEA 2025.3.2\plugins\maven\lib\maven3\bin\mvn.cmd"
}

# Dynamically load variables from a local .env file in the backend folder (if it exists)
$envFile = Join-Path -Path $baseDir -ChildPath ".env"
if (Test-Path $envFile) {
    Write-Host "Loading local environment variables from $envFile..."
    Get-Content $envFile | ForEach-Object {
        $line = $_.Trim()
        if ($line -and -not $line.StartsWith("#") -and $line -like "*=*") {
            $key, $value = $line -split '=', 2
            $envKey = $key.Trim()
            $envValue = $value.Trim().Trim('"').Trim("'")
            [System.Environment]::SetEnvironmentVariable($envKey, $envValue)
        }
    }
}

# Ensure all background java processes are killed before starting
Write-Host "Killing any existing java processes to free ports..."
Stop-Process -Name java -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 2

# Set global env variables that all child processes will inherit!
$env:JAVA_HOME = $javaHome
$env:SPRING_DATASOURCE_USERNAME = "root"
$env:SPRING_DATASOURCE_PASSWORD = "10092412"
$env:USERNAME = "root"
$env:PASSWORD = "10092412"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "10092412"
$env:EUREKA_SERVER_URL = "http://localhost:8761/eureka/"
$env:SPRING_KAFKA_BOOTSTRAP_SERVERS = "localhost:29092"
$env:KAFKA_BOOTSTRAP_SERVERS = "localhost:29092"
$env:SPRING_DATA_REDIS_HOST = "localhost"
$env:SPRING_DATA_REDIS_PORT = "6379"
$env:SPRING_REDIS_HOST = "localhost"
$env:SPRING_REDIS_PORT = "6379"
$env:REDIS_HOST = "localhost"
$env:REDIS_PORT = "6379"

# Bypasses the Spring Cloud Compatibility Verifier version checks
$env:SPRING_CLOUD_COMPATIBILITY_VERIFIER_ENABLED = "false"

# Forces Eureka clients to register using their IP address rather than machine hostname, resolving local DNS resolution errors
$env:EUREKA_INSTANCE_PREFER_IP_ADDRESS = "true"

# Cloudinary properties needed for image uploading initialization.
# Fall back to safe dummy values only if they are not already defined (e.g. from local .env or system env)
if (-not $env:CLOUD_NAME) { $env:CLOUD_NAME = "dummy_cloud" }
if (-not $env:CLOUD_API_KEY) { $env:CLOUD_API_KEY = "1234567890" }
if (-not $env:CLOUD_API_SECRET) { $env:CLOUD_API_SECRET = "dummy_secret" }

$spawnedProcesses = @()

foreach ($service in $services) {
    Write-Host "Starting $service in background..."
    
    # Set service-specific environment variables!
    if ($service -eq "auth-service") {
        $env:DB_AUTH_URL = "jdbc:mysql://localhost:3306/auth_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/auth_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        Remove-Item Env:\SERVER_PORT -ErrorAction SilentlyContinue
    } elseif ($service -eq "case-service-base") {
        $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/case_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        Remove-Item Env:\SERVER_PORT -ErrorAction SilentlyContinue
    } elseif ($service -eq "evidence-service") {
        $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/Evidence_Service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        $env:SERVER_PORT = "8084"
    } elseif ($service -eq "holiday-service") {
        $env:DB_URL = "jdbc:mysql://localhost:3306/holiday_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/holiday_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        $env:SERVER_PORT = "8081"
    } elseif ($service -eq "investigation-service") {
        $env:DB_URL = "jdbc:mysql://localhost:3306/investigation_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/investigation_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        Remove-Item Env:\SERVER_PORT -ErrorAction SilentlyContinue
    } elseif ($service -eq "report-service-base") {
        $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/report_services?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        Remove-Item Env:\SERVER_PORT -ErrorAction SilentlyContinue
    } elseif ($service -eq "suspect-service") {
        $env:DB_URL = "jdbc:mysql://localhost:3306/suspect_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/suspect_service?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        Remove-Item Env:\SERVER_PORT -ErrorAction SilentlyContinue
    } else {
        # Clear out database variables for services that don't need databases (e.g. gateway, discovery)
        Remove-Item Env:\DB_AUTH_URL -ErrorAction SilentlyContinue
        Remove-Item Env:\DB_URL -ErrorAction SilentlyContinue
        Remove-Item Env:\SPRING_DATASOURCE_URL -ErrorAction SilentlyContinue
        Remove-Item Env:\SERVER_PORT -ErrorAction SilentlyContinue
    }
    
    $serviceDir = Join-Path -Path $baseDir -ChildPath $service
    $logsDir = Join-Path -Path $serviceDir -ChildPath "logs"
    if (-not (Test-Path $logsDir)) {
        New-Item -ItemType Directory -Path $logsDir -Force | Out-Null
    }
    
    $outFile = Join-Path -Path $logsDir -ChildPath "console.log"
    $errFile = Join-Path -Path $logsDir -ChildPath "console.err"
    
    # Empty existing log files
    Clear-Content -Path $outFile -ErrorAction SilentlyContinue
    Clear-Content -Path $errFile -ErrorAction SilentlyContinue
    
    # Start the maven process in background with inherited environment variables and output redirection
    $proc = Start-Process -FilePath $mvnCmd -ArgumentList "spring-boot:run" -WorkingDirectory $serviceDir -NoNewWindow -RedirectStandardOutput $outFile -RedirectStandardError $errFile -PassThru
    $spawnedProcesses += $proc
    
    # We give discovery-server and api-gateway a bit more time to start up and stabilize
    if ($service -eq "discovery-server") {
        Start-Sleep -Seconds 12
    } elseif ($service -eq "api-gateway") {
        Start-Sleep -Seconds 8
    } else {
        Start-Sleep -Seconds 4
    }
}

Write-Host "All services started successfully in the background! Logs redirected to logs/console.log."
Write-Host "Keeping parent script alive to prevent child processes from being terminated by the runner..."

# Keep the script running forever
while ($true) {
    Start-Sleep -Seconds 3600
}
