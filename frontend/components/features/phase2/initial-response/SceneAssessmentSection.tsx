import { Button } from "@/components/ui/button";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

const assessments = [
  { id: "PE-01", location: "A1 - Kitchen", collector: "Lt. James Potter", time: "14:35 - 25/06/25" },
  { id: "PE-02", location: "B2 - Living Room", collector: "Sgt. Ron Weasley", time: "14:42 - 25/06/25" },
];

export default function SceneAssessmentSection() {
  return (
    <div className="bg-white rounded-lg p-6 shadow-sm border">
      <div className="flex items-center justify-between mb-4">
        <label className="text-sm font-medium text-gray-700">
          PRELIMINARY ASSESSMENT OF THE SCENE SITUATION
        </label>
        <Button variant="outline" size="sm">
          ADD ⊕
        </Button>
      </div>
      
      <Table>
        <TableHeader>
          <TableRow className="bg-gray-100">
            <TableHead className="font-medium">ID</TableHead>
            <TableHead className="font-medium">Location</TableHead>
            <TableHead className="font-medium">Collector</TableHead>
            <TableHead className="font-medium">Time</TableHead>
            <TableHead className="w-32"></TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {assessments.map((assessment, index) => (
            <TableRow key={index}>
              <TableCell>{assessment.id}</TableCell>
              <TableCell>{assessment.location}</TableCell>
              <TableCell>{assessment.collector}</TableCell>
              <TableCell>{assessment.time}</TableCell>
              <TableCell>
                <Button variant="outline" size="sm">
                  View details
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
} 