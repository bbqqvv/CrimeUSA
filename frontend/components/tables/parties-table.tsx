"use client"

import { useState } from "react"
import { Edit, Trash2, Plus } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { RelevantPartiesModal } from "@/components/modals/relevant-parties-modal"

export function PartiesTable() {
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [parties, setParties] = useState([
    { id: "#1", role: "Witness", name: "—", statement: "—", attachments: "File Title.png" },
  ])

  const handleAddParty = (data: any) => {
    const newParty = {
      id: `#${parties.length + 1}`,
      role: data.relationship,
      name: data.fullName || "—",
      statement: data.statement || "—",
      attachments: "File Title.png",
    }
    setParties([...parties, newParty])
  }

  return (
    <div className="mb-8">
      <h3 className="text-lg font-semibold mb-4 text-center border-b pb-2">Relevant Parties</h3>
      <Table>
        <TableHeader>
          <TableRow className="bg-gray-100">
            <TableHead>ID</TableHead>
            <TableHead>Relevant Role</TableHead>
            <TableHead>Name</TableHead>
            <TableHead>Statement</TableHead>
            <TableHead>Attachments</TableHead>
            <TableHead>Action</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {parties.map((party) => (
            <TableRow key={party.id}>
              <TableCell>{party.id}</TableCell>
              <TableCell>{party.role}</TableCell>
              <TableCell>{party.name}</TableCell>
              <TableCell>{party.statement}</TableCell>
              <TableCell>{party.attachments}</TableCell>
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

      <RelevantPartiesModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} onSubmit={handleAddParty} />
    </div>
  )
}
