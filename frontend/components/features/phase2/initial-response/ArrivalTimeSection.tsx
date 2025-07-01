import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

export default function ArrivalTimeSection() {
  return (
    <div className="bg-white rounded-lg p-6 shadow-sm border border-gray-200">
      <label className="block text-sm font-semibold text-gray-800 uppercase tracking-wide mb-4">
        TIME OF ARRIVAL AT THE SCENE
      </label>
      <div className="flex items-center gap-4">
        <div className="flex items-center gap-1">
          <Input 
            placeholder="09" 
            className="w-16 text-center bg-gray-50 border-gray-200 font-mono text-lg"
            maxLength={2}
          />
          <span className="text-gray-500 text-lg font-bold">:</span>
          <Input 
            placeholder="32" 
            className="w-16 text-center bg-gray-50 border-gray-200 font-mono text-lg"
            maxLength={2}
          />
        </div>
        <div className="flex gap-2">
          <Button variant="outline" size="sm" className="bg-blue-50 border-blue-200 text-blue-700">AM</Button>
          <Button variant="outline" size="sm" className="bg-gray-50 border-gray-200">PM</Button>
        </div>
      </div>
    </div>
  );
} 