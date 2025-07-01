"use client";

import { useEffect, useState } from "react";
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";

const initialResponseSections = [
  { id: "dispatch", label: "Time of dispatching forces to the scene" },
  { id: "arrival", label: "Time of arrival at the scene" },
  { id: "officers", label: "List of officers assigned to the scene" },
  { id: "assessment", label: "Preliminary assessment of the scene situation" },
  { id: "preservation", label: "Scene preservation measures taken" },
  { id: "medical", label: "Information on medical/rescue support provided" },
];

const sceneInfoSections = [
  { id: "statements", label: "Initial Statements" },
  { id: "description", label: "Scene Description" },
  { id: "media", label: "Images and Videos" },
  { id: "evidence", label: "Preliminary Physical Evidence Information" },
  { id: "sketch", label: "Scene Sketch" },
];

export default function SidebarAccordion() {
  const [activeSection, setActiveSection] = useState<string | null>(null);

  // Theo dõi scroll và cập nhật mục đang active
  useEffect(() => {
    const handleScroll = () => {
      const allSections = [...initialResponseSections, ...sceneInfoSections];
      for (const section of allSections) {
        const el = document.getElementById(section.id);
        if (el) {
          const rect = el.getBoundingClientRect();
          if (rect.top <= 100 && rect.bottom >= 100) {
            setActiveSection(section.id);
            break;
          }
        }
      }
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  const scrollToSection = (id: string) => {
    const el = document.getElementById(id);
    if (el) {
      el.scrollIntoView({ behavior: "smooth", block: "start" });
      setActiveSection(id);
    }
  };

  return (
    <div className="w-64 mt-8 rounded-lg border p-0 bg-white">
      <Accordion type="single" collapsible className="w-full" defaultValue="item-1">
        {/* Initial Response */}
        <AccordionItem value="item-1">
          <AccordionTrigger className="bg-gray-400 text-black px-4 py-2 rounded-t-lg data-[state=open]:bg-blue-200">
            Initial Response
          </AccordionTrigger>
          <AccordionContent className="bg-blue-50 px-4 py-2">
            <ul className="list-none pl-0 text-sm space-y-2">
              {initialResponseSections.map((s) => (
                <li
                  key={s.id}
                  onClick={() => scrollToSection(s.id)}
                  className={`cursor-pointer px-2 py-1 rounded transition ${
                    activeSection === s.id
                      ? "bg-blue-200 text-blue-900 font-semibold"
                      : "hover:text-blue-600"
                  }`}
                >
                  {s.label}
                </li>
              ))}
            </ul>
          </AccordionContent>
        </AccordionItem>

        {/* Scene Information */}
        <AccordionItem value="item-2">
          <AccordionTrigger className="bg-gray-400 text-black px-4 py-2 data-[state=open]:bg-blue-200">
            Scene Information
          </AccordionTrigger>
          <AccordionContent className="bg-blue-50 px-4 py-2">
            <ul className="list-none pl-0 text-sm space-y-2">
              {sceneInfoSections.map((s) => (
                <li
                  key={s.id}
                  onClick={() => scrollToSection(s.id)}
                  className={`cursor-pointer px-2 py-1 rounded transition ${
                    activeSection === s.id
                      ? "bg-blue-200 text-blue-900 font-semibold"
                      : "hover:text-blue-600"
                  }`}
                >
                  {s.label}
                </li>
              ))}
            </ul>
          </AccordionContent>
        </AccordionItem>

        {/* Initial Investigation Report */}
        <AccordionItem value="item-3">
          <AccordionTrigger className="bg-gray-400 text-black px-4 py-2 rounded-b-lg data-[state=open]:bg-blue-200">
            Initial Investigation Report
          </AccordionTrigger>
          <AccordionContent className="bg-blue-50 px-4 py-2">
            {/* Nội dung nếu cần */}
            <p className="text-sm text-gray-600">Report details go here...</p>
          </AccordionContent>
        </AccordionItem>
      </Accordion>
    </div>
  );
}
