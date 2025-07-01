"use client";

import React from "react";
import Link from "next/link";

export const Nav = () => {
    const links = [
        { href: "/", label: "Home", active: true },
        { href: "/about", label: "About" },
        { href: "/bureaus", label: "Bureaus" },
        { href: "/services", label: "Services" },
        { href: "/stats", label: "Stats" },
        { href: "/policies", label: "Policies" },
    ];

    return (
        <nav className="w-full bg-white shadow-sm px-4">
            <div className="max-w-screen-xl mx-auto py-4 flex flex-wrap justify-center items-center gap-x-4 gap-y-2">
                {links.map((link, index) => (
                    <React.Fragment key={link.href}>
                        <Link
                            href={link.href}
                            className={`pb-1 transition-all ${link.active
                                ? "text-blue-600 border-b-2 border-blue-600"
                                : "text-gray-600 hover:text-blue-600 hover:border-b-2 hover:border-blue-600"
                                }`}
                        >
                            {link.label}
                        </Link>

                        {/* Divider (hide on small screens or last item) */}
                        {index < links.length - 1 && (
                            <div className="hidden sm:block w-px h-5 bg-gray-300" />
                        )}
                    </React.Fragment>
                ))}

                {/* Search Input */}
                <div className="flex-grow sm:flex-grow-0 sm:ml-4">
                    <input
                        type="search"
                        placeholder="Search..."
                        className="w-full sm:w-auto border border-gray-300 rounded-md px-3 py-1 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                </div>
            </div>
        </nav>
    );
};
