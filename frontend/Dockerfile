# Dockerfile

# --- Stage 1: Build ---
    FROM node:18-alpine AS builder
    WORKDIR /app
    
    # Cài đặt pnpm trên toàn cục
    RUN npm install -g pnpm
    
    # Copy các file quản lý project và dependencies
    # THAY ĐỔI QUAN TRỌNG Ở ĐÂY: Thêm pnpm-lock.yaml và tsconfig.json
    COPY package*.json pnpm-lock.yaml tsconfig.json next.config.js ./
    
    # Cài đặt dependencies
    RUN pnpm install
    
    # Copy toàn bộ source code còn lại
    COPY . .
    
    # Build ứng dụng Next.js
    RUN pnpm run build
    
    # --- Stage 2: Run ---
    FROM node:18-alpine
    WORKDIR /app
    
    RUN addgroup -S appgroup && adduser -S appuser -G appgroup
    USER appuser
    
    # Copy các file đã được build từ stage 'builder'
    COPY --from=builder /app/public ./public
    COPY --from=builder /app/.next/standalone ./
    COPY --from=builder /app/.next/static ./.next/static
    
    EXPOSE 3000
    
    CMD ["node", "server.js"]