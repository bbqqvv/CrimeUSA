"use client"

import { Calendar } from "lucide-react"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { EvidenceTable } from "@/components/tables/evidence-table"
import { PartiesTable } from "@/components/tables/parties-table"
import { CRIME_TYPES, SEVERITY_LEVELS } from "@/constants/form-options"
import type { IncidentInfo } from "@/types/form"

interface IncidentInfoFormProps {
  data: IncidentInfo
  onChange: (field: keyof IncidentInfo, value: string) => void
}

export function IncidentInfoForm({ data, onChange }: IncidentInfoFormProps) {
  return (
    <div className="bg-white p-8 rounded-lg shadow-sm">
      <h2 className="text-xl font-semibold mb-6 text-center border-b pb-4">Incident Information</h2>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
        <div>
          <Label className="text-sm font-medium">
            Type of crime <span className="text-red-500">*</span>
          </Label>
          <Select value={data.crimeType} onValueChange={(value: string) => onChange("crimeType", value)}>
            <SelectTrigger className="mt-1 bg-gray-100">
              <SelectValue placeholder="Select an option" />
            </SelectTrigger>
            <SelectContent>
              {CRIME_TYPES.map((type) => (
                <SelectItem key={type.value} value={type.value}>
                  {type.label}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>

        <div>
          <Label className="text-sm font-medium">
            Severity <span className="text-red-500">*</span>
          </Label>
          <Select value={data.severity} onValueChange={(value: string) => onChange("severity", value)}>
            <SelectTrigger className="mt-1 bg-gray-100">
              <SelectValue placeholder="Select an option" />
            </SelectTrigger>
            <SelectContent>
              {SEVERITY_LEVELS.map((level) => (
                <SelectItem key={level.value} value={level.value}>
                  {level.label}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
      </div>

      <div className="mb-6">
        <Label className="text-sm font-medium">
          Datetime of occurrence <span className="text-red-500">*</span>
        </Label>
        <div className="mt-1 relative">
          <Input
            value={data.datetime}
            onChange={(e) => onChange("datetime", e.target.value)}
            placeholder="Choose"
            className="bg-gray-100 pr-10"
          />
          <Calendar className="absolute right-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-500" />
        </div>
      </div>

      <div className="mb-6">
        <Label className="text-sm font-medium">Detailed address</Label>
        <Input
          value={data.detailedAddress}
          onChange={(e) => onChange("detailedAddress", e.target.value)}
          className="mt-1 bg-gray-100"
        />
      </div>

      <div className="mb-8">
        <Label className="text-sm font-medium">Description of the incident</Label>
        <Textarea
          value={data.description}
          onChange={(e) => onChange("description", e.target.value)}
          placeholder="Briefly describe what happened, including key facts such as time, location, and main events."
          className="mt-1 bg-gray-100 min-h-[100px]"
        />
      </div>

      <PartiesTable />
      <EvidenceTable />
    </div>
  )
}
