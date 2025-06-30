"use client"

import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"
import { Label } from "@/components/ui/label"
import { FormField } from "@/components/ui/form-field"
import { RELATIONSHIP_OPTIONS } from "@/constants/form-options"
import type { ReporterInfo } from "@/types/form"

interface ReporterInfoFormProps {
  data: ReporterInfo
  onChange: (field: keyof ReporterInfo, value: string) => void
}

export function ReporterInfoForm({ data, onChange }: ReporterInfoFormProps) {
  return (
    <div className="bg-white p-8 rounded-lg shadow-sm">
      <h2 className="text-xl font-semibold mb-6 text-center border-b pb-4">Reporter Information</h2>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <FormField
          label="Full name"
          id="fullName"
          value={data.fullName}
          onChange={(value) => onChange("fullName", value)}
          required
        />

        <FormField
          label="Email"
          id="email"
          type="email"
          value={data.email}
          onChange={(value) => onChange("email", value)}
          required
        />

        <FormField
          label="Phone number"
          id="phoneNumber"
          value={data.phoneNumber}
          onChange={(value) => onChange("phoneNumber", value)}
          required
        />

        <FormField label="Address" id="address" value={data.address} onChange={(value) => onChange("address", value)} />
      </div>

      <div className="mt-6">
        <Label className="text-sm font-medium">
          Relationship to the incident <span className="text-red-500">*</span>
        </Label>
        <RadioGroup
          value={data.relationship}
          onValueChange={(value) => onChange("relationship", value)}
          className="mt-3 space-y-3"
        >
          {RELATIONSHIP_OPTIONS.map((option) => (
            <div key={option.value} className="flex items-center space-x-2">
              <RadioGroupItem value={option.value} id={option.value} />
              <Label htmlFor={option.value}>{option.label}</Label>
            </div>
          ))}
        </RadioGroup>
      </div>
    </div>
  )
}
