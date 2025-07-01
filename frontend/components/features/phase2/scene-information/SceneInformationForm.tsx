import { Button } from "@/components/ui/button";

export default function SceneInformationForm() {
  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h1 className="text-2xl font-bold text-center mb-6">SCENE INFORMATION</h1>
      </div>

      {/* Placeholder Content */}
      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Initial Statements</h2>
        <p className="text-gray-600">This section contains initial statements and witness testimonies.</p>
      </div>

      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Scene Description</h2>
        <p className="text-gray-600">Detailed description of the crime scene layout and conditions.</p>
      </div>

      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Images and Videos</h2>
        <p className="text-gray-600">Photographic and video documentation of the scene.</p>
      </div>

      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Preliminary Physical Evidence Information</h2>
        <p className="text-gray-600">Initial catalog of physical evidence found at the scene.</p>
      </div>

      <div className="bg-white rounded-lg p-6 shadow-sm border">
        <h2 className="text-lg font-semibold mb-4">Scene Sketch</h2>
        <p className="text-gray-600">Architectural and spatial documentation of the scene.</p>
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