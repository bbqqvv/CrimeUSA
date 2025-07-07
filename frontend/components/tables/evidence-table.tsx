"use client"

import { useState } from "react"
import { Edit, Trash2, Plus } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { InitialEvidenceModal } from "@/components/modals/initial-evidence-modal"

export function EvidenceTable() {
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [evidence, setEvidence] = useState([
    { id: "#1", type: "Documentary Evidence", location: "—", description: "—", attachments: "File Title.png" },
  ])

  const handleAddEvidence = (data: any) => {
    const newEvidence = {
      id: `#${evidence.length + 1}`,
      type: data.evidenceType,
      location: data.location || "—",
      description: data.description || "—",
      attachments: "File Title.png",
    }
    setEvidence([...evidence, newEvidence])
  }

  return (
    <div className="mb-8">
      <h3 className="text-lg font-semibold mb-4 text-center border-b pb-2">Initial Evidence</h3>
      <Table>
        <TableHeader>
          <TableRow className="bg-gray-100">
            <TableHead>ID</TableHead>
            <TableHead>Types of Evidence</TableHead>
            <TableHead>Location</TableHead>
            <TableHead>Description</TableHead>
            <TableHead>Attachments</TableHead>
            <TableHead>Action</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {evidence.map((item) => (
            <TableRow key={item.id}>
              <TableCell>{item.id}</TableCell>
              <TableCell>{item.type}</TableCell>
              <TableCell>{item.location}</TableCell>
              <TableCell>{item.description}</TableCell>
              <TableCell>{item.attachments}</TableCell>
              <TableCell>
                <div className="flex space-x-2">
                  <Edit className="w-4 h-4 text-blue-600 cursor-pointer" />
                  <Trash2 className="w-4 h-4 text-red-600 cursor-pointer" />
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <div className="flex justify-end mt-4">
        <Button variant="outline" size="sm" onClick={() => setIsModalOpen(true)}>
          <Plus className="w-4 h-4 mr-2" />
          ADD
        </Button>
      </div>

      <InitialEvidenceModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} onSubmit={handleAddEvidence} />
    </div>
  )
}
