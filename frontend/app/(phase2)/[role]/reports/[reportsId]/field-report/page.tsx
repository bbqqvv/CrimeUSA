"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { ArrowLeft, Trash2, ExternalLink } from "lucide-react";
import { useRouter, useParams } from "next/navigation";
import { DeleteEvidenceModal } from "@/components/features/phase2/DeleteEvidenceModal";
import { SectionContainer } from "@/components/features/phase2/SectionContainer";
import { DataTable } from "@/components/features/phase2/DataTable";
import { ActionButtons } from "@/components/features/phase2/ActionButtons";

/**
 * FIELD REPORT PAGE
 * 
 * This page provides a comprehensive overview of the field report with quick access
 * to different sections and detailed information entry capabilities.
 * 
 * FEATURES:
 * - Initial Response overview with details button
 * - Scene Information overview with details button
 * - Report details text input
 * - Images and videos table display
 * - Level assessment with urgency dropdown and description
 * - Edit toggle functionality
 * - Navigation to related pages
 */

// Mock data for the field report
const fieldReportData = {
   reportDetails: "Traffic accident investigation at the intersection of Main Street and Oak Avenue. Two vehicles involved: a blue sedan (License: ABC-123) and a white SUV (License: XYZ-789). The blue sedan appears to have run a red light while the SUV was making a legal left turn. Initial assessment shows moderate damage to both vehicles. No fatalities reported, but minor injuries to the SUV driver who was transported to General Hospital for evaluation. Scene was secured and traffic redirected through alternate routes.",
   levelAssessment: {
      urgency: "URGENT",
      description: "Priority investigation required due to potential traffic light malfunction reported by witnesses. Heavy traffic area with history of similar incidents. Immediate analysis needed to determine if infrastructure improvements are necessary to prevent future accidents."
   }
};

// Mock data for images and videos
const mediaFiles = [
   {
      fileName: "scene_overview.jpg",
      type: "Image",
      capturedBy: "Officer Martinez",
      timestamp: "2024-07-03 14:25:30",
      size: "2.4 MB"
   },
   {
      fileName: "vehicle_damage_sedan.jpg",
      type: "Image", 
      capturedBy: "Officer Martinez",
      timestamp: "2024-07-03 14:28:15",
      size: "1.8 MB"
   },
   {
      fileName: "witness_statement.mp4",
      type: "Video",
      capturedBy: "Detective Johnson", 
      timestamp: "2024-07-03 15:10:22",
      size: "45.2 MB"
   },
   {
      fileName: "traffic_light_view.jpg",
      type: "Image",
      capturedBy: "Officer Chen",
      timestamp: "2024-07-03 14:32:45", 
      size: "2.1 MB"
   }
];

// Mock data for overview sections
const initialResponseOverview = [
   {
      field: "Dispatch Time",
      value: "14:15 PM"
   },
   {
      field: "Arrival Time", 
      value: "14:25 PM"
   },
   {
      field: "First Responding Officer",
      value: "Officer Martinez - Badge #4521"
   },
   {
      field: "Status",
      value: "Scene Secured"
   }
];

const sceneInformationOverview = [
   {
      field: "Location",
      value: "Main Street & Oak Avenue Intersection"
   },
   {
      field: "Weather Conditions",
      value: "Clear, 72°F"
   },
   {
      field: "Witnesses",
      value: "3 witnesses interviewed"
   },
   {
      field: "Evidence Collected",
      value: "Physical evidence secured"
   }
];

export default function FieldReportPage() {
   const router = useRouter();
   const params = useParams();
   const [isEditing, setIsEditing] = useState(false);
   const [editableData, setEditableData] = useState(fieldReportData);
   
   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);

   // Check if data has content
   const hasContent = () => {
      return editableData.reportDetails.trim() !== "" ||
             editableData.levelAssessment.description.trim() !== "";
   };

   const handleBack = () => {
      // Navigate back to reports overview
      router.push(`/${params.role}/reports/${params.reportsId}`);
   };

   const handleEdit = () => {
      setIsEditing(!isEditing);
   };

   const handleSave = () => {
      // TODO: Save field report to database/API
      console.log("Saving field report:", editableData);
      setIsEditing(false);
   };

   const handleCancel = () => {
      // Reset to original data
      setEditableData(fieldReportData);
      setIsEditing(false);
   };

   const handleClearAll = () => {
      // Clear all data when in edit mode
      setEditableData({
         reportDetails: "",
         levelAssessment: {
            urgency: "NOT URGENT",
            description: ""
         }
      });
   };

   const handleInputChange = (field: string, value: string) => {
      if (field.startsWith('levelAssessment.')) {
         const subField = field.split('.')[1];
         setEditableData(prev => ({
            ...prev,
            levelAssessment: {
               ...prev.levelAssessment,
               [subField]: value
            }
         }));
      } else {
         setEditableData(prev => ({
            ...prev,
            [field]: value
         }));
      }
   };

   // Navigation functions
   const handleInitialResponseDetails = () => {
      router.push(`/${params.role}/reports/${params.reportsId}/initial-response`);
   };

   const handleSceneInformationDetails = () => {
      router.push(`/${params.role}/reports/${params.reportsId}/scene-information`);
   };

   const handleMediaView = (media: any, index: number) => {
      router.push(`/${params.role}/reports/${params.reportsId}/scene-information/media`);
   };

   // Delete handlers
   const handleDeleteClick = () => {
      setShowDeleteModal(true);
   };

   const handleConfirmDelete = () => {
      console.log("Deleting field report");
      router.push(`/${params.role}/reports/${params.reportsId}`);
      setShowDeleteModal(false);
   };

   const handleCloseModal = () => {
      setShowDeleteModal(false);
   };

   // Column configurations
   const overviewColumns = [
      { key: "field", label: "Field" },
      { key: "value", label: "Value" }
   ];

   const mediaColumns = [
      { key: "fileName", label: "File Name" },
      { key: "type", label: "Type" },
      { key: "capturedBy", label: "Captured By" },
      { key: "timestamp", label: "Timestamp" },
      { key: "size", label: "Size" }
   ];

   return (
      <main className="flex-1 p-6">
         {/* Header */}
         <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
            FIELD REPORT - CASE #{params.reportsId}
         </h1>

         {/* Main Content Container */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 pt-10">

            {/* INITIAL RESPONSE OVERVIEW SECTION */}
            <div className="mb-6 bg-white p-4">
               <div className="flex justify-between items-center mb-4">
                  <label className="font-semibold text-md">
                     INITIAL RESPONSE
                  </label>
                  <Button
                     variant="outline"
                     size="sm"
                     onClick={handleInitialResponseDetails}
                     className="flex items-center gap-2 rounded-full bg-blue-100"
                  >
                     <ExternalLink className="w-4 h-4" />
                     Details
                  </Button>
               </div>
               <DataTable
                  columns={overviewColumns}
                  data={initialResponseOverview}
               />
            </div>

            {/* SCENE INFORMATION OVERVIEW SECTION */}
            <div className="mb-6 bg-white p-4">
               <div className="flex justify-between items-center mb-4">
                  <label className="font-semibold text-md">
                     SCENE INFORMATION
                  </label>
                  <Button
                     variant="outline"
                     size="sm"
                     onClick={handleSceneInformationDetails}
                     className="flex items-center gap-2 rounded-full bg-blue-100"
                  >
                     <ExternalLink className="w-4 h-4" />
                     Details
                  </Button>
               </div>
               <DataTable
                  columns={overviewColumns}
                  data={sceneInformationOverview}
               />
            </div>

            {/* REPORT DETAILS SECTION */}
            <div className="mb-6 bg-white p-4">
               <label className="block font-semibold text-md mb-4">
                  REPORT DETAILS
               </label>
               {isEditing ? (
                  <Textarea
                     value={editableData.reportDetails}
                     onChange={(e) => handleInputChange('reportDetails', e.target.value)}
                     placeholder="Enter detailed report information..."
                     className="w-full rounded-lg border-gray-300"
                     rows={8}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-4 rounded-lg leading-relaxed min-h-[12rem] whitespace-pre-line">
                     {editableData.reportDetails || <span className="text-gray-500 italic">No report details provided</span>}
                  </div>
               )}
            </div>

            {/* IMAGES AND VIDEOS TABLE SECTION */}
            <SectionContainer
               label="IMAGES AND VIDEOS"
               onAdd={() => router.push(`/${params.role}/reports/${params.reportsId}/scene-information/media`)}
               addButtonText="Add Media"
            >
               <DataTable
                  columns={mediaColumns}
                  data={mediaFiles}
                  actions={(row, index) => (
                     <ActionButtons
                        row={row}
                        index={index}
                        onView={handleMediaView}
                        // No edit/delete for this view - just viewing
                     />
                  )}
               />
            </SectionContainer>

            {/* LEVEL ASSESSMENT SECTION */}
            <div className="mb-6 bg-white p-4">
               <div className="flex justify-between items-center mb-4">
                  <label className="font-semibold text-md">
                     LEVEL ASSESSMENT
                  </label>
                  <div className="flex items-center gap-3">
                     <span className="text-sm font-medium text-gray-700">Priority Level:</span>
                     {isEditing ? (
                        <select
                           value={editableData.levelAssessment.urgency}
                           onChange={(e) => handleInputChange('levelAssessment.urgency', e.target.value)}
                           className="rounded-lg border border-gray-300 px-3 py-1 text-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                        >
                           <option value="URGENT">URGENT</option>
                           <option value="NOT URGENT">NOT URGENT</option>
                        </select>
                     ) : (
                        <span className={`px-2 py-1 rounded-full text-xs font-medium ${
                           editableData.levelAssessment.urgency === "URGENT" 
                              ? "bg-red-100 text-red-800" 
                              : "bg-gray-100 text-gray-800"
                        }`}>
                           {editableData.levelAssessment.urgency}
                        </span>
                     )}
                  </div>
               </div>
               {isEditing ? (
                  <Textarea
                     value={editableData.levelAssessment.description}
                     onChange={(e) => handleInputChange('levelAssessment.description', e.target.value)}
                     placeholder="Enter assessment description..."
                     className="w-full rounded-lg border-gray-300"
                     rows={5}
                  />
               ) : (
                  <div className="text-sm text-gray-900 bg-gray-50 p-4 rounded-lg leading-relaxed min-h-[8rem] whitespace-pre-line">
                     {editableData.levelAssessment.description || <span className="text-gray-500 italic">No assessment description provided</span>}
                  </div>
               )}
            </div>

            {/* ACTION BUTTONS */}
            <div className="flex justify-between items-center bg-white p-4">
               {/* Back Button - Left Side */}
               <Button
                  variant="outline"
                  onClick={handleBack}
                  className="flex items-center gap-2 rounded-full"
               >
                  <ArrowLeft className="w-4 h-4" />
                  Back to Reports
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
                           Save Report
                        </Button>
                     </>
                  ) : (
                     <Button onClick={handleEdit} className="rounded-full">
                        Edit Report
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
            evidenceName="this field report"
         />
      </main>
   );
}