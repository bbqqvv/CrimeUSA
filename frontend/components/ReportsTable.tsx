// src/components/ReportsTable.tsx
'use client';

import { useState } from 'react';
import { reports } from '@/data/mockData';
import Badge from './ui/Badge';

type ReportStatus = 'Approved' | 'Pending' | 'Rejected';

const ReportsTable = () => {
  const [currentPage, setCurrentPage] = useState(1);
  // Thêm logic cho filter và pagination sau

  return (
    <div className="bg-white shadow-md rounded-lg p-6">
      <table className="w-full text-sm text-left text-gray-600">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50">
          <tr>
            <th scope="col" className="px-6 py-3">Report ID</th>
            <th scope="col" className="px-6 py-3">Type of Crime</th>
            <th scope="col" className="px-6 py-3">Severity</th>
            <th scope="col" className="px-6 py-3">Date</th>
            <th scope="col" className="px-6 py-3">Reporter</th>
            <th scope="col" className="px-6 py-3">Status</th>
            <th scope="col" className="px-6 py-3">Action</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((report) => (
            <tr key={report.id + report.reporter} className="bg-white border-b hover:bg-gray-50">
              <td className="px-6 py-4 font-medium text-gray-900">{report.id}</td>
              <td className="px-6 py-4">{report.type}</td>
              <td className="px-6 py-4">{report.severity}</td>
              <td className="px-6 py-4">{report.date}</td>
              <td className="px-6 py-4">{report.reporter}</td>
              <td className="px-6 py-4">
                <Badge status={report.status as ReportStatus} />
              </td>
              <td className="px-6 py-4">
                <a href="#" className="font-medium text-blue-600 hover:underline">View detail</a>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Pagination */}
      <div className="flex justify-end items-center mt-4 text-sm">
        <button className="px-3 py-1 rounded hover:bg-gray-200">Previous</button>
        <span className="px-3 py-1 bg-blue-500 text-white rounded-full mx-1">1</span>
        <span className="px-3 py-1 rounded-full hover:bg-gray-200 mx-1">2</span>
        <span className="px-3 py-1 rounded-full hover:bg-gray-200 mx-1">3</span>
        <button className="px-3 py-1 rounded hover:bg-gray-200">Next</button>
      </div>
    </div>
  );
};

export default ReportsTable;