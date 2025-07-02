"use client";

import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { Calendar } from "lucide-react";
import { SectionContainer } from "@/components/(phase2)/SectionContainer";
import { DataTable } from "@/components/(phase2)/DataTable";
import { ActionButtons } from "@/components/(phase2)/ActionButtons";
import { DeleteEvidenceModal } from "../DeleteEvidenceModal";

/**
 * PRELIMINARY INVESTIGATION PAGE
 *
 * This is the main page component for the Preliminary Investigation section of the police report system.
 * It displays various forms and tables for collecting investigation data including:
 * - Location of investigation
 * - Start and end times
 * - List of officers assigned
 * - Actions taken
 * - Collected evidence
 * - Initial statement summary
 * - Preliminary conclusion
 * - Reporter information
 *
 * The page uses reusable components (SectionContainer, DataTable, ActionButtons)
 * to maintain consistency and reduce code duplication.
 */

// Initial mock data for different sections
const officersInit = [
   { name: "Brandie", role: "Patrol Officer", phone: "(225) 555-0118" },
   { name: "Brandie", role: "Patrol Officer", phone: "(225) 555-0118" },
   { name: "Brandie", role: "Detective", phone: "(225) 555-0118" },
];

const actionsInit = [
   "Immediate perimeter established using police tape (approx. 30-meter radius)",
   "Vehicle stabilized to prevent further movement.",
   "Photographic documentation of the scene commenced at 22:26.",
];

const evidenceInit = [
   { name: "Knife", description: "Bloody Knife" },
   { name: "Footprints", description: "Bloody Footprints" },
   { name: "Ex", description: "Ex" },
];

export default function Page() {
   // State management for all data sections
   const [officers, setOfficers] = useState(officersInit);
   const [actions, setActions] = useState(actionsInit);
   const [evidence, setEvidence] = useState(evidenceInit);
   
   // State for form fields
   const [location, setLocation] = useState("");
   const [startTime, setStartTime] = useState("");
   const [endTime, setEndTime] = useState("");
   const [reporter, setReporter] = useState("");
   const [statementSummary, setStatementSummary] = useState("Based on initial witness interviews, the incident occurred between 2:15 PM and 2:30 PM on the specified date. Multiple witnesses reported hearing raised voices from the kitchen area, followed by sounds of a struggle and breaking glass. The primary witness, Ms. Johnson from apartment 2B, observed an individual matching the suspect's description leaving through the back entrance at approximately 2:28 PM. Initial statements indicate the victim was alone in the residence at the time of the incident, with no signs of forced entry through the main entrance.");
   const [preliminaryConclusion, setPreliminaryConclusion] = useState("Preliminary evidence suggests this was a targeted incident rather than a random crime. The lack of forced entry indicates the victim likely knew the perpetrator or the perpetrator had access to the residence. Physical evidence collection is ongoing, with priority given to DNA analysis of blood samples and fingerprint processing of the weapon. The crime scene shows signs of a brief but violent altercation concentrated in the kitchen area. Further investigation is required to establish motive and confirm suspect identification through forensic analysis and additional witness interviews.");

   // State for controlling the delete modal
   const [showDeleteModal, setShowDeleteModal] = useState(false);
   const [evidenceToDelete, setEvidenceToDelete] = useState<any>(null);

   /**
    * COLUMN CONFIGURATIONS
    */
   const officerColumns = [
      { key: "name", label: "Full Name" },
      { key: "role", label: "Role" },
      { key: "phone", label: "Phone Number" },
   ];

   const actionsColumns = [
      {
         key: "number",
         label: "#",
         render: (_: any, __: any, index: number) => index + 1,
      },
      { key: "measure", label: "Actions Taken" },
   ];

   const evidenceColumns = [
      {
         key: "number",
         label: "#",
         render: (_: any, __: any, index: number) => index + 1,
      },
      { key: "name", label: "Evidence Name" },
      { key: "description", label: "Description" },
   ];

   /**
    * EVENT HANDLERS
    */

   // Add handlers
   const handleAddOfficer = () => {
      console.log("Add officer");
   };

   const handleAddAction = () => {
      console.log("Add action");
   };

   const handleAddEvidence = () => {
      console.log("Add evidence");
   };

   // Edit handlers
   const handleEditOfficer = (officer: any, index: number) => {
      console.log("Edit officer:", officer, index);
   };

   const handleEditAction = (action: any, index: number) => {
      console.log("Edit action:", action, index);
   };

   const handleEditEvidence = (evidence: any, index: number) => {
      console.log("Edit evidence:", evidence, index);
   };

   // Delete handlers - updated to show confirmation modal
   const handleDeleteOfficer = (officer: any, index: number) => {
      setEvidenceToDelete({ item: officer, index, type: 'officer' });
      setShowDeleteModal(true);
   };

   const handleDeleteAction = (action: any, index: number) => {
      setEvidenceToDelete({ item: action, index, type: 'action' });
      setShowDeleteModal(true);
   };

   const handleDeleteEvidence = (evidence: any, index: number) => {
      setEvidenceToDelete({ item: evidence, index, type: 'evidence' });
      setShowDeleteModal(true);
   };

   // Confirm deletion handler
   const handleConfirmDelete = () => {
      if (!evidenceToDelete) return;

      const { item, index, type } = evidenceToDelete;
      
      switch (type) {
         case 'officer':
            setOfficers(prev => prev.filter((_, i) => i !== index));
            console.log("Officer deleted:", item, index);
            break;
         case 'action':
            setActions(prev => prev.filter((_, i) => i !== index));
            console.log("Action deleted:", item, index);
            break;
         case 'evidence':
            setEvidence(prev => prev.filter((_, i) => i !== index));
            console.log("Evidence deleted:", item, index);
            break;
      }

      setEvidenceToDelete(null);
      setShowDeleteModal(false);
   };

   // Close modal handler
   const handleCloseModal = () => {
      setShowDeleteModal(false);
      setEvidenceToDelete(null);
   };

   /**
    * DATA TRANSFORMATION
    */
   const actionsData = actions.map((text) => ({ measure: text }));

   return (
      <main className="flex-1 p-6">
         {/* Page Header */}
         <h1 className="text-3xl font-bold text-center bg-blue-100 text-blue-900 px-4 py-2 rounded-t-lg shadow">
            INITIAL INVESTIGATION REPORT
         </h1>

         {/* Main Content Container with gray background */}
         <div className="bg-gray-300 rounded-b-lg shadow p-6 pt-10 space-y-6">

            {/* LOCATION OF INVESTIGATION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  LOCATION OF INVESTIGATION
               </label>
               <Input
                  value={location}
                  onChange={(e) => setLocation(e.target.value)}
                  placeholder="Enter investigation location..."
                  className="w-full rounded-lg"
               />
            </div>

            {/* START TIME AND END TIME SECTION */}
            <div className="bg-white rounded-lg p-6">
               <div className="grid grid-cols-2 gap-6">
                  <div>
                     <label className="block font-semibold text-lg mb-4">
                        START TIME
                     </label>
                     <Input
                        value={startTime}
                        onChange={(e) => setStartTime(e.target.value)}
                        placeholder="Enter start time..."
                        className="w-full rounded-lg"
                     />
                  </div>
                  <div>
                     <label className="block font-semibold text-lg mb-4">
                        END TIME
                     </label>
                     <Input
                        value={endTime}
                        onChange={(e) => setEndTime(e.target.value)}
                        placeholder="Enter end time..."
                        className="w-full rounded-lg"
                     />
                  </div>
               </div>
            </div>

            {/* LIST OF OFFICERS ASSIGNED SECTION */}
            <SectionContainer
               label="LIST OF OFFICERS ASSIGNED"
               onAdd={handleAddOfficer}
            >
               <DataTable
                  columns={officerColumns}
                  data={officers}
                  actions={(row, index) => (
                     <ActionButtons
                        row={row}
                        index={index}
                        onEdit={handleEditOfficer}
                        onDelete={handleDeleteOfficer}
                     />
                  )}
               />
            </SectionContainer>

            {/* ACTIONS TAKEN SECTION */}
            <SectionContainer
               label="ACTIONS TAKEN"
               onAdd={handleAddAction}
            >
               <DataTable
                  columns={actionsColumns}
                  data={actionsData}
                  actions={(row, index) => (
                     <ActionButtons
                        row={row}
                        index={index}
                        onEdit={handleEditAction}
                        onDelete={handleDeleteAction}
                     />
                  )}
               />
            </SectionContainer>

            {/* COLLECTED EVIDENCE SECTION */}
            <SectionContainer
               label="COLLECTED EVIDENCE"
               onAdd={handleAddEvidence}
            >
               <DataTable
                  columns={evidenceColumns}
                  data={evidence}
                  actions={(row, index) => (
                     <ActionButtons
                        row={row}
                        index={index}
                        onEdit={handleEditEvidence}
                        onDelete={handleDeleteEvidence}
                     />
                  )}
               />
            </SectionContainer>

            {/* INITIAL STATEMENT SUMMARY SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  INITIAL STATEMENT SUMMARY
               </label>
               <Textarea
                  value={statementSummary}
                  onChange={(e) => setStatementSummary(e.target.value)}
                  className="w-full rounded-lg border-gray-300"
                  rows={6}
                  placeholder="Enter initial statement summary..."
               />
            </div>

            {/* PRELIMINARY CONCLUSION SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  PRELIMINARY CONCLUSION
               </label>
               <Textarea
                  value={preliminaryConclusion}
                  onChange={(e) => setPreliminaryConclusion(e.target.value)}
                  className="w-full rounded-lg border-gray-300"
                  rows={6}
                  placeholder="Enter preliminary conclusion..."
               />
            </div>

            {/* REPORTER SECTION */}
            <div className="bg-white rounded-lg p-6">
               <label className="block font-semibold text-lg mb-4">
                  REPORTER
               </label>
               <Input
                  value={reporter}
                  onChange={(e) => setReporter(e.target.value)}
                  placeholder="Enter reporter name..."
                  className="w-full rounded-lg"
               />
            </div>

            {/* PAGE ACTION BUTTONS */}
            <div className="flex justify-end gap-4 bg-white p-4 rounded-lg">
               <Button variant="outline" className="rounded-full">
                  Cancel
               </Button>
               <Button className="rounded-full">Print</Button>
               <Button className="rounded-full">Submit</Button>
            </div>
         </div>

         {/* DELETE CONFIRMATION MODAL */}
         <DeleteEvidenceModal
            isOpen={showDeleteModal}
            onClose={handleCloseModal}
            onConfirm={handleConfirmDelete}
            evidenceName={
               evidenceToDelete?.item?.name ||        // For officers/evidence (name)
               evidenceToDelete?.item?.measure ||     // For actions (measure)
               "this item"                            // Fallback
            }
         />
      </main>
   );
}