import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  async redirects() {
    return [
      {
        source: '/', // Đường dẫn nguồn (khi user vào đây)
        destination: '/home', // Sẽ được chuyển hướng tới đây
        permanent: true, // Đánh dấu đây là chuyển hướng vĩnh viễn
      },
    ]
  },
};

export default nextConfig;
