"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { Input } from "@/components/ui/input";
import { ArrowLeft, Trash2 } from "lucide-react";
import { useRouter, useParams } from "next/navigation";
import { DeleteEvidenceModal } from "@/components/features/phase2/DeleteEvidenceModal";
import { FileUploadWithPreview, UploadedFile } from "@/components/features/phase2/FileUploadWithPreview";

/**
 * WITNESS STATEMENT DETAIL PAGE
 * 
 * This page displays detailed information about a specific witness statement.
 * It shows comprehensive details including witness information, statement content, and evidence files.
 * Features edit toggle functionality similar to other detail pages.
 * 
 * FEATURES:
 * - Witness information form (name, date, contact, role)
 * - Detailed statement recording
 * - Evidence file upload and management with preview
 * - Edit toggle functionality
 * - Navigation back to statements list
 * - Delete functionality with confirmation modal
 * - Blank field validation
 */

// Mock data structure for existing witness statement
const witnessStatementData = {
   witnessName: "Sarah Johnson",
   date: "2024-07-03",
   phoneNumber: "+1-555-0123",
   role: "Witness",
   detailedStatement: "I was walking down Main Street around 2:30 PM when I heard a loud crash. I turned around and saw two vehicles had collided at the intersection. The blue sedan ran the red light and hit the white SUV that was making a left turn. I immediately called 911 and stayed at the scene to provide assistance. The driver of the blue sedan appeared to be on their phone at the time of impact."
};

export default function WitnessStatementDetailPage() {
   const router = useRouter();
   const params = useParams();
   const [isEditing, setIsEditing] = useState(false);
   const [editableData, setEditableData] = useState(witnessStatementData);
   const [uploadedFiles, setUploadedFiles] = useState<UploadedFile[]>([]);
   
   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);

   // Check if data has content (not all blank)
   const hasContent = () => {
      return editableData.witnessName.trim() !== "" ||
             editableData.date.trim() !== "" ||
             editableData.phoneNumber.trim() !== "" ||
             editableData.role.trim() !== "" ||
             editableData.detailedStatement.trim() !== "" ||
             uploadedFiles.length > 0;
   };

   const handleBack = () => {
      // Navigate back to scene information page with correct dynamic parameters
      router.push(`/${params.role}/reports/${params.reportsId}/scene-information`);
   };

   const handleEdit = () => {
      setIsEditing(!isEditing);
   };

   const handleSave = () => {
      // TODO: Save witness statement to database/API
      console.log("Saving witness statement:", editableData);
      console.log("Evidence files:", uploadedFiles);
      setIsEditing(false);
   };

   const handleCancel = () => {
      // Reset to original data and clean up preview URLs
      uploadedFiles.forEach(uploadedFile => {
         if (uploadedFile.preview) {
            URL.revokeObjectURL(uploadedFile.preview);
         }
      });
      setEditableData(witnessStatementData);
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
         witnessName: "",
         date: "",
         phoneNumber: "",
         role: "",
         detailedStatement: ""
      });
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
      console.log("Deleting witness statement:", editableData.witnessName);
      
      // Clean up preview URLs
      uploadedFiles.forEach(uploadedFile => {
         if (uploadedFile.preview) {
            URL.revokeObjectURL(uploadedFile.preview);
         }
      });
      
      // Navigate back to scene information page after deletion
      router.push(`/${params.role}/reports/${params.reportsId}/scene-information`);
      
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
               WITNESS STATEMENT DETAILS {editableData.witnessName && `- ${editableData.witnessName}`}
            </h1>
         </div>

         {/* Main Content Container */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 space-y-6">

            {/* WITNESS INFORMATION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Witness Information</h2>
               <div className="grid grid-cols-2 gap-4">
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Witness Name</label>
                     {isEditing ? (
                        <Input
                           value={editableData.witnessName}
                           onChange={(e) => handleInputChange('witnessName', e.target.value)}
                           placeholder="Enter witness name..."
                           className="w-full rounded-lg"
                        />
                     ) : (
                        <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                           {editableData.witnessName || <span className="text-gray-500 italic">No witness name specified</span>}
                        </div>
                     )}
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Date</label>
                     {isEditing ? (
                        <Input
                           type="date"
                           value={editableData.date}
                           onChange={(e) => handleInputChange('date', e.target.value)}
                           className="w-full rounded-lg"
                        />
                     ) : (
                        <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                           {editableData.date ? new Date(editableData.date).toLocaleDateString() : <span className="text-gray-500 italic">No date specified</span>}
                        </div>
                     )}
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Contact Information (Phone Number)</label>
                     {isEditing ? (
                        <Input
                           value={editableData.phoneNumber}
                           onChange={(e) => handleInputChange('phoneNumber', e.target.value)}
                           placeholder="Enter phone number..."
                           className="w-full rounded-lg"
                        />
                     ) : (
                        <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                           {editableData.phoneNumber || <span className="text-gray-500 italic">No phone number specified</span>}
                        </div>
                     )}
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Role</label>
                     {isEditing ? (
                        <select
                           value={editableData.role}
                           onChange={(e) => handleInputChange('role', e.target.value)}
                           className="w-full rounded-lg border border-gray-300 px-3 py-2 focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                        >
                           <option value="">Select role...</option>
                           <option value="Witness">Witness</option>
                           <option value="Victim">Victim</option>
                           <option value="Bystander">Bystander</option>
                           <option value="First Responder">First Responder</option>
                        </select>
                     ) : (
                        <div className="text-sm text-gray-900 bg-gray-50 p-3 rounded-lg leading-relaxed min-h-[2.5rem] flex items-center">
                           {editableData.role || <span className="text-gray-500 italic">No role specified</span>}
                        </div>
                     )}
                  </div>
               </div>
            </div>

            {/* DETAILED STATEMENT SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Detailed Statement</h2>
               {isEditing ? (
                  <Textarea
                     value={editableData.detailedStatement}
                     onChange={(e) => handleInputChange('detailedStatement', e.target.value)}
                     className="w-full rounded-lg border-gray-300"
                     rows={8}
                     placeholder="Enter detailed witness statement..."
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-4 rounded-lg leading-relaxed min-h-[12rem] whitespace-pre-line">
                     {editableData.detailedStatement || <span className="text-gray-500 italic">No detailed statement provided</span>}
                  </div>
               )}
            </div>

            {/* EVIDENCE FILES SECTION - Using the reusable component */}
            <FileUploadWithPreview
               uploadedFiles={uploadedFiles}
               onFilesChange={handleFilesChange}
               isEditing={isEditing}
               title="EVIDENCE FILES"
               description="Supports: JPG, PNG, PDF, DOC, MP3, MP4, MOV files"
               accept=".jpg,.jpeg,.png,.pdf,.doc,.docx,.mp3,.mp4,.mov,.wav"
               maxFiles={15}
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
                  Back to Scene Information
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
                           Save Statement
                        </Button>
                     </>
                  ) : (
                     <Button onClick={handleEdit} className="rounded-full">
                        Edit Statement
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
            evidenceName={editableData.witnessName ? `Witness Statement - ${editableData.witnessName}` : "this witness statement"}
         />
      </main>
   );
}