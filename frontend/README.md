# NYPD Website - Frontend

## 🚀 Khởi chạy dự án

# Cài đặt dependencies (Next.js recommend pnpm)
pnpm install

# Chạy development server
pnpm run dev
```

## 📁 Cấu trúc thư mục dự án

### Các file config chính
- `.env` - Chứa các biến môi trường
- `.env.prod` - Các biến môi trường ở môi trường sản phẩm
- `locale` - chuyển đổi ngôn ngữ
- `config` - chứa các cấu hình như API URL
- `models` - users class services: dịch vụ lấy dự liệu từ API 
- `enums` - chứa các giá trị kiểu liệt kê (enumerations) 
- `utils` - các hàm dùng chung cho toàn bộ dự án 
- uploads` -các config để upload ảnh redux: cấu hình redux hooks: các hool custome

### Cấu trúc thư mục

```
frontend/
├── app/                         # App Router (Next.js 13+)
│   ├── (auth)/                  # Route Group cho các trang xác thực
│   │   ├── login/
│   │   │   └── page.tsx
│   │   ├── register/
│   │   │   └── page.tsx
│   │   └── layout.tsx           # Layout riêng cho các form xác thực
│   │
│   ├── home/                    # Route Group cho trang chính
│   │   ├── page.tsx             # Trang chủ NYPD
│   │   └── layout.tsx           # Layout chính (có Header, Footer)
│   │
│   ├── globals.css              # Các style global của Tailwind
│   ├── layout.tsx               # Layout gốc của toàn bộ ứng dụng
│   └── page.tsx                 # Root page
│
├── components/
│   ├── features/                # Các component phức tạp, dành riêng cho một tính năng
│   │   └── user-profile/
│   │       └── UserDetails.tsx
│   │
│   ├── shared/                  # Các component "thông minh" tự xây dựng, tái sử dụng
│   │   ├── header/
│   │   │   └── HomeHeader.tsx   # Header chính của website
│   │   ├── footer/
│   │   │   └── HomeFooter.tsx   # Footer chính của website
│   │   ├── PageHeader.tsx
│   │   ├── DataTable.tsx
│   │   └── ThemeToggle.tsx
│   │
│   └── ui/                      # Các component của ShadCN (do CLI tạo ra)
│       ├── button.tsx
│       ├── input.tsx
│       ├── table.tsx
│       └── card.tsx
│
├── hooks/                       # Custom React hooks
│
├── lib/
│   ├── actions.ts               # Server Actions (quan trọng trong App Router)
│   ├── auth.ts                  # Cấu hình xác thực (NextAuth.js, Clerk, ...)
│   ├── types.ts                 # Các định nghĩa TypeScript chung
│   ├── utils.ts                 # Các hàm tiện ích (như hàm `cn` của ShadCN)
│   └── validators/              # Các schema validation (Zod)
│
├── models/                      # User class và các model khác
│
├── providers/
│   ├── ThemeProvider.tsx        # Provider cho dark/light mode
│   └── QueryProvider.tsx        # Provider cho React Query (nếu cần)
│
├── public/                      # Static assets
│   └── SC_001/                  # Assets cho thiết kế NYPD
├── redux/                       # Cấu hình Redux (state management)
│
├── services/
│   └── auth.service.ts          # Dịch vụ lấy dữ liệu từ API
│
├── uploads/                     # Các config để upload ảnh
│
└── utils/                       # Các hàm dùng chung cho toàn bộ dự án





