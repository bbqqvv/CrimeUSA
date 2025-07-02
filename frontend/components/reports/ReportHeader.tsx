import { Badge } from "@/components/ui/badge";
import { Calendar, Clock } from "lucide-react";

export function ReportHeader() {
  return (
    <div className="flex justify-between items-start border-b-2 border-gray-300 pb-6">
      <div className="space-y-2">
        <div className="flex items-center gap-2 text-sm text-gray-600">
          <span>Report ID: 11111</span>
        </div>
        <div className="flex items-center gap-2">
          <span className="text-sm text-gray-600">Status:</span>
          <Badge className="bg-orange-100 text-orange-800 border-orange-200 hover:bg-orange-100">
            Pending
          </Badge>
        </div>
      </div>
      <div className="text-right space-y-2">
        <div className="flex items-center gap-2 text-sm text-gray-600">
          <Calendar className="w-4 h-4" />
          <span>Date: 05/22/2024</span>
        </div>
        <div className="flex items-center gap-2 text-sm text-gray-600">
          <Clock className="w-4 h-4" />
          <span>Time: 18:22</span>
        </div>
      </div>
    </div>
  );
}
