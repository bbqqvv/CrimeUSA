# 🕵️‍♂️ Criminal Investigation Management System

> A modern web application built to manage complex criminal investigations — combining Next.js (frontend) and Spring Boot (microservices backend).

---

## 🎯 Mục tiêu dự án

Xây dựng hệ thống hỗ trợ cơ quan điều tra tại Mỹ quản lý toàn bộ quy trình điều tra tội phạm, từ tiếp nhận báo cáo, xử lý vật chứng, bắt giữ nghi phạm, đến kết án và lưu trữ hồ sơ.

---

## 📌 Tech Stack (thống nhất ngày: [19/6/2025])

🚀 Next.js Project Setup Guide

> Hướng dẫn chi tiết để chạy dự án Next.js sau khi clone về lần đầu.

---

## ✅ Clone dự án

### Frontend
```bash
git clone [https://github.com/your-org/your-project.git](https://github.com/quocnhat02/MockProject_062025_Nhom1.git)
cd MockProject_062025_Nhom1/frontend

# npm
npm install

npm run dev
```

### 🌐 Frontend:

- **Framework:** Next.js `15.3.4` (App Router)
- **Runtime:** Node.js `22.16.0`
- **Package manager:** npm `10.0.x`
- **Dependencies:**
  - React `19.1`
  - TypeScript `5.8.x`
  - Tailwind CSS `4.0`
  - Axios `1.10.0`
  - React Hook Form `7.58.1`
  - Zod `3.25.67` (nếu dùng validate)
  - React Query `5.80.10` (nếu dùng fetch)

### 🔧 Backend:

- **Framework:** Spring Boot `3.5.0`
- **Java Version:** `21`
- **Dependencies:**
  - Lombok `1.18.30`
- Mapstruct 1.18.30
- Spring Security
  -Spring Oauth-2

#### 📁 Danh sách Microservices

| Service | Chức năng chính |
|--------|-----------------|
| `auth-service` | Đăng nhập, phân quyền, JWT |
| `case-service` | Quản lý vụ án, kết quả, timeline |
| `report-service` | Tiếp nhận và xử lý báo cáo |
| `suspect-service` | Quản lý nghi phạm, án phạt |
| `evidence-service` | Vật chứng, điều tra kỹ thuật số/pháp y |
| `investigation-service` | Lên kế hoạch điều tra, phỏng vấn |
| `warrant-arrest-service` | Quản lý lệnh và quá trình bắt giữ |
| `victim-witness-service` | Người bị hại và nhân chứng |
| `api-gateway` | Cổng truy cập trung tâm |
| `discovery-server` | Eureka hoặc Nacos |
| `config-server` | Quản lý cấu hình tập trung |

---

### 🗃️ Database - tools:

- Mysql `8.x`
- MySQL Workbench
- Redis `7.x` (nếu có caching)

### 🧪 Testing:

- Backend: JUnit `5`, Mockito `5.11.0`
- Frontend: Jest `29.7.0`, React Testing Library `14.1.2`

### 💡 IDE đề xuất:

- IntelliJ Community IDEA 2025.1 (Backend)
- VS Code 2025 1.101 (Frontend)

Test API:
Postman

---

## 📏 Quy định bắt buộc:

- Tất cả thành viên phải cài đúng **version** framework & dependency như trên
- Nếu thêm thư viện mới ➜ **phải thảo luận trước với cả nhóm**
- Vi phạm → Pull Request không được merge
- Code Java tuân theo: https://docs.google.com/spreadsheets/d/1YLuj9Mm_N0HJS1DyFAx889jWZI9vdsen/edit?gid=1620575693#gid=1620575693
