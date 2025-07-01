import { Button } from "@/components/ui/button";
import {
  DispatchTimeSection,
  ArrivalTimeSection,
  OfficersListSection,
  SceneAssessmentSection,
  PreservationMeasuresSection,
  MedicalSupportSection
} from ".";

export default function InitialResponseForm() {
  return (
    <div className="space-y-6">
      {/* Main Form Container with Background */}
      <div className="bg-[#E7E7E7] rounded-lg shadow-sm border">
        {/* Form Header */}
        <h1 className="bg-[#C8E3FF] text-2xl font-bold text-center mb-6 py-2 mx-0 rounded-t-lg">INITIAL RESPONSE</h1>
        
        {/* Form Sections */}
        <div className="space-y-6 p-6">
          <DispatchTimeSection />
          <ArrivalTimeSection />
          <OfficersListSection />
          <SceneAssessmentSection />
          <PreservationMeasuresSection />
          <MedicalSupportSection />
        </div>
      </div>

      {/* Action Buttons */}
      <div className="flex justify-end gap-4 pt-6">
        <Button variant="outline" size="lg">
          Cancel
        </Button>
        <Button size="lg">
          Save
        </Button>
      </div>
    </div>
  );
} 