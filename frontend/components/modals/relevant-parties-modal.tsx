"use client"

import { useState } from "react"
import { Modal } from "@/components/ui/modal"
import { FormField } from "@/components/ui/form-field"
import { Label } from "@/components/ui/label"
import { Textarea } from "@/components/ui/textarea"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { FileUpload } from "@/components/ui/file-upload"

interface RelevantPartiesModalProps {
  isOpen: boolean
  onClose: () => void
  onSubmit: (data: any) => void
}

const RELATIONSHIP_OPTIONS = [
  { value: "victim", label: "Victim" },
  { value: "witness", label: "Witness" },
  { value: "suspect", label: "Suspect" },
  { value: "complainant", label: "Complainant" },
]

const GENDER_OPTIONS = [
  { value: "male", label: "Male" },
  { value: "female", label: "Female" },
  { value: "other", label: "Other" },
  { value: "prefer-not-to-say", label: "Prefer not to say" },
]

export function RelevantPartiesModal({ isOpen, onClose, onSubmit }: RelevantPartiesModalProps) {
  const [formData, setFormData] = useState({
    fullName: "",
    relationship: "",
    gender: "",
    nationality: "",
    statement: "",
  })

  const handleInputChange = (field: string, value: string) => {
    setFormData((prev) => ({ ...prev, [field]: value }))
  }

  const handleSubmit = () => {
    onSubmit(formData)
    setFormData({
      fullName: "",
      relationship: "",
      gender: "",
      nationality: "",
      statement: "",
    })
    onClose()
  }

  const handleCancel = () => {
    setFormData({
      fullName: "",
      relationship: "",
      gender: "",
      nationality: "",
      statement: "",
    })
    onClose()
  }

  return (
    <Modal
      isOpen={isOpen}
      onClose={onClose}
      title="Relevant Parties"
      description="This form is used to document the roles and identities of all parties connected to the incident."
      onCancel={handleCancel}
      onSubmit={handleSubmit}
    >
      <div className="space-y-6">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <FormField
            label="Full name"
            id="fullName"
            value={formData.fullName}
            onChange={(value) => handleInputChange("fullName", value)}
            placeholder="E.g., John Michael Doe"
          />

          <div>
            <Label className="text-sm font-medium">
              Relationship to the incident <span className="text-red-500">*</span>
            </Label>
            <Select value={formData.relationship} onValueChange={(value) => handleInputChange("relationship", value)}>
              <SelectTrigger className="mt-1">
                <SelectValue placeholder="Select an option" />
              </SelectTrigger>
              <SelectContent>
                {RELATIONSHIP_OPTIONS.map((option) => (
                  <SelectItem key={option.value} value={option.value}>
                    {option.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div>
            <Label className="text-sm font-medium">Gender</Label>
            <Select value={formData.gender} onValueChange={(value) => handleInputChange("gender", value)}>
              <SelectTrigger className="mt-1">
                <SelectValue placeholder="Select an option" />
              </SelectTrigger>
              <SelectContent>
                {GENDER_OPTIONS.map((option) => (
                  <SelectItem key={option.value} value={option.value}>
                    {option.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <FormField
            label="Nationality"
            id="nationality"
            value={formData.nationality}
            onChange={(value) => handleInputChange("nationality", value)}
            placeholder="E.g., American"
          />
        </div>

        <div>
          <Label className="text-sm font-medium">Statement / Description</Label>
          <Textarea
            value={formData.statement}
            onChange={(e) => handleInputChange("statement", e.target.value)}
            placeholder="Provide a clear and detailed description of what happened, including dates, times, locations, and people involved."
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
