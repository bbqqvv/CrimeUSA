import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

export default function DispatchTimeSection() {
  return (
    <div className="bg-white rounded-lg p-6 shadow-sm border border-gray-200">
      <div className="flex items-center justify-between mb-4">
        <label className="text-sm font-semibold text-gray-800 uppercase tracking-wide">
          TIME OF DISPATCHING FORCES TO THE SCENE
        </label>
        <Button variant="outline" size="sm" className="gap-2">
          Choose 📁
        </Button>
      </div>
      <Input 
        placeholder="Enter dispatch time..." 
        className="w-full bg-gray-50 border-gray-200"
      />
    </div>
  );
} 