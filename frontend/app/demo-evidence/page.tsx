"use client";

import { Suspense, useState } from "react";
import { Button } from "@/components/ui/button";
import { ArrowLeft, Trash2 } from "lucide-react";
import { useRouter } from "next/navigation";
import { DeleteEvidenceModal } from "@/components/features/phase2/DeleteEvidenceModal";
import { EvidenceForm } from "@/components/form/EvidenceForm";
import { toast } from "sonner";

function DemoEvidenceContent() {
   const router = useRouter();
   const [showDeleteModal, setShowDeleteModal] = useState(false);
   
   // Pre-filled extremely realistic forensic evidence data for the screenshot
   const mockEvidence = {
      id: "EVD-2026-094",
      location: "1042 North Charles Street, Baltimore, MD (Master Bedroom - Floor beside bedside table)",
      collector: "Special Agent John Miller (ID: 4892)",
      time: "May 30, 2026 - 22:45 UTC",
      overview: "A semi-automatic pistol (9mm Glock 19) with a defaced serial number, found lying on the hardwood floor between the bed and the bedside table.",
      detailedDescription: "The item is a black polymer-framed semi-automatic pistol, Glock 19, 9x19mm caliber. The serial number on the slide has been intentionally defaced and scratched, making it partially illegible under natural light. One magazine is inserted in the grip, containing 12 live rounds of 9mm ammunition. Traces of dark-red organic staining (suspected blood) are visible on the left side of the slide and polymer grip panels.",
      initialCondition: "The firearm is structurally intact but shows significant signs of physical tampering on the serial number engraving. The slide is locked back, indicating it was fired until empty or jammed. The exterior surfaces are dry, with light dust and several smudge marks on the polymer frame (suspected latent fingerprints).",
      preservationMeasures: "The firearm was handled wearing sterile nitrile gloves. Photos were taken in situ before recovery. The magazine was carefully ejected, and the chamber was cleared for safety. The weapon was placed inside an anti-static, rigid cardboard evidence collection box and secured with zip ties to prevent movement. Sealed with red tamper-evident forensic evidence tape (Seal #BALT-4892-A)."
   };

   const [evidenceData, setEvidenceData] = useState<any>(mockEvidence);

   const handleBack = () => {
      toast.info("Demo mode: Back navigation is disabled.");
   };

   const handleFormSubmit = (data: any) => {
      setEvidenceData(data);
      toast.success("Demo mode: Evidence saved successfully!");
   };

   const handleFormCancel = () => {
      toast.info("Demo mode: Cancel clicked.");
   };

   const handleDeleteClick = () => {
      setShowDeleteModal(true);
   };

   const handleConfirmDelete = () => {
      toast.success("Demo mode: Evidence deleted successfully!");
      setShowDeleteModal(false);
   };

   const handleCloseModal = () => {
      setShowDeleteModal(false);
   };

   return (
      <main className="flex-1 p-6 max-w-5xl mx-auto">
         {/* Header */}
         <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
            EVIDENCE DETAILS - {evidenceData.id}
         </h1>

         {/* Main Content Container */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 space-y-6">
            {/* Evidence Form */}
            <EvidenceForm
               initialData={evidenceData}
               onSubmit={handleFormSubmit}
               onCancel={handleFormCancel}
               isLoading={false}
            />

            {/* Navigation and Delete Buttons */}
            <div className="flex justify-between items-center bg-white p-4 rounded-lg shadow-sm">
               {/* Back Button - Left Side */}
               <Button
                  variant="outline"
                  onClick={handleBack}
                  className="flex items-center gap-2 rounded-full"
               >
                  <ArrowLeft className="w-4 h-4" />
                  Back to Evidence List
               </Button>

               {/* Delete Button - Right Side */}
               <Button
                  variant="destructive"
                  onClick={handleDeleteClick}
                  className="flex items-center gap-2 rounded-full"
               >
                  <Trash2 className="w-4 h-4" />
                  Delete
               </Button>
            </div>
         </div>

         {/* DELETE CONFIRMATION MODAL */}
         <DeleteEvidenceModal
            isOpen={showDeleteModal}
            onClose={handleCloseModal}
            onConfirm={handleConfirmDelete}
            evidenceName={`Evidence ${evidenceData.id} - ${evidenceData.location}`}
         />
      </main>
   );
}

export default function DemoEvidencePage() {
   return (
      <Suspense fallback={<div className="p-6 text-center">Loading Demo Page...</div>}>
         <DemoEvidenceContent />
      </Suspense>
   );
}
