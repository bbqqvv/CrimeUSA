"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { Input } from "@/components/ui/input";
import { ArrowLeft, Trash2 } from "lucide-react";
import { useRouter } from "next/navigation";
import { DeleteEvidenceModal } from "../../DeleteEvidenceModal";

/**
 * EVIDENCE DETAIL PAGE
 * 
 * This page displays detailed information about a specific piece of evidence.
 * It shows comprehensive details including overview, description, condition, and preservation measures.
 * This is a blank, editable version for creating new evidence records.
 * 
 * FEATURES:
 * - Overview section with key information (editable)
 * - Detailed description in editable text area
 * - Initial condition assessment (editable)
 * - Initial preservation measures taken (editable)
 * - Navigation back to main evidence list
 * - Delete functionality with confirmation modal
 * - All fields editable by default
 */

// Blank data structure for new evidence
const blankEvidenceData = {
   id: "",
   location: "",
   collector: "",
   time: "",
   overview: "",
   detailedDescription: "",
   initialCondition: "",
   preservationMeasures: ""
};

export default function EvidenceDetailPage() {
   const router = useRouter();
   const [evidenceData, setEvidenceData] = useState(blankEvidenceData);
   
   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);

   const handleBack = () => {
      router.back(); // Navigate back to previous page
   };

   const handleSave = () => {
      // TODO: Save new evidence to database/API
      console.log("Saving evidence:", evidenceData);
      // Could navigate back after successful save
      // router.push('/dot2/scene-information');
   };

   const handleCancel = () => {
      // Reset to blank data
      setEvidenceData(blankEvidenceData);
   };

   const handleInputChange = (field: string, value: string) => {
      setEvidenceData(prev => ({
         ...prev,
         [field]: value
      }));
   };

   const handleTextareaChange = (field: string, value: string) => {
      setEvidenceData(prev => ({
         ...prev,
         [field]: value
      }));
   };

   // Delete handlers for modal
   const handleDeleteClick = () => {
      setShowDeleteModal(true);
   };

   const handleConfirmDelete = () => {
      // TODO: Delete evidence from database/API
      console.log("Deleting evidence:", evidenceData.id);
      
      // Navigate back to main evidence list after deletion
      router.push('/dot2/scene-information');
      
      // Reset modal state
      setShowDeleteModal(false);
   };

   const handleCloseModal = () => {
      setShowDeleteModal(false);
   };

   return (
      <main className="flex-1 p-6">
         {/* Header */}
         <div className="mb-6">
            <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
               EVIDENCE DETAILS {evidenceData.id && `- ${evidenceData.id}`}
            </h1>
         </div>

         {/* Main Content Container */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 space-y-6">

            {/* OVERVIEW SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Overview</h2>
               <div className="grid grid-cols-2 gap-4 mb-4">
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Evidence ID</label>
                     <Input
                        value={evidenceData.id}
                        onChange={(e) => handleInputChange('id', e.target.value)}
                        placeholder="Enter evidence ID..."
                        className="w-full rounded-lg"
                     />
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Location</label>
                     <Input
                        value={evidenceData.location}
                        onChange={(e) => handleInputChange('location', e.target.value)}
                        placeholder="Enter location..."
                        className="w-full rounded-lg"
                     />
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Collector</label>
                     <Input
                        value={evidenceData.collector}
                        onChange={(e) => handleInputChange('collector', e.target.value)}
                        placeholder="Enter collector name..."
                        className="w-full rounded-lg"
                     />
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Time</label>
                     <Input
                        value={evidenceData.time}
                        onChange={(e) => handleInputChange('time', e.target.value)}
                        placeholder="Enter time..."
                        className="w-full rounded-lg"
                     />
                  </div>
               </div>
               <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">Summary</label>
                  <Textarea
                     value={evidenceData.overview}
                     onChange={(e) => handleTextareaChange('overview', e.target.value)}
                     className="w-full rounded-lg border-gray-300"
                     rows={3}
                     placeholder="Enter evidence summary..."
                  />
               </div>
            </div>

            {/* DETAILED DESCRIPTION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Detailed Description</h2>
               <Textarea
                  value={evidenceData.detailedDescription}
                  onChange={(e) => handleTextareaChange('detailedDescription', e.target.value)}
                  className="w-full rounded-lg border-gray-300"
                  rows={8}
                  placeholder="Enter detailed description of the evidence..."
               />
            </div>

            {/* INITIAL CONDITION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Initial Condition</h2>
               <Textarea
                  value={evidenceData.initialCondition}
                  onChange={(e) => handleTextareaChange('initialCondition', e.target.value)}
                  className="w-full rounded-lg border-gray-300"
                  rows={6}
                  placeholder="Enter initial condition of the evidence..."
               />
            </div>

            {/* INITIAL PRESERVATION MEASURES SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Initial Preservation Measures</h2>
               <Textarea
                  value={evidenceData.preservationMeasures}
                  onChange={(e) => handleTextareaChange('preservationMeasures', e.target.value)}
                  className="w-full rounded-lg border-gray-300"
                  rows={6}
                  placeholder="Enter preservation measures taken..."
               />
            </div>

            {/* ACTION BUTTONS */}
            <div className="flex justify-between items-center bg-white p-4 rounded-lg">
               {/* Back Button - Left Side */}
               <Button
                  variant="outline"
                  onClick={handleBack}
                  className="flex items-center gap-2 rounded-full"
               >
                  <ArrowLeft className="w-4 h-4" />
                  Back to Evidence List
               </Button>

               {/* Save/Cancel and Delete Buttons - Right Side */}
               <div className="flex gap-4">
                  <Button variant="outline" onClick={handleCancel} className="rounded-full">
                     Clear All
                  </Button>
                  <Button onClick={handleSave} className="rounded-full">
                     Save Evidence
                  </Button>

                  <Button
                     variant="destructive"
                     onClick={handleDeleteClick}
                     className="flex items-center gap-2 rounded-full"
                     disabled={!evidenceData.id} // Disable if no ID entered
                  >
                     <Trash2 className="w-4 h-4" />
                     Delete
                  </Button>
               </div>
            </div>
         </div>

         {/* DELETE CONFIRMATION MODAL */}
         <DeleteEvidenceModal
            isOpen={showDeleteModal}
            onClose={handleCloseModal}
            onConfirm={handleConfirmDelete}
            evidenceName={evidenceData.id ? `Evidence ${evidenceData.id} - ${evidenceData.location}` : "this evidence"}
         />
      </main>
   );
}