"use client"

import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"
import { Label } from "@/components/ui/label"
import { FormField } from "@/components/ui/form-field"
import { relationshipOptions } from "@/constants/form-options"
import type { ReporterInfo } from "@/types/form"
import { Link } from "lucide-react"
import { Separator } from "@radix-ui/react-menu"
import { Input } from "../ui/input"
import { Button } from "../ui/button"

interface ReporterStep1PageProps {
    data: ReporterInfo
    onChange: (field: keyof ReporterInfo, value: string) => void
}

export function ReporterStep1Page({ data, onChange }: ReporterStep1PageProps) {
    return (
        <div>
            <section className="flex flex-col min-h-screen bg-white text-black">
                <main className="px-4 sm:px-6 lg:px-8">
                    {/* Reporter Information */}
                    <div className="flex justify-center mt-16 px-2">
                        <div className="w-full max-w-screen-md">
                            <div className="flex items-center">
                                <Separator className="flex-1" />
                                <h2 className="mx-4 font-semibold text-lg sm:text-2xl">Reporter Information</h2>
                                <Separator className="flex-1" />
                            </div>

                            {/* Form fields */}
                            <div className="mt-10 grid grid-cols-1 md:grid-cols-2 gap-x-10 gap-y-6">
                                {[
                                    { id: "fullName", label: "Full name", required: true },
                                    { id: "email", label: "Email", required: true },
                                    { id: "phoneNumber", label: "Phone number", required: true },
                                    { id: "address", label: "Address", required: false },
                                ].map((field) => (
                                    <div key={field.id} className="space-y-2">
                                        <Label className="font-medium text-base sm:text-lg" htmlFor={field.id}>
                                            {field.label} {field.required && <span className="text-[#ff0000]">*</span>}
                                        </Label>
                                        <Input id={field.id} className="h-[50px] bg-[#eeeeee] rounded-lg" />
                                    </div>
                                ))}
                            </div>

                            {/* Relationship */}
                            <div className="mt-8">
                                <Label className="font-medium text-base sm:text-xl">
                                    Relationship to the incident <span className="text-[#ff0000]">*</span>
                                </Label>
                                <RadioGroup className="mt-6 space-y-4">
                                    {relationshipOptions.map((option) => (
                                        <div key={option.value} className="flex items-center">
                                            <div className="ml-[20px] mr-[20px]">
                                                <RadioGroupItem
                                                    value={option.value}
                                                    id={option.value}
                                                    className="w-5 h-5 border-2 border-[#808080]"
                                                />
                                            </div>
                                            <Label htmlFor={option.value} className="font-medium text-base sm:text-lg">
                                                {option.label}
                                            </Label>
                                        </div>
                                    ))}
                                </RadioGroup>
                            </div>
                        </div>
                    </div>
                </main>

            </section>
        </div>
    )
}
