"use client";

import { useState } from "react";
import EnhancedReportSidebar, { SectionType } from "@/components/shared/sidebar/EnhancedReportSidebar";
import { InitialResponseForm } from "@/components/features/phase2/initial-response";
import { SceneInformationForm } from "@/components/features/phase2/scene-information";
import { InvestigationReportForm } from "@/components/features/phase2/investigation-report";

export default function ReportDemo() {
  const [activeSection, setActiveSection] = useState<SectionType>("initial-response");

  const handleSectionChange = (section: SectionType) => {
    setActiveSection(section);
  };

  const renderActiveComponent = () => {
    switch (activeSection) {
      case "initial-response":
        return <InitialResponseForm />;
      case "scene-information":
        return <SceneInformationForm />;
      case "investigation-report":
        return <InvestigationReportForm />;
      default:
        return <InitialResponseForm />;
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="container mx-auto px-4 py-8">
        <div className="flex gap-8">
          {/* Sidebar */}
          <div className="flex-shrink-0">
            <EnhancedReportSidebar 
              activeSection={activeSection}
              onSectionChange={handleSectionChange}
            />
          </div>

          {/* Main Content Area */}
          <div className="flex-1 max-w-4xl">
            {renderActiveComponent()}
          </div>
        </div>
      </div>
    </div>
  );
} 