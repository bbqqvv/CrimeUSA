'use client'
import { ReporterInfoForm } from "@/components/forms/reporter-info-form";
import { Button } from "@/components/ui/button";
import { ProgressSteps } from "@/components/ui/progress-steps";
import Image from "next/image";
import { ReportTable } from "@/components/report-table";

export default async function ReporterPage() {
  var currentStep = 3;

  return (
    <div>
      <div className="container mx-auto px-4 py-4">
        <div className="text-sm text-gray-500">
          Home {">"} Report {">"} Step {currentStep}
        </div>
      </div>
      <main className="container mx-auto px-4 pb-8">

        <h1 className="text-3xl font-bold text-center mb-8">CRIME REPORT</h1>
        <div className="mx-auto w-full ">
          <div className="block">
            <ProgressSteps currentStep={currentStep} totalSteps={3} />
          </div>
        </div>

        <div className="flex flex-col justify-center items-center">
          <Image
            className="mt-10 mb-10 ml-10"
            src="/images/image 11.svg"
            alt="image"
            width={160}
            height={160}
          />
          <div className="w-100 h-10 text-center mb-30">
            <p className="text-wrap">
              Your report will be reviewed within 5–10 working days.
              Please check the status regularly for updates.
              Thank you for your submission.
            </p>
          </div>
          <div className="mb-50">
            <ReportTable></ReportTable>
          </div>
        </div>
      </main>
    </div>
  );
}
