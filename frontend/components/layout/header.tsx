import { Search } from "lucide-react"

export function Header() {
  return (
    <>
      {/* Top Header */}
      <header className="bg-gray-800 text-white">
        <div className="container mx-auto px-4 py-2 flex items-center justify-between">
          <div className="flex items-center space-x-4">
            <span className="text-xl font-bold">NYC</span>
            <span className="text-sm">New York City Police Department</span>
          </div>
          <div className="flex items-center space-x-2">
            <span className="text-sm">English</span>
            <span>▼</span>
          </div>
        </div>
      </header>

      {/* NYPD Logo */}
      <div className="bg-white py-6">
        <div className="container mx-auto px-4 text-center">
          <div className="flex items-center justify-center space-x-3">
            <div className="w-16 h-16 bg-blue-600 rounded-full flex items-center justify-center">
              <span className="text-white font-bold text-xs">NYPD</span>
            </div>
            <span className="text-4xl font-bold text-blue-600">NYPD</span>
          </div>
        </div>
      </div>

      {/* Navigation */}
      <nav className="bg-gray-100 border-b">
        <div className="container mx-auto px-4">
          <div className="flex space-x-8">
            {["Home", "About", "Bureaus", "Services", "Stats", "Policies"].map((item) => (
              <a key={item} href="#" className="py-4 text-gray-700 hover:text-blue-600">
                {item}
              </a>
            ))}
            <div className="py-4 ml-auto">
              <Search className="w-5 h-5 text-gray-500" />
            </div>
          </div>
        </div>
      </nav>
    </>
  )
}
