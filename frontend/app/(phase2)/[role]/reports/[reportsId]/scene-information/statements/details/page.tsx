"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { Input } from "@/components/ui/input";
import { ArrowLeft, Trash2, Upload, File } from "lucide-react";
import { useRouter } from "next/navigation";
import { DeleteEvidenceModal } from "@/components/features/phase2/DeleteEvidenceModal";

/**
 * WITNESS STATEMENT DETAIL PAGE
 * 
 * This page displays detailed information about a specific witness statement.
 * It shows comprehensive details including witness information, statement content, and evidence files.
 * This is a blank, editable version for creating new witness statements.
 * 
 * FEATURES:
 * - Witness information form (name, date, contact, role)
 * - Detailed statement recording
 * - Evidence file upload and management
 * - Navigation back to statements list
 * - Delete functionality with confirmation modal
 * - All fields editable by default
 */

// Blank data structure for new witness statement
const blankWitnessData = {
   witnessName: "",
   date: "",
   phoneNumber: "",
   role: "",
   detailedStatement: ""
};

// Dummy evidence files
const dummyFiles = [
   { id: 1, name: "witness_audio_recording.mp3", type: "Audio", size: "2.4 MB" },
   { id: 2, name: "witness_photo_evidence.jpg", type: "Image", size: "1.8 MB" },
   { id: 3, name: "witness_document.pdf", type: "Document", size: "0.9 MB" }
];

export default function WitnessStatementDetailPage() {
   const router = useRouter();
   const [witnessData, setWitnessData] = useState(blankWitnessData);
   const [evidenceFiles, setEvidenceFiles] = useState(dummyFiles);
   
   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);

   const handleBack = () => {
      router.back(); // Navigate back to previous page
   };

   const handleSave = () => {
      // TODO: Save new witness statement to database/API
      console.log("Saving witness statement:", witnessData);
      console.log("Evidence files:", evidenceFiles);
   };

   const handleCancel = () => {
      // Reset to blank data
      setWitnessData(blankWitnessData);
   };

   const handleInputChange = (field: string, value: string) => {
      setWitnessData(prev => ({
         ...prev,
         [field]: value
      }));
   };

   // Delete handlers for modal
   const handleDeleteClick = () => {
      setShowDeleteModal(true);
   };

   const handleConfirmDelete = () => {
      // TODO: Delete witness statement from database/API
      console.log("Deleting witness statement:", witnessData.witnessName);
      
      // Navigate back to statements list after deletion
      router.push('/dot2/scene-information');
      
      // Reset modal state
      setShowDeleteModal(false);
   };

   const handleCloseModal = () => {
      setShowDeleteModal(false);
   };

   // File upload handler
   const handleFileUpload = () => {
      // TODO: Implement file upload logic
      console.log("Upload file clicked");
      // For now, just add a dummy file
      const newFile = {
         id: evidenceFiles.length + 1,
         name: `uploaded_file_${Date.now()}.pdf`,
         type: "Document",
         size: "1.2 MB"
      };
      setEvidenceFiles(prev => [...prev, newFile]);
   };

   // File delete handler
   const handleDeleteFile = (fileId: number) => {
      setEvidenceFiles(prev => prev.filter(file => file.id !== fileId));
   };

   return (
      <main className="flex-1 p-6">
         {/* Header */}
         <div className="mb-6">
            <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
               WITNESS STATEMENT DETAILS {witnessData.witnessName && `- ${witnessData.witnessName}`}
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
                     <Input
                        value={witnessData.witnessName}
                        onChange={(e) => handleInputChange('witnessName', e.target.value)}
                        placeholder="Enter witness name..."
                        className="w-full rounded-lg"
                     />
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Date</label>
                     <Input
                        type="date"
                        value={witnessData.date}
                        onChange={(e) => handleInputChange('date', e.target.value)}
                        className="w-full rounded-lg"
                     />
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Contact Information (Phone Number)</label>
                     <Input
                        value={witnessData.phoneNumber}
                        onChange={(e) => handleInputChange('phoneNumber', e.target.value)}
                        placeholder="Enter phone number..."
                        className="w-full rounded-lg"
                     />
                  </div>
                  <div>
                     <label className="block text-sm font-medium text-gray-700 mb-1">Role</label>
                     <select
                        value={witnessData.role}
                        onChange={(e) => handleInputChange('role', e.target.value)}
                        className="w-full rounded-lg border border-gray-300 px-3 py-2 focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                     >
                        <option value="">Select role...</option>
                        <option value="Witness">Witness</option>
                        <option value="Victim">Victim</option>
                     </select>
                  </div>
               </div>
            </div>

            {/* DETAILED STATEMENT SECTION */}
            <div className="bg-white rounded-lg p-6">
               <h2 className="text-xl font-bold text-gray-900 mb-4">Detailed Statement</h2>
               <Textarea
                  value={witnessData.detailedStatement}
                  onChange={(e) => handleInputChange('detailedStatement', e.target.value)}
                  className="w-full rounded-lg border-gray-300"
                  rows={8}
                  placeholder="Enter detailed witness statement..."
               />
            </div>

            {/* EVIDENCE FILES SECTION */}
            <div className="bg-white rounded-lg p-6">
               <div className="flex justify-between items-center mb-4">
                  <h2 className="text-xl font-bold text-gray-900">Evidence Files</h2>
                  <Button
                     onClick={handleFileUpload}
                     className="flex items-center gap-2 rounded-lg"
                  >
                     <Upload className="w-4 h-4" />
                     Upload File
                  </Button>
               </div>
               
               {/* File List */}
               <div className="space-y-3">
                  {evidenceFiles.map((file) => (
                     <div key={file.id} className="flex items-center justify-between p-3 border border-gray-200 rounded-lg bg-gray-50">
                        <div className="flex items-center gap-3">
                           <File className="w-5 h-5 text-blue-600" />
                           <div>
                              <div className="font-medium text-gray-900">{file.name}</div>
                              <div className="text-sm text-gray-500">{file.type} • {file.size}</div>
                           </div>
                        </div>
                        <div className="flex gap-2">
                           <Button
                              variant="outline"
                              size="sm"
                              className="rounded-lg"
                           >
                              Download
                           </Button>
                           <Button
                              variant="destructive"
                              size="sm"
                              onClick={() => handleDeleteFile(file.id)}
                              className="rounded-lg"
                           >
                              <Trash2 className="w-4 h-4" />
                           </Button>
                        </div>
                     </div>
                  ))}
                  
                  {evidenceFiles.length === 0 && (
                     <div className="text-center py-8 text-gray-500">
                        No evidence files uploaded yet.
                     </div>
                  )}
               </div>
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
                  Back to Statements
               </Button>

               {/* Save/Cancel and Delete Buttons - Right Side */}
               <div className="flex gap-4">
                  <Button variant="outline" onClick={handleCancel} className="rounded-full">
                     Clear All
                  </Button>
                  <Button onClick={handleSave} className="rounded-full">
                     Save Statement
                  </Button>

                  <Button
                     variant="destructive"
                     onClick={handleDeleteClick}
                     className="flex items-center gap-2 rounded-full"
                     disabled={!witnessData.witnessName} // Disable if no name entered
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
            evidenceName={witnessData.witnessName ? `Witness Statement - ${witnessData.witnessName}` : "this witness statement"}
         />
      </main>
   );
}