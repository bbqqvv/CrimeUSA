import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "../globals.css";
import HomeHeader from "@/components/shared/header/HomeHeader";
import HomeFooter from "@/components/shared/footer/HomeFooter";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "NYPD - New York City Police Department",
  description: "Official website of the New York City Police Department",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <HomeHeader />
        <main className="min-h-screen">
          {children}
        </main>
        <HomeFooter />
      </body>
    </html>
  );
}
