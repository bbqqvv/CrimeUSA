"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { ArrowLeft, Trash2 } from "lucide-react";
import { useRouter } from "next/navigation";
import { DeleteEvidenceModal } from "../../DeleteEvidenceModal";

/**
 * ASSESSMENT DETAIL PAGE
 * 
 * This page displays detailed information about a specific preliminary assessment.
 * It shows comprehensive details including overview, description, condition, and preservation measures.
 * 
 * FEATURES:
 * - Overview section with key information
 * - Detailed description in editable text area
 * - Initial condition assessment
 * - Initial preservation measures taken
 * - Navigation back to main assessment list
 * - Delete functionality with confirmation modal
 */

// Mock data for the assessment detail
// In a real application, this would come from route parameters or API
const assessmentData = {
   id: "PE 01",
   location: "A1 - Kitchen",
   collector: "Lt. James Potter",
   time: "14:25 - 25/06/25",
   overview: "Initial assessment of the kitchen area where the incident occurred. The scene shows signs of disturbance with evidence of a struggle.",
   detailedDescription: "Upon arrival at the scene, the kitchen area displayed clear evidence of a physical altercation. Furniture was overturned, including two dining chairs and a small side table. Glass fragments from a broken drinking glass were scattered across the floor near the sink area. Blood spatter patterns were observed on the wall adjacent to the refrigerator, approximately 3-4 feet from the ground. The victim was found lying prone near the kitchen island with visible injuries to the head and torso. No signs of forced entry were observed through the kitchen windows or back door.",
   initialCondition: "Scene was secured upon arrival. Weather conditions: Clear, temperature 72°F. Lighting: Natural daylight supplemented by overhead kitchen lighting. Access points: Front door (unlocked), back door (locked from inside), two kitchen windows (closed and locked). Time of initial assessment: 14:25 hours. First responders had already established a preliminary perimeter.",
   preservationMeasures: "Immediate establishment of crime scene perimeter using yellow tape in a 30-meter radius around the residence. All personnel required to use designated entry/exit point through front door only. Photographic documentation commenced immediately upon scene assessment. Evidence markers placed for all visible blood spatter and physical evidence. Kitchen area cordoned off as primary scene. Secondary sweep conducted of adjacent rooms. Weather protection measures implemented due to potential for afternoon rain."
};

export default function AssessmentDetailPage() {
   const router = useRouter();
   const [isEditing, setIsEditing] = useState(false);
   const [editableData, setEditableData] = useState(assessmentData);
   
   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);

   const handleBack = () => {
      router.back(); // Navigate back to previous page
   };

   const handleEdit = () => {
      setIsEditing(!isEditing);
   };

   const handleSave = () => {
      // TODO: Save changes to database/API
      console.log("Saving changes:", editableData);
      setIsEditing(false);
   };

   const handleCancel = () => {
      // Reset to original data
      setEditableData(assessmentData);
      setIsEditing(false);
   };

   const handleTextareaChange = (field: string, value: string) => {
      setEditableData(prev => ({
         ...prev,
         [field]: value
      }));
   };

   // Delete handlers for modal
   const handleDeleteClick = () => {
      setShowDeleteModal(true);
   };

   const handleConfirmDelete = () => {
      // TODO: Delete assessment from database/API
      console.log("Deleting assessment:", editableData.id);
      
      // Navigate back to main assessment list after deletion
      router.push('/dot2/initial-response');
      
      // Reset modal state
      setShowDeleteModal(false);
   };

   const handleCloseModal = () => {
      setShowDeleteModal(false);
   };

   return (
      <main className="flex-1 p-6">
         {/* Header with Back Button */}
         <div className="mb-6">
            <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
               PRELIMINARY ASSESSMENT DETAILS {editableData.id}
            </h1>
         </div>

         {/* Main Content Container */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 space-y-6">

            {/* OVERVIEW SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Overview</h2>
               <div className="grid grid-cols-2 gap-4 mb-4">
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Assessment ID</label>
                     <div className="text-sm text-gray-900 bg-gray-50 p-2 rounded">{editableData.id}</div>
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Location</label>
                     <div className="text-sm text-gray-900 bg-gray-50 p-2 rounded">{editableData.location}</div>
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Collector</label>
                     <div className="text-sm text-gray-900 bg-gray-50 p-2 rounded">{editableData.collector}</div>
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Time</label>
                     <div className="text-sm text-gray-900 bg-gray-50 p-2 rounded">{editableData.time}</div>
                  </div>
               </div>
               <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">Summary</label>
                  {isEditing ? (
                     <Textarea
                        value={editableData.overview}
                        onChange={(e) => handleTextareaChange('overview', e.target.value)}
                        className="w-full rounded-lg border-gray-300"
                        rows={3}
                     />
                  ) : (
                     <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed">
                        {editableData.overview}
                     </div>
                  )}
               </div>
            </div>

            {/* DETAILED DESCRIPTION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Detailed Description</h2>
               {isEditing ? (
                  <Textarea
                     value={editableData.detailedDescription}
                     onChange={(e) => handleTextareaChange('detailedDescription', e.target.value)}
                     className="w-full rounded-lg border-gray-300"
                     rows={8}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-4 rounded-lg leading-relaxed">
                     {editableData.detailedDescription}
                  </div>
               )}
            </div>

            {/* INITIAL CONDITION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Initial Condition</h2>
               {isEditing ? (
                  <Textarea
                     value={editableData.initialCondition}
                     onChange={(e) => handleTextareaChange('initialCondition', e.target.value)}
                     className="w-full rounded-lg border-gray-300"
                     rows={6}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-4 rounded-lg leading-relaxed">
                     {editableData.initialCondition}
                  </div>
               )}
            </div>

            {/* INITIAL PRESERVATION MEASURES SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Initial Preservation Measures</h2>
               {isEditing ? (
                  <Textarea
                     value={editableData.preservationMeasures}
                     onChange={(e) => handleTextareaChange('preservationMeasures', e.target.value)}
                     className="w-full rounded-lg border-gray-300"
                     rows={6}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-4 rounded-lg leading-relaxed">
                     {editableData.preservationMeasures}
                  </div>
               )}
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
                  Back to Assessments
               </Button>

               {/* Edit/Save/Cancel and Delete Buttons - Right Side */}
               <div className="flex gap-4">
                  {isEditing ? (
                     <>
                        <Button variant="outline" onClick={handleCancel} className="rounded-full">
                           Cancel
                        </Button>
                        <Button onClick={handleSave} className="rounded-full">
                           Save Changes
                        </Button>
                     </>
                  ) : (
                     <Button onClick={handleEdit} className="rounded-full">
                        Edit Assessment
                     </Button>
                  )}

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
         </div>

         {/* DELETE CONFIRMATION MODAL */}
         <DeleteEvidenceModal
            isOpen={showDeleteModal}
            onClose={handleCloseModal}
            onConfirm={handleConfirmDelete}
            evidenceName={`Assessment ${editableData.id} - ${editableData.location}`}
         />
      </main>
   );
}