'use client'
import Link from 'next/link';
import { Separator } from './ui/separator';
import {NavigationMenu, NavigationMenuList, NavigationMenuItem, NavigationMenuTrigger, NavigationMenuLink, navigationMenuTriggerStyle} from './ui/navigation-menu'
import { Search } from "lucide-react"
import { Input } from "@/components/ui/input"
import { useState } from 'react';

export default function Navbar() {
    const [value, onChange] = useState()

    return (
        <div className="container mx-auto px-4 py-2 flex justify-center items-center">
            <NavigationMenu>
                <NavigationMenuList className='h-10 flex space-x-4'>
                    <NavigationMenuItem>
                        <NavigationMenuLink asChild className={`${navigationMenuTriggerStyle()} hover:text-blue-500 font-bold`}>
                            <Link href="/">Home</Link>
                        </NavigationMenuLink>
                    </NavigationMenuItem>
                    <div className="w-px h-4 bg-gray-300" />
                    <NavigationMenuItem>
                        <NavigationMenuLink asChild className={`${navigationMenuTriggerStyle()} hover:text-blue-500 font-bold`}>
                            <Link href="/about">About</Link>
                        </NavigationMenuLink>
                    </NavigationMenuItem>
                    <div className="w-px h-4 bg-gray-300" />
                    <NavigationMenuItem>
                        <NavigationMenuLink asChild className={`${navigationMenuTriggerStyle()} hover:text-blue-500 font-bold`}>
                            <Link href="/bureaus">Bureaus</Link>
                        </NavigationMenuLink>
                    </NavigationMenuItem>
                    <div className="w-px h-4 bg-gray-300" />
                    <NavigationMenuItem>
                        <NavigationMenuLink asChild className={`${navigationMenuTriggerStyle()} hover:text-blue-500 font-bold`}>
                            <Link href="/services">Services</Link>
                        </NavigationMenuLink>
                    </NavigationMenuItem>
                    <div className="w-px h-4 bg-gray-300" />
                    <NavigationMenuItem>
                        <NavigationMenuLink asChild className={`${navigationMenuTriggerStyle()} hover:text-blue-500 font-bold`}>
                            <Link href="/stats">Stats</Link>
                        </NavigationMenuLink>
                    </NavigationMenuItem>
                    <div className="w-px h-4 bg-gray-300" /><NavigationMenuItem>
                        <NavigationMenuLink asChild className={`${navigationMenuTriggerStyle()} hover:text-blue-500 font-bold`}>
                            <Link href="/policies">Policies</Link>
                        </NavigationMenuLink>
                    </NavigationMenuItem>
                    <NavigationMenu>
                        <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-4 w-4" />
                        <Input
                            type="text"
                            className="pl-10 bg-gray-100 border-gray-200 rounded-md focus:bg-white focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                        />
                    </NavigationMenu>
                </NavigationMenuList>
            </NavigationMenu>
        </div>
    );
}