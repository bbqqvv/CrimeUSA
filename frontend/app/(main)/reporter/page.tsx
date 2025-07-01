'use client'

import Link from "next/link";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Separator } from "@radix-ui/react-dropdown-menu";
import { Inter } from "next/font/google";

const inter = Inter({ subsets: ["latin"] });

export default function ReporterPage1() {
    const navItems = [
        { name: "Home", active: true },
        { name: "About", active: false },
        { name: "Bureaus", active: false },
        { name: "Services", active: false },
        { name: "Stats", active: false },
        { name: "Policies", active: false },
    ];

    const steps = [
        { number: 1, label: "Step 1", active: true },
        { number: 2, label: "Step 2", active: false },
        { number: 3, label: "Step 3", active: false },
    ];

    const relationshipOptions = [
        { value: "victim", label: "Victim" },
        { value: "witness", label: "Witness" },
        { value: "offender", label: "Offender" },
        { value: "anonymous", label: "Anonymous" },
    ];
    return (
        <div>
            <section className="flex flex-col min-h-screen bg-white text-black">
            <main className="px-4 sm:px-6 lg:px-8">

                {/* Breadcrumb */}
                <div className="flex flex-wrap items-center gap-2 text-[#878787] text-sm sm:text-base mt-6 sm:mt-8 max-w-screen-xl mx-auto">
                    <Link href="/" className="hover:underline hover:text-black transition-colors">
                        Home
                    </Link>
                    <span>&gt;</span>
                    <span className="text-black font-medium">Report - Step 1</span>
                </div>

                {/* Page title */}
                <h1
                    className={`text-center font-bold text-[24px] sm:text-[32px] mt-6 sm:mt-10 ${inter.className}`}
                >
                    CRIME REPORT
                </h1>

                {/* Step progress */}
                <div className="flex justify-center mt-10 sm:mt-16">
                    <div className="relative flex items-center w-full max-w-2xl">
                        {steps.map((step, index) => (
                            <div key={step.number} className="flex-1 flex flex-col items-center relative">
                                {/* Line between steps */}
                                {index < steps.length - 1 && (
                                    <div className="absolute top-[31px] left-1/2 w-full h-[2px] bg-[#d0d0d0] z-0" />
                                )}

                                {/* Circle step */}
                                <div
                                    className={`relative z-10 w-12 h-12 sm:w-[62px] sm:h-[62px] rounded-full flex items-center justify-center ${step.active
                                        ? "bg-black text-white"
                                        : "bg-[#ebebeb] text-black border border-[#434343]"
                                        }`}
                                >
                                    <span className="text-lg sm:text-2xl">{step.number}</span>
                                </div>

                                {/* Label step */}
                                <span className="font-semibold text-sm sm:text-xl mt-2 sm:mt-[10px]">{step.label}</span>
                            </div>
                        ))}
                    </div>
                </div>

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

                        {/* Next button */}
                        <div className="flex justify-end mt-16">
                            <Button className="w-40 h-[50px] bg-[#434343] rounded-lg text-[#f7f7f7] font-semibold">
                                Next
                            </Button>
                        </div>
                    </div>
                </div>
            </main>

        </section>
        </div>
        

    );
}