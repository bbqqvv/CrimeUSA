import { Button } from "@/components/ui/button";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

const measures = [
  { 
    number: 1, 
    description: "Immediate perimeter established using police tape (approx. 30-meter radius)" 
  },
  { 
    number: 2, 
    description: "Vehicle stabilized to prevent further movement." 
  },
  { 
    number: 3, 
    description: "Photographic documentation of the scene commenced at 22:26." 
  },
];

export default function PreservationMeasuresSection() {
  return (
    <div className="bg-white rounded-lg p-6 shadow-sm border">
      <div className="flex items-center justify-between mb-4">
        <label className="text-sm font-medium text-gray-700">
          SCENE PRESERVATION MEASURES TAKEN
        </label>
        <Button variant="outline" size="sm">
          ADD ⊕
        </Button>
      </div>
      
      <Table>
        <TableHeader>
          <TableRow className="bg-gray-100">
            <TableHead className="font-medium w-16">#</TableHead>
            <TableHead className="font-medium">Preservation Measures</TableHead>
            <TableHead className="w-20"></TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {measures.map((measure, index) => (
            <TableRow key={index}>
              <TableCell>{measure.number}</TableCell>
              <TableCell>{measure.description}</TableCell>
              <TableCell>
                <div className="flex gap-1">
                  <Button variant="ghost" size="sm" className="h-8 w-8 p-0">
                    🗑️
                  </Button>
                  <Button variant="ghost" size="sm" className="h-8 w-8 p-0">
                    ✏️
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