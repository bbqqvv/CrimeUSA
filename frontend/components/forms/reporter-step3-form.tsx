
import { Button } from "@/components/ui/button";
import { ProgressSteps } from "@/components/ui/progress-steps";
import Image from "next/image";
import { ReviewReportTable } from "@/components/reivew-report-table";

export function ReporterStep3Page() {
  var currentStep = 3;

  return (
    <div>
      <main className="container mx-auto px-4 pb-8">
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
            <ReviewReportTable></ReviewReportTable>
          </div>
        </div>
      </main>
    </div>
  );
}
