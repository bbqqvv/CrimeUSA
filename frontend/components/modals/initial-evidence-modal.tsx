"use client"

import { useState } from "react"
import { Modal } from "@/components/ui/modal"
import { FormField } from "@/components/ui/form-field"
import { Label } from "@/components/ui/label"
import { Textarea } from "@/components/ui/textarea"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { FileUpload } from "@/components/ui/file-upload"

interface InitialEvidenceModalProps {
  isOpen: boolean
  onClose: () => void
  onSubmit: (data: any) => void
}

const EVIDENCE_TYPES = [
  { value: "documentary", label: "Documentary Evidence" },
  { value: "physical", label: "Physical Evidence" },
  { value: "digital", label: "Digital Evidence" },
  { value: "testimonial", label: "Testimonial Evidence" },
  { value: "photographic", label: "Photographic Evidence" },
]

export function InitialEvidenceModal({ isOpen, onClose, onSubmit }: InitialEvidenceModalProps) {
  const [formData, setFormData] = useState({
    evidenceType: "",
    location: "",
    description: "",
  })

  const handleInputChange = (field: string, value: string) => {
    setFormData((prev) => ({ ...prev, [field]: value }))
  }

  const handleSubmit = () => {
    onSubmit(formData)
    setFormData({
      evidenceType: "",
      location: "",
      description: "",
    })
    onClose()
  }

  const handleCancel = () => {
    setFormData({
      evidenceType: "",
      location: "",
      description: "",
    })
    onClose()
  }

  return (
    <Modal
      isOpen={isOpen}
      onClose={onClose}
      title="Initial Evidence"
      description="This form is used to document the initial evidence connected to the incident."
      onCancel={handleCancel}
      onSubmit={handleSubmit}
    >
      <div className="space-y-6">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <Label className="text-sm font-medium">
              Types of Evidence <span className="text-red-500">*</span>
            </Label>
            <Select value={formData.evidenceType} onValueChange={(value) => handleInputChange("evidenceType", value)}>
              <SelectTrigger className="mt-1">
                <SelectValue placeholder="Select an option" />
              </SelectTrigger>
              <SelectContent>
                {EVIDENCE_TYPES.map((type) => (
                  <SelectItem key={type.value} value={type.value}>
                    {type.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <FormField
            label="Evidence Location"
            id="location"
            value={formData.location}
            onChange={(value) => handleInputChange("location", value)}
            placeholder="E.g., At the scene, in the car,..."
          />
        </div>

        <div>
          <Label className="text-sm font-medium">Evidence Description</Label>
          <Textarea
            value={formData.description}
            onChange={(e) => handleInputChange("description", e.target.value)}
            placeholder="Provide a clear and detailed description of the evidence (shape, material, identifying features...)"
            className="mt-1 min-h-[120px]"
          />
        </div>

        <div>
          <Label className="text-sm font-medium mb-4 block">Attachments</Label>
          <FileUpload />
        </div>
      </div>
    </Modal>
  )
}
