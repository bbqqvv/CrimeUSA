"use client";

import React from "react";

import Link from "next/link";
import { ChevronDown } from "lucide-react";
import { Button } from "../../ui/button";
import { DropdownMenu, DropdownMenuTrigger, DropdownMenuContent, DropdownMenuItem } from "@radix-ui/react-dropdown-menu";

export const Header = () => {
    return (
        <header className="bg-black text-white w-full">
            <div className="flex flex-wrap justify-between items-center gap-4 px-4 py-4 max-w-screen-xl mx-auto">
                {/* Logo + Title */}
                <div className="flex flex-col sm:flex-row sm:items-center gap-1 sm:gap-3">
                    <h1 className="text-3xl font-bold leading-none">NYC</h1>
                    <h2 className="text-base sm:text-lg md:text-2xl text-gray-300 leading-tight">
                        New York City Police Department
                    </h2>
                </div>

                {/* Right-side: Language & Login */}
                <div className="flex flex-col sm:flex-row items-center gap-3">
                    {/* Language Dropdown */}
                    <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                            <Button
                                variant="outline"
                                className="flex items-center gap-1 text-white border-white px-3 py-1"
                            >
                                <span>English</span>
                                <ChevronDown className="w-4 h-4" />
                            </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent align="start">
                            <DropdownMenuItem>English</DropdownMenuItem>
                            <DropdownMenuItem>Tiếng Việt</DropdownMenuItem>
                            <DropdownMenuItem>Español</DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu>

                    {/* Divider */}
                    <div className="hidden sm:block w-px h-6 bg-gray-400" />

                    {/* Login Button */}
                    <Link href="/login">
                        <Button
                            variant="outline"
                            className="text-white border-white px-4 py-1"
                        >
                            Login
                        </Button>
                    </Link>
                </div>
            </div>
        </header>
    );
};
