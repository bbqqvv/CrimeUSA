import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

const officers = [
  { fullName: "Brandie", role: "Patrol Officer", phone: "(225) 555-0118" },
  { fullName: "Brandie", role: "Patrol Officer", phone: "(225) 555-0118" },
  { fullName: "Brandie", role: "Detective", phone: "(225) 555-0118" },
];

export default function OfficersListSection() {
  return (
    <div className="bg-white rounded-lg p-6 shadow-sm border">
      <div className="flex items-center justify-between mb-4">
        <label className="text-sm font-medium text-gray-700">
          LIST OF OFFICERS ASSIGNED TO THE SCENE
        </label>
        <Button variant="outline" size="sm">
          ADD ⊕
        </Button>
      </div>
      
      <Table>
        <TableHeader>
          <TableRow className="bg-gray-100">
            <TableHead className="font-medium">Full Name</TableHead>
            <TableHead className="font-medium">Role</TableHead>
            <TableHead className="font-medium">Phone Number</TableHead>
            <TableHead className="w-20"></TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {officers.map((officer, index) => (
            <TableRow key={index}>
              <TableCell>{officer.fullName}</TableCell>
              <TableCell>{officer.role}</TableCell>
              <TableCell>{officer.phone}</TableCell>
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