"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { ArrowLeft, Trash2 } from "lucide-react";
import { useRouter, useParams } from "next/navigation";
import { DeleteEvidenceModal } from "@/components/features/phase2/DeleteEvidenceModal";
import { FileUploadWithPreview, UploadedFile } from "@/components/features/phase2/FileUploadWithPreview";

/**
 * SCENE PRESERVATION MEASURES PAGE
 * 
 * This page allows for creating and editing scene preservation measures information.
 * It provides comprehensive forms for capturing preservation details and documentation.
 * 
 * FEATURES:
 * - Responsible unit/officer information
 * - Arrival time tracking
 * - Start and end time tracking with AM/PM toggles
 * - Scene preservation methods description
 * - Area covered/perimeter details
 * - Notes and special instructions
 * - Attachment upload functionality with preview
 * - Edit toggle functionality
 * - Blank field validation
 */

// Mock data for the scene preservation measures
const preservationData = {
   responsibleUnit: "Unit Alpha-7 - Officer Martinez",
   arrivalTime: "14:25",
   startTime: "14:30",
   endTime: "18:45",
   description: "Established perimeter using police tape and traffic cones. Secured all entry points to the scene. Posted officers at strategic locations to prevent unauthorized access. Documented all personnel entering and exiting the scene. Maintained chain of custody for all evidence collected.",
   areaCovered: "Intersection of Main St and Oak Ave - 50m radius perimeter. Covered both eastbound and westbound lanes of Main St, and northbound lane of Oak Ave. Total secured area approximately 7,850 square meters.",
   notes: "Weather conditions: Clear, 72°F. Heavy pedestrian traffic in area required additional crowd control measures. Media personnel kept at designated distance of 100m from scene perimeter. Local businesses cooperated with temporary access restrictions."
};

export default function ScenePreservationPage() {
   const router = useRouter();
   const params = useParams();
   const [isEditing, setIsEditing] = useState(false);
   const [editableData, setEditableData] = useState(preservationData);
   const [uploadedFiles, setUploadedFiles] = useState<UploadedFile[]>([]);
   
   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);
   
   // State for time controls
   const [arrivalTime, setArrivalTime] = useState("14:25");
   const [startTime, setStartTime] = useState("14:30");
   const [endTime, setEndTime] = useState("18:45");
   const [startIsAM, setStartIsAM] = useState(false); // false for PM
   const [endIsAM, setEndIsAM] = useState(false); // false for PM

   // Check if data has content (not all blank)
   const hasContent = () => {
      return editableData.responsibleUnit.trim() !== "" ||
             arrivalTime.trim() !== "" ||
             startTime.trim() !== "" ||
             endTime.trim() !== "" ||
             editableData.description.trim() !== "" ||
             editableData.areaCovered.trim() !== "" ||
             editableData.notes.trim() !== "" ||
             uploadedFiles.length > 0;
   };

   const handleBack = () => {
      // Navigate back to initial response page
      router.push(`/${params.role}/reports/${params.reportsId}/initial-response`);
   };

   const handleEdit = () => {
      setIsEditing(!isEditing);
   };

   const handleSave = () => {
      // TODO: Save preservation data to database/API
      console.log("Saving preservation data:", {
         ...editableData,
         arrivalTime,
         startTime: `${startTime} ${startIsAM ? 'AM' : 'PM'}`,
         endTime: `${endTime} ${endIsAM ? 'AM' : 'PM'}`
      });
      console.log("Uploaded files:", uploadedFiles);
      setIsEditing(false);
   };

   const handleCancel = () => {
      // Reset to original data and clean up preview URLs
      uploadedFiles.forEach(uploadedFile => {
         if (uploadedFile.preview) {
            URL.revokeObjectURL(uploadedFile.preview);
         }
      });
      setEditableData(preservationData);
      setArrivalTime("14:25");
      setStartTime("14:30");
      setEndTime("18:45");
      setStartIsAM(false);
      setEndIsAM(false);
      setUploadedFiles([]);
      setIsEditing(false);
   };

   const handleClearAll = () => {
      // Clear all data when in edit mode and clean up preview URLs
      uploadedFiles.forEach(uploadedFile => {
         if (uploadedFile.preview) {
            URL.revokeObjectURL(uploadedFile.preview);
         }
      });
      setEditableData({
         responsibleUnit: "",
         arrivalTime: "",
         startTime: "",
         endTime: "",
         description: "",
         areaCovered: "",
         notes: ""
      });
      setArrivalTime("");
      setStartTime("");
      setEndTime("");
      setStartIsAM(true);
      setEndIsAM(true);
      setUploadedFiles([]);
   };

   const handleInputChange = (field: string, value: string) => {
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
      console.log("Deleting preservation measures");
      
      // Clean up preview URLs
      uploadedFiles.forEach(uploadedFile => {
         if (uploadedFile.preview) {
            URL.revokeObjectURL(uploadedFile.preview);
         }
      });
      
      // Navigate back to initial response page after deletion
      router.push(`/${params.role}/reports/${params.reportsId}/initial-response`);
      
      setShowDeleteModal(false);
   };

   const handleCloseModal = () => {
      setShowDeleteModal(false);
   };

   const handleFilesChange = (files: UploadedFile[]) => {
      setUploadedFiles(files);
   };

   // Time toggle component
   const TimeToggle = ({ isAM, setIsAM, disabled = false }: { isAM: boolean, setIsAM: (value: boolean) => void, disabled?: boolean }) => (
      <div className="flex items-center justify-center bg-gray-200 rounded-full w-24 h-8 px-1 relative">
         <button
            type="button"
            disabled={disabled}
            className={`flex-1 h-6 rounded-full text-sm font-medium focus:outline-none transition-colors duration-200 ${
               isAM 
                  ? 'bg-white text-blue-900 shadow' 
                  : disabled 
                     ? 'text-gray-400' 
                     : 'text-gray-500 hover:text-gray-700'
            }`}
            style={{ marginRight: "2px" }}
            onClick={() => !disabled && setIsAM(true)}
         >
            AM
         </button>
         <button
            type="button"
            disabled={disabled}
            className={`flex-1 h-6 rounded-full text-sm font-medium focus:outline-none transition-colors duration-200 ${
               !isAM 
                  ? 'bg-white text-blue-900 shadow' 
                  : disabled 
                     ? 'text-gray-400' 
                     : 'text-gray-500 hover:text-gray-700'
            }`}
            onClick={() => !disabled && setIsAM(false)}
         >
            PM
         </button>
      </div>
   );

   return (
      <main className="flex-1 p-6">
         {/* Header */}
         <div className="mb-6">
            <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
               SCENE PRESERVATION MEASURES
            </h1>
         </div>

         {/* Main Content Container */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 space-y-6">

            {/* RESPONSIBLE UNIT/OFFICER SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  RESPONSIBLE UNIT/OFFICER
               </label>
               {isEditing ? (
                  <Input
                     value={editableData.responsibleUnit}
                     onChange={(e) => handleInputChange('responsibleUnit', e.target.value)}
                     placeholder="Enter responsible unit/officer..."
                     className="w-full rounded-lg"
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                     {editableData.responsibleUnit || <span className="text-gray-500 italic">No responsible unit/officer specified</span>}
                  </div>
               )}
            </div>

            {/* TIME OF ARRIVAL SECTION */}
            <div className="bg-white rounded-lg p-6">
               <div className="flex justify-between items-center">
                  <label className="font-semibold text-lg">
                     TIME OF ARRIVAL AT THE SCENE
                  </label>
                  <div className="flex items-center gap-2">
                     {isEditing ? (
                        <Input 
                           className="w-20 rounded-full" 
                           value={arrivalTime}
                           onChange={(e) => setArrivalTime(e.target.value)}
                           placeholder="HH:MM"
                        />
                     ) : (
                        <div className="text-sm text-gray-900 bg-gray-50 px-3 py-1 rounded-full min-w-[80px] text-center">
                           {arrivalTime || <span className="text-gray-500 italic">--:--</span>}
                        </div>
                     )}
                  </div>
               </div>
            </div>

            {/* START TIME SECTION */}
            <div className="bg-white rounded-lg p-6">
               <div className="flex justify-between items-center">
                  <label className="font-semibold text-lg">
                     START TIME
                  </label>
                  <div className="flex items-center gap-2">
                     {isEditing ? (
                        <>
                           <Input 
                              className="w-20 rounded-full" 
                              value={startTime}
                              onChange={(e) => setStartTime(e.target.value)}
                              placeholder="HH:MM"
                           />
                           <TimeToggle isAM={startIsAM} setIsAM={setStartIsAM} />
                        </>
                     ) : (
                        <div className="flex items-center gap-2">
                           <div className="text-sm text-gray-900 bg-gray-50 px-3 py-1 rounded-full min-w-[80px] text-center">
                              {startTime || <span className="text-gray-500 italic">--:--</span>}
                           </div>
                           <div className="text-sm text-gray-900 bg-gray-50 px-3 py-1 rounded-full min-w-[60px] text-center">
                              {startTime ? (startIsAM ? 'AM' : 'PM') : <span className="text-gray-500 italic">--</span>}
                           </div>
                        </div>
                     )}
                  </div>
               </div>
            </div>

            {/* END TIME SECTION */}
            <div className="bg-white rounded-lg p-6">
               <div className="flex justify-between items-center">
                  <label className="font-semibold text-lg">
                     END TIME
                  </label>
                  <div className="flex items-center gap-2">
                     {isEditing ? (
                        <>
                           <Input 
                              className="w-20 rounded-full" 
                              value={endTime}
                              onChange={(e) => setEndTime(e.target.value)}
                              placeholder="HH:MM"
                           />
                           <TimeToggle isAM={endIsAM} setIsAM={setEndIsAM} />
                        </>
                     ) : (
                        <div className="flex items-center gap-2">
                           <div className="text-sm text-gray-900 bg-gray-50 px-3 py-1 rounded-full min-w-[80px] text-center">
                              {endTime || <span className="text-gray-500 italic">--:--</span>}
                           </div>
                           <div className="text-sm text-gray-900 bg-gray-50 px-3 py-1 rounded-full min-w-[60px] text-center">
                              {endTime ? (endIsAM ? 'AM' : 'PM') : <span className="text-gray-500 italic">--</span>}
                           </div>
                        </div>
                     )}
                  </div>
               </div>
            </div>

            {/* DESCRIPTION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  DESCRIPTION OF THE SCENE PRESERVATION METHODS
               </label>
               {isEditing ? (
                  <Textarea
                     value={editableData.description}
                     onChange={(e) => handleInputChange('description', e.target.value)}
                     placeholder="Enter description of preservation methods..."
                     className="w-full rounded-lg border-gray-300"
                     rows={6}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[8rem] whitespace-pre-line">
                     {editableData.description || <span className="text-gray-500 italic">No preservation methods described</span>}
                  </div>
               )}
            </div>

            {/* AREA COVERED SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  AREA COVERED / PERIMETER
               </label>
               {isEditing ? (
                  <Textarea
                     value={editableData.areaCovered}
                     onChange={(e) => handleInputChange('areaCovered', e.target.value)}
                     placeholder="Enter area covered and perimeter details..."
                     className="w-full rounded-lg border-gray-300"
                     rows={4}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[6rem] whitespace-pre-line">
                     {editableData.areaCovered || <span className="text-gray-500 italic">No area coverage specified</span>}
                  </div>
               )}
            </div>

            {/* NOTES SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  NOTES / SPECIAL INSTRUCTIONS
               </label>
               {isEditing ? (
                  <Textarea
                     value={editableData.notes}
                     onChange={(e) => handleInputChange('notes', e.target.value)}
                     placeholder="Enter notes or special instructions..."
                     className="w-full rounded-lg border-gray-300"
                     rows={5}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[7rem] whitespace-pre-line">
                     {editableData.notes || <span className="text-gray-500 italic">No notes or special instructions</span>}
                  </div>
               )}
            </div>

            {/* ATTACHMENTS SECTION - Using the reusable component */}
            <FileUploadWithPreview
               uploadedFiles={uploadedFiles}
               onFilesChange={handleFilesChange}
               isEditing={isEditing}
               title="ATTACHMENTS"
               description="Supports: JPG, PNG, PDF, DOC, MP4, MOV files"
               accept=".jpg,.jpeg,.png,.pdf,.doc,.docx,.mp4,.mov,.avi"
               maxFiles={20}
            />

            {/* ACTION BUTTONS */}
            <div className="flex justify-between items-center bg-white p-4 rounded-lg">
               {/* Back Button - Left Side */}
               <Button
                  variant="outline"
                  onClick={handleBack}
                  className="flex items-center gap-2 rounded-full"
               >
                  <ArrowLeft className="w-4 h-4" />
                  Back to Initial Response
               </Button>

               {/* Edit/Save/Cancel and Delete Buttons - Right Side */}
               <div className="flex gap-4">
                  {isEditing ? (
                     <>
                        <Button variant="outline" onClick={handleCancel} className="rounded-full">
                           Cancel
                        </Button>
                        <Button variant="outline" onClick={handleClearAll} className="rounded-full text-red-600 hover:text-red-700">
                           Clear All
                        </Button>
                        <Button onClick={handleSave} className="rounded-full">
                           Save Measures
                        </Button>
                     </>
                  ) : (
                     <Button onClick={handleEdit} className="rounded-full">
                        Edit Preservation Measures
                     </Button>
                  )}

                  <Button
                     variant="destructive"
                     onClick={handleDeleteClick}
                     className="flex items-center gap-2 rounded-full"
                     disabled={!hasContent()}
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
            evidenceName="these scene preservation measures"
         />
      </main>
   );
}