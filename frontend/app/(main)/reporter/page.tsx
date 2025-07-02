"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Header } from "@/components/layout/header"
import { Footer } from "@/components/layout/footer"
import { ProgressSteps } from "@/components/ui/progress-steps"
import { ReporterStep1Page } from "@/components/forms/reporter-step1-form"
import { ReporterStep2Page } from "@/components/forms/reporter-step2-form"
import { useFormData } from "@/hooks/use-form-data"
import { ReporterStep3Page } from "@/components/forms/reporter-step3-form"

export default function ReportPage() {
  const [currentStep, setCurrentStep] = useState(1)
  const { formData, updateField } = useFormData()

  const handleNext = () => setCurrentStep((prev) => Math.min(prev + 1, 3))
  const handleBack = () => setCurrentStep((prev) => Math.max(prev - 1, 1))
  const handleSubmit = () => {
    setCurrentStep((prev) => Math.min(prev + 1, 3))
    console.log("Form submitted:", formData)
  }

  return (
    <div>
      <div className="container mx-auto px-4 py-4">
        <div className="text-sm text-gray-500">
          Home {">"} Report {">"} Step {currentStep}
        </div>
      </div>
      <main className="container mx-auto px-4 pb-8">
        <h1 className="text-3xl font-bold text-center mb-8">CRIME REPORT</h1>

        <ProgressSteps currentStep={currentStep} totalSteps={3} />

        <div className="max-w-4xl mx-auto">
          {currentStep === 1 && (
            <>
            {/* {Sample step1 form} */}
              <ReporterStep1Page data={formData} onChange={updateField} />
              <div className="flex justify-end mt-8">
                <Button onClick={handleNext} className="bg-gray-600 hover:bg-gray-700">
                  Next
                </Button>
              </div>
            </>
          )}

          {currentStep === 2 && (
            <>
            {/* {Sample step2 form} */}
              <ReporterStep2Page data={formData} onChange={updateField} />
              <div className="flex justify-between mt-8">
                <Button onClick={handleBack} variant="outline" className="bg-gray-400 hover:bg-gray-500 text-white">
                  Back
                </Button>
                <Button onClick={handleSubmit} className="bg-gray-600 hover:bg-gray-700">
                  Submit
                </Button>
              </div>
            </>
          )}
          {currentStep === 3 && (
            <>
              <ReporterStep3Page></ReporterStep3Page>
              <div className="flex justify-between mt-8">
              </div>
            </>
          )}
        </div>
      </main>
    </div>
  );
}