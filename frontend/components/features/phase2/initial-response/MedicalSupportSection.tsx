import { Button } from "@/components/ui/button";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

const medicalSupport = [
  { id: "EMS45", type: "Medical Emergency", time: "08:00 PM" },
  { id: "RES-Q12", type: "Patrol Officer", time: "08:00 PM" },
  { id: "RES-Q12", type: "Detective", time: "08:00 PM" },
];

export default function MedicalSupportSection() {
  return (
    <div className="bg-white rounded-lg p-6 shadow-sm border">
      <div className="flex items-center justify-between mb-4">
        <label className="text-sm font-medium text-gray-700">
          INFORMATION ON MEDICAL/RESCUE SUPPORT PROVIDED
        </label>
        <Button variant="outline" size="sm">
          ADD ⊕
        </Button>
      </div>
      
      <Table>
        <TableHeader>
          <TableRow className="bg-gray-100">
            <TableHead className="font-medium">Medical/Rescue Unit ID</TableHead>
            <TableHead className="font-medium">Type of Support Provided</TableHead>
            <TableHead className="font-medium">Time of Arrival</TableHead>
            <TableHead className="w-24"></TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {medicalSupport.map((support, index) => (
            <TableRow key={index}>
              <TableCell>{support.id}</TableCell>
              <TableCell>{support.type}</TableCell>
              <TableCell>{support.time}</TableCell>
              <TableCell>
                <div className="flex gap-1">
                  <Button variant="ghost" size="sm" className="h-8 w-8 p-0">
                    🗑️
                  </Button>
                  <Button variant="ghost" size="sm" className="h-8 w-8 p-0">
                    ✏️
                  </Button>
                  <Button variant="ghost" size="sm" className="h-8 w-8 p-0">
                    ℹ️
                  </Button>
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
} 