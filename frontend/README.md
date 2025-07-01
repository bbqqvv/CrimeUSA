Clone dự án
pnpm i (Nextjs recommend pnpm )
pnpm run dev

1. Cấu trúc thư mục dự án
.env: chứa các biến môi trường
.env.prod: các biến môi trường ở môi trường sản phẩm
locales: chuyển đổi ngôn ngữ
config: chứa các cấu hình như API URL,...
models: users class
services: dịch vụ lấy dự liệu từ API
enums: chứa các giá trị kiểu liệt kê (enumerations)
utils: các hàm dùng chung cho toàn bộ dự án
uploads: các config để upload ảnh
redux: cấu hình redux
hooks: các hool custome

├── app/
│   ├── (auth)/                  # Route Group cho các trang xác thực
│   │   ├── login/
│   │   │   └── page.tsx
│   │   └── layout.tsx           # Layout riêng cho các form xác thực
│   ├── (main)/                  # Route Group cho các trang chính của ứng dụng
│   │   └── layout.tsx           # Layout chính (có sidebar, navbar)
|   |
│   ├── globals.css              # Các style global của Tailwind
│   └── layout.tsx               # Layout gốc của toàn bộ ứng dụng
│
├── components/
│   ├── features/                # Các component phức tạp, dành riêng cho một tính năng
│   │   └── user-profile/
│   │       └── UserDetails.tsx
│   ├── shared/                  # Các component "thông minh" tự xây dựng, tái sử dụng
│   │   ├── PageHeader.tsx
│   │   ├── DataTable.tsx
│   │   └── ThemeToggle.tsx
│   └── ui/                      # Các component của ShadCN (do CLI tạo ra)
│       ├── button.tsx
│       ├── card.tsx
│       └── ...
│
├── lib/
│   ├── actions.ts               # Server Actions (quan trọng trong App Router)
│   ├── auth.ts                  # Cấu hình xác thực (NextAuth.js, Clerk, ...)
│   ├── types.ts                 # Các định nghĩa TypeScript chung
│   ├── utils.ts                 # Các hàm tiện ích (như hàm `cn` của ShadCN)
│   └── validators/              # Các schema validation (Zod)
│
├── providers/
│   ├── ThemeProvider.tsx        # Provider cho dark/light mode
│   └── QueryProvider.tsx        # Provider cho React Query (nếu cần)
│
├── 
│
