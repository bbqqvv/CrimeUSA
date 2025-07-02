# 🔧 Microservice Setup Guide (for team members)

## 🐳 Run your microservice

```bash
cd suspect-service
cp .env.suspect-service .env        # chỉnh lại DB info nếu cần

chmod +x run.sh  # chỉ cần làm 1 lần để file được chạy
./run.sh         # chạy


Bước 1: Build Docker image
./mvnw clean package -DskipTests    || mvn clean package -DskipTests
docker build -t investigation-service .

Bước 2: Chạy Docker container từ image
docker run -d -p 8082:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=3306 \
  -e DB_USER=root \
  -e DB_PASS=123456 \
  -e KAFKA_HOST=host.docker.internal:9092 \
  -e REDIS_HOST=host.docker.internal \
  -e REDIS_PORT=6379 \
  investigation-service

