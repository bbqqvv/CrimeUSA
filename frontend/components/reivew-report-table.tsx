"use client";

import { useState } from "react";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { EyeIcon, Printer } from "lucide-react";
import { StatusComponent } from "./status-component";

export function ReviewReportTable() {
  const [selectedRows, setSelectedRows] = useState<number[]>([]);
  const report = [
  {
    reportID: "1",
    provider: "oaxbvfk.5098@drive.com",
    date: "10/2/2025",
    time: "12:00",
    status: "Pending",
  },
  {
    reportID: "2",
    provider: "oaxbvfk.5098@drive.com",
    date: "10/3/2025",
    time: "12:00",
    status: "Pending",
  },
];

  return (
    <div className="border rounded-md">
      <Table>
        <TableHeader>
          <TableHead>Report ID</TableHead>
          <TableHead>Provider</TableHead>
          <TableHead>Date</TableHead>
          <TableHead>Time</TableHead>
          <TableHead>Status</TableHead>
          <TableHead>Action</TableHead>
        </TableHeader>
        <TableBody>
          {report.map((report) => (
            <TableRow key={report.reportID}>
              <TableCell className="p-4"> {report.reportID}</TableCell>
              <TableCell className="p-4"> {report.provider}</TableCell>
              <TableCell className="p-4"> {report.date}</TableCell>
              <TableCell className="p-4"> {report.time}</TableCell>
              <TableCell className="p-4"> <StatusComponent status={report.status}></StatusComponent></TableCell>
              <TableCell className="p-4 flex">
                <EyeIcon />
                <Printer className="ml-5" />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}