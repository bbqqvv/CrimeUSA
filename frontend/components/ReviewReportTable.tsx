"use client";

import { useState } from "react";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { EyeIcon, Printer } from "lucide-react";
import { StatusComponent } from "./StatusComponent";
import { useRouter } from "next/navigation";

export function ReviewReportTable() {
  const [selectedRows, setSelectedRows] = useState<number[]>([]);
  const router = useRouter();
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
    // <div className="border rounded-md">
      <div className="overflow-x-auto rounded-lg border border-gray-200 bg-white">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Report ID</TableHead>
              <TableHead>Provider</TableHead>
              <TableHead>Date</TableHead>
              <TableHead>Time</TableHead>
              <TableHead>Status</TableHead>
              <TableHead>Action</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {report.map((report) => (
              <TableRow key={report.reportID}>
                <TableCell className="p-4">{report.reportID}</TableCell>
                <TableCell className="p-4">{report.provider}</TableCell>
                <TableCell className="p-4">{report.date}</TableCell>
                <TableCell className="p-4">{report.time}</TableCell>
                <TableCell className="p-4">
                  <StatusComponent status={report.status} />
                </TableCell>
                <TableCell className="p-4 flex">
                  <EyeIcon
                    className="cursor-pointer"
                    onClick={() => router.push(`/view-reporter/` + report.reportID)}
                  />
                  <Printer className="ml-5" />
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>


    // </div>
  );
}