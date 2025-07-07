import { Search } from "lucide-react"

const footerSections = [
  {
    title: "Directory of City Agencies",
    links: ["Notify NYC", "NYC Mobile Apps"],
  },
  {
    title: "Contact NYC Government",
    links: ["CityStore", "Maps"],
  },
  {
    title: "City Employees",
    links: ["Stay Connected", "Resident Toolkit"],
  },
]

export function Footer() {
  return (
    <footer className="bg-black text-white py-8 mt-12">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          {footerSections.map((section) => (
            <div key={section.title}>
              <h4 className="font-semibold mb-4">{section.title}</h4>
              {section.links.map((link) => (
                <p key={link} className="text-sm mb-2">
                  {link}
                </p>
              ))}
            </div>
          ))}

          <div className="flex flex-col items-end">
            <div className="flex items-center space-x-2 mb-4">
              <span className="text-2xl font-bold">NYC</span>
              <div className="bg-white text-black px-2 py-1 rounded">
                <Search className="w-4 h-4" />
              </div>
            </div>
            <div className="text-xs text-gray-400">
              <p>City of New York. 2025. All Rights Reserved.</p>
              <p>NYC is a trademark and service mark of the City of New York</p>
              <div className="flex space-x-4 mt-2">
                <a href="#" className="hover:underline">
                  Privacy Policy
                </a>
                <a href="#" className="hover:underline">
                  Terms of Use
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  )
}
