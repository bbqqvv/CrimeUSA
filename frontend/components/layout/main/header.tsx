"use client"

import React from "react"
import Link from "next/link"
import Image from "next/image"
import { ChevronDown } from "lucide-react"
import {
    DropdownMenu,
    DropdownMenuTrigger,
    DropdownMenuContent,
    DropdownMenuItem,
} from "@radix-ui/react-dropdown-menu"
import { Button } from "@/components/ui/button"

const navLinks = [
    { href: "/", label: "Home" },
    { href: "/about", label: "About" },
    { href: "/bureaus", label: "Bureaus" },
    { href: "/services", label: "Services" },
    { href: "/stats", label: "Stats" },
    { href: "/policies", label: "Policies" },
]

export const Header = () => {
    return (
        <header className="w-full bg-black shadow-sm border-b">
            {/* Top Bar */}
            <div className="flex flex-wrap justify-between items-center gap-4 px-4 py-4 max-w-screen-xl mx-auto">
                {/* Logo + Title */}
                <div className="flex flex-col sm:flex-row sm:items-center gap-1 sm:gap-3">
                    <h1 className="text-3xl font-bold text-white leading-none">NYC</h1>
                    <h2 className="text-base sm:text-lg md:text-2xl text-gray-300 leading-tight">
                        New York City Police Department
                    </h2>
                </div>

                {/* Right Actions */}
                <div className="flex flex-col sm:flex-row items-center gap-3">
                    {/* Language Dropdown */}
                    <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                            <Button
                                variant="outline"
                                className="flex items-center gap-1 text-white border-white hover:bg-white hover:text-black"
                            >
                                <span>English</span>
                                <ChevronDown className="w-4 h-4" />
                            </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent
                            align="start"
                            className="bg-white text-black shadow-md border rounded-md p-1"
                        >
                            <DropdownMenuItem className="cursor-pointer px-3 py-1">English</DropdownMenuItem>
                            <DropdownMenuItem className="cursor-pointer px-3 py-1">Tiếng Việt</DropdownMenuItem>
                            <DropdownMenuItem className="cursor-pointer px-3 py-1">Español</DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu>

                    {/* Divider */}
                    {/* <div className="hidden sm:block w-px h-6 bg-gray-400" /> */}

                    {/* Login */}
                    {/* <Link href="/login">
                        <Button
                            variant="outline"
                            className="text-white border-white hover:bg-white hover:text-black px-4 py-1"
                        >
                            Login
                        </Button>
                    </Link> */}
                </div>
            </div>

            <div className="bg-white border-b">
                {/* Logo Image */}
                <div className="flex justify-center py-2">
                    <Image
                        src="/images/logo/Logo.png"
                        alt="NYPD Logo"
                        width={150}
                        height={50}
                        className="object-contain"
                    />
                </div>
            </div>
            {/* Navigation Bar */}
            <nav className="bg-white px-4 border-t">
                <div className="max-w-screen-xl mx-auto py-3 flex flex-wrap justify-center items-center gap-x-4 gap-y-2">
                    {navLinks.map((link, index) => (
                        <React.Fragment key={link.href}>
                            <Link
                                href={link.href}
                                className="text-gray-700 hover:text-blue-600 hover:underline underline-offset-4 transition-colors"
                            >
                                {link.label}
                            </Link>
                            {index < navLinks.length - 1 && (
                                <div className="hidden sm:block w-px h-5 bg-gray-300" />
                            )}
                        </React.Fragment>
                    ))}

                    {/* Search */}
                    <div className="flex-grow sm:flex-grow-0 sm:ml-4">
                        <input
                            type="search"
                            placeholder="Search..."
                            className="w-full sm:w-auto border border-gray-300 rounded-md px-3 py-1 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>
                </div>
            </nav>
        </header>
    )
}
