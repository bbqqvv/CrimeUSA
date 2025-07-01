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
      {/* Header */}
      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h1 className="text-2xl font-bold text-center mb-6">INITIAL RESPONSE</h1>
      </div>

      {/* Form Sections */}
      <DispatchTimeSection />
      <ArrivalTimeSection />
      <OfficersListSection />
      <SceneAssessmentSection />
      <PreservationMeasuresSection />
      <MedicalSupportSection />

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