import { Search } from "lucide-react"
import { Input } from "@/components/ui/input"

export default function Footer() {
    return (
        <footer>
            <div className="bg-black text-white mt-40">
                <div className="container mx-auto px-4 py-8">
                    <div className="grid grid-cols-1 md:grid-cols-4 lg:grid-cols-6 gap-8">
                        {/* Column 1 */}
                        <div className="space-y-4">
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                Directory of City Agencies
                            </a>
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                Notify NYC
                            </a>
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                NYC Mobile Apps
                            </a>
                        </div>

                        {/* Column 2 */}
                        <div className="space-y-4">
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                Contact NYC Government
                            </a>
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                CityStore
                            </a>
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                Maps
                            </a>
                        </div>

                        {/* Column 3 */}
                        <div className="space-y-4">
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                City Employees
                            </a>
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                Stay Connected
                            </a>
                            <a href="#" className="block text-white hover:text-gray-300 transition-colors">
                                Resident Toolkit
                            </a>
                        </div>

                        {/* Spacer for larger screens */}
                        <div className="hidden lg:block"></div>

                        {/* NYC Logo and Search - spans 2 columns on large screens */}
                        <div className="md:col-span-2 lg:col-span-2 space-y-4">
                            {/* NYC Logo and Search */}
                            <div className="flex items-center justify-between">
                                <div className="text-3xl font-bold tracking-wider">NYC</div>
                                <div className="relative">
                                    <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-4 w-4" />
                                    <Input
                                        type="text"
                                        placeholder="Search"
                                        className="pl-10 bg-gray-800 border-gray-600 text-white placeholder-gray-400 focus:bg-gray-700 focus:border-gray-500 w-40"
                                    />
                                </div>
                            </div>

                            {/* Copyright and Legal */}
                            <div className="text-sm text-gray-300 space-y-2">
                                <p>City of New York. 2025 All Rights Reserved.</p>
                                <p>NYC is a trademark and service mark of the City of New York</p>

                                {/* Legal Links */}
                                <div className="flex items-center space-x-4 pt-2">
                                    <a href="#" className="hover:text-white transition-colors">
                                        Privacy Policy
                                    </a>
                                    <a href="#" className="hover:text-white transition-colors">
                                        Terms of Use
                                    </a>
                                    {/* Accessibility Icon */}
                                    <div className="ml-auto">
                                        <svg
                                            className="w-6 h-6 text-white"
                                            viewBox="0 0 24 24"
                                            fill="currentColor"
                                            aria-label="Accessibility"
                                        >
                                        </svg>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </footer>
    )
}
