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
 * MEDICAL/RESCUE SUPPORT PAGE
 * 
 * This page allows for creating and editing medical/rescue support information.
 * It provides comprehensive forms for capturing support details and documentation.
 * 
 * FEATURES:
 * - Medical/rescue unit information
 * - Support type and personnel details
 * - Location and assignment tracking
 * - Remarks and notes section
 * - Scene sketch upload functionality with image preview
 * - Edit toggle functionality like assessment page
 * - Blank field validation
 */

// Mock data for the medical support record
const medicalSupportData = {
   unitId: "EMS-001",
   supportType: "Emergency Medical Response",
   personnelAssigned: "Dr. Sarah Johnson - Lead Paramedic\nMark Williams - EMT\nLisa Chen - Emergency Nurse",
   locationAssigned: "Scene Perimeter - Ambulance Station Alpha",
   remarks: "Medical team arrived at 14:30 and established triage area. Two patients transported to General Hospital with non-life-threatening injuries. Scene remained secure throughout medical operations."
};

export default function MedicalSupportPage() {
   const router = useRouter();
   const params = useParams();
   const [isEditing, setIsEditing] = useState(false);
   const [editableData, setEditableData] = useState(medicalSupportData);
   const [uploadedFiles, setUploadedFiles] = useState<UploadedFile[]>([]);
   
   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);

   // Check if data has content (not all blank)
   const hasContent = () => {
      return editableData.unitId.trim() !== "" ||
             editableData.supportType.trim() !== "" ||
             editableData.personnelAssigned.trim() !== "" ||
             editableData.locationAssigned.trim() !== "" ||
             editableData.remarks.trim() !== "" ||
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
      // TODO: Save medical support data to database/API
      console.log("Saving medical support data:", editableData);
      console.log("Uploaded files:", uploadedFiles);
      setIsEditing(false);
   };

   const handleCancel = () => {
      // Clean up preview URLs
      uploadedFiles.forEach(uploadedFile => {
         if (uploadedFile.preview) {
            URL.revokeObjectURL(uploadedFile.preview);
         }
      });
      setEditableData(medicalSupportData);
      setUploadedFiles([]);
      setIsEditing(false);
   };

   const handleClearAll = () => {
      // Clean up preview URLs
      uploadedFiles.forEach(uploadedFile => {
         if (uploadedFile.preview) {
            URL.revokeObjectURL(uploadedFile.preview);
         }
      });
      setEditableData({
         unitId: "",
         supportType: "",
         personnelAssigned: "",
         locationAssigned: "",
         remarks: ""
      });
      setUploadedFiles([]);
   };

   const handleInputChange = (field: string, value: string) => {
      setEditableData(prev => ({
         ...prev,
         [field]: value
      }));
   };

   const handleDeleteClick = () => {
      setShowDeleteModal(true);
   };

   const handleConfirmDelete = () => {
      console.log("Deleting medical support record:", editableData.unitId);
      
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

   return (
      <main className="flex-1 p-6">
         {/* Header */}
         <div className="mb-6">
            <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
               MEDICAL/RESCUE SUPPORT {editableData.unitId && `- ${editableData.unitId}`}
            </h1>
         </div>

         {/* Main Content Container */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 space-y-6">

            {/* MEDICAL/RESCUE UNIT ID SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  MEDICAL/RESCUE UNIT ID
               </label>
               {isEditing ? (
                  <Input
                     value={editableData.unitId}
                     onChange={(e) => handleInputChange('unitId', e.target.value)}
                     placeholder="Enter medical/rescue unit ID..."
                     className="w-full rounded-lg"
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                     {editableData.unitId || <span className="text-gray-500 italic">No unit ID specified</span>}
                  </div>
               )}
            </div>

            {/* TYPE OF SUPPORT PROVIDED SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  TYPE OF SUPPORT PROVIDED
               </label>
               {isEditing ? (
                  <Input
                     value={editableData.supportType}
                     onChange={(e) => handleInputChange('supportType', e.target.value)}
                     placeholder="Enter type of support provided..."
                     className="w-full rounded-lg"
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                     {editableData.supportType || <span className="text-gray-500 italic">No support type specified</span>}
                  </div>
               )}
            </div>

            {/* PERSONNEL ASSIGNED SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  PERSONNEL ASSIGNED
               </label>
               {isEditing ? (
                  <Textarea
                     value={editableData.personnelAssigned}
                     onChange={(e) => handleInputChange('personnelAssigned', e.target.value)}
                     placeholder="Enter personnel assigned details..."
                     className="w-full rounded-lg border-gray-300"
                     rows={4}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[6rem] whitespace-pre-line">
                     {editableData.personnelAssigned || <span className="text-gray-500 italic">No personnel information specified</span>}
                  </div>
               )}
            </div>

            {/* LOCATION ASSIGNED SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  LOCATION ASSIGNED
               </label>
               {isEditing ? (
                  <Input
                     value={editableData.locationAssigned}
                     onChange={(e) => handleInputChange('locationAssigned', e.target.value)}
                     placeholder="Enter location assigned..."
                     className="w-full rounded-lg"
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                     {editableData.locationAssigned || <span className="text-gray-500 italic">No location specified</span>}
                  </div>
               )}
            </div>

            {/* REMARKS/NOTES SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  REMARKS/NOTES
               </label>
               {isEditing ? (
                  <Textarea
                     value={editableData.remarks}
                     onChange={(e) => handleInputChange('remarks', e.target.value)}
                     placeholder="Enter remarks or additional notes..."
                     className="w-full rounded-lg border-gray-300"
                     rows={6}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[8rem] whitespace-pre-line">
                     {editableData.remarks || <span className="text-gray-500 italic">No remarks or notes</span>}
                  </div>
               )}
            </div>

            {/* SCENE SKETCH SECTION - Using the reusable component */}
            <FileUploadWithPreview
               uploadedFiles={uploadedFiles}
               onFilesChange={handleFilesChange}
               isEditing={isEditing}
               title="SCENE SKETCH"
               description="Supports: JPG, PNG, PDF, DOC files"
               accept=".jpg,.jpeg,.png,.pdf,.doc,.docx"
               maxFiles={10}
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
                           Save Changes
                        </Button>
                     </>
                  ) : (
                     <Button onClick={handleEdit} className="rounded-full">
                        Edit Medical Support
                     </Button>
                  )}

                  <Button
                     variant="destructive"
                     onClick={handleDeleteClick}
                     className="flex items-center gap-2 rounded-full"
                     disabled={!hasContent()} // Disable if no content
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
            evidenceName={editableData.unitId ? `Medical Support ${editableData.unitId}` : "this medical support record"}
         />
      </main>
   );
}