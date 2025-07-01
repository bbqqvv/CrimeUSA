import { Button } from "@/components/ui/button";

export default function InvestigationReportForm() {
  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h1 className="text-2xl font-bold text-center mb-6">INITIAL INVESTIGATION REPORT</h1>
      </div>

      {/* Placeholder Content */}
      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Case Summary</h2>
        <p className="text-gray-600">Summary of the initial investigation findings and preliminary conclusions.</p>
      </div>

      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Evidence Analysis</h2>
        <p className="text-gray-600">Initial analysis of collected evidence and forensic findings.</p>
      </div>

      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Recommendations</h2>
        <p className="text-gray-600">Recommended next steps for the investigation process.</p>
      </div>

      {/* Action Buttons */}
      <div className="flex justify-end gap-4 pt-6">
        <Button variant="outline" size="lg">
          Cancel
        </Button>
        <Button size="lg">
          Save
        </Button>
      </div>
    </div>
  );
} 