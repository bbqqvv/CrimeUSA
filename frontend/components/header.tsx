import { Separator } from "./ui/separator"
import Image from "next/image"

export default function Header() {
    return(
        <header>
            <div className="bg-black text-white sticky top-0 z-50">
                <div className="container mx-auto px-4 py-2 flex justify-between items-center text-sm">
                    <div className="flex items-center space-x-4">
                        <Image
                            src="/images/Logo NYC.png"
                            alt="NYC"
                            width={65}
                            height={25}
                        />
                        <span className="px-10 font-bold">New York City Police Department</span>
                    </div>
                    <div className="flex h-10 items-center space-x-10">
                        <select className="bg-black text-white border-none text-sm font-bold">
                            <option>English</option>
                            <option>Vietnam</option>
                        </select>
                        <div className="w-px h-6 bg-gray-300" />
                        <button className="text-sm font-bold">Login</button>
                    </div>
                </div>
            </div>
            <div className="container mx-auto px-4 py-2 flex justify-center items-center">
                <Image
                    src="/images/Logo.png"
                    alt="NYC"
                    width={255}
                    height={100}
                    className="object-contain"
                />
            </div>
        <div className="w-full h-px bg-gray-400 mx-auto" />
        </header>
    )
}