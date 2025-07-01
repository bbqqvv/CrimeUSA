// app/login/page.tsx
"use client";

import { useState } from "react";
import { Eye, EyeOff } from "lucide-react";
import Image from "next/image";

export default function LoginPage() {
    const [showPassword, setShowPassword] = useState(false);

    return (
        <div className="relative min-h-screen flex items-center justify-center bg-black">
            {/* Background Image */}
            <Image
                src="/bg-login.png"
                alt="Background"
                fill
                className="object-cover opacity-80"
                priority
            />

            {/* Overlay */}
            <div className="absolute inset-0 bg-black/30 backdrop-blur-sm" />

            {/* Login Box */}
            <div className="relative z-10 bg-white/80 backdrop-blur-xl rounded-xl w-full max-w-sm p-6 shadow-xl">
                <h1 className="text-center text-lg font-semibold text-gray-800 mb-6">
                    PD SYSTEM
                </h1>

                <form className="space-y-4">
                    {/* Username */}
                    <div>
                        <label className="text-sm text-gray-600">Username</label>
                        <input
                            type="text"
                            className="w-full mt-1 px-3 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Enter username"
                        />
                    </div>

                    {/* Password */}
                    <div>
                        <label className="text-sm text-gray-600">Password</label>
                        <div className="relative">
                            <input
                                type={showPassword ? "text" : "password"}
                                className="w-full mt-1 px-3 py-2 rounded-md border border-gray-300 pr-10 focus:outline-none focus:ring-2 focus:ring-blue-500"
                                placeholder="Enter password"
                            />
                            <button
                                type="button"
                                className="absolute right-2 top-2 text-gray-500"
                                onClick={() => setShowPassword(!showPassword)}
                            >
                                {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                            </button>
                        </div>
                    </div>

                    {/* Login Button */}
                    <button
                        type="submit"
                        className="w-full mt-2 bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition"
                    >
                        Login
                    </button>
                </form>

                {/* Language Dropdown */}
                <div className="mt-4 text-center text-sm text-gray-700">
                    <select className="border border-gray-300 rounded-md px-2 py-1 w-full text-sm">
                        <option>English (United States)</option>
                        <option>Tiếng Việt</option>
                        <option>Español</option>
                    </select>
                </div>

                {/* Footer Links */}
                <div className="mt-4 text-center text-xs text-gray-500 space-x-2">
                    <a href="#">About</a>
                    <a href="#">Help Center</a>
                    <a href="#">Terms of Service</a>
                    <a href="#">Privacy Policy</a>
                    <a href="#">Cookie Policy</a>
                </div>
            </div>
        </div>
    );
}
