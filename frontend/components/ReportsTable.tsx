'use client';

import { useState } from 'react';
import { reports } from '@/data/mockData';
import Badge from './ui/Badge';

type ReportStatus = 'Approved' | 'Pending' | 'Rejected';

const ReportsTable = () => {
  const [currentPage, setCurrentPage] = useState(1);

  return (
    <div className="bg-white shadow-md rounded-lg p-4 md:p-6 w-full">
      {/* Wrapper cho cuộn ngang */}
      <div className="overflow-x-auto">
        <table className="min-w-[700px] w-full text-sm text-left text-gray-600">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
              <th className="px-4 py-3">Report ID</th>
              <th className="px-4 py-3">Type of Crime</th>
              <th className="px-4 py-3">Severity</th>
              <th className="px-4 py-3">Date</th>
              <th className="px-4 py-3">Reporter</th>
              <th className="px-4 py-3">Status</th>
              <th className="px-4 py-3">Action</th>
            </tr>
          </thead>
          <tbody>
            {reports.map((report) => (
              <tr key={report.id + report.reporter} className="bg-white border-b hover:bg-gray-50">
                <td className="px-4 py-4 font-medium text-gray-900">{report.id}</td>
                <td className="px-4 py-4">{report.type}</td>
                <td className="px-4 py-4">{report.severity}</td>
                <td className="px-4 py-4">{report.date}</td>
                <td className="px-4 py-4">{report.reporter}</td>
                <td className="px-4 py-4">
                  <Badge status={report.status as ReportStatus} />
                </td>
                <td className="px-4 py-4">
                  <a href="#" className="text-blue-600 hover:underline whitespace-nowrap">View detail</a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Pagination */}
      <div className="flex flex-wrap justify-center md:justify-end items-center gap-2 mt-4 text-sm">
        <button className="px-3 py-1 rounded hover:bg-gray-200">Previous</button>
        <span className="px-3 py-1 bg-blue-500 text-white rounded-full">1</span>
        <span className="px-3 py-1 rounded-full hover:bg-gray-200">2</span>
        <span className="px-3 py-1 rounded-full hover:bg-gray-200">3</span>
        <button className="px-3 py-1 rounded hover:bg-gray-200">Next</button>
      </div>
    </div>
  );
};

export default ReportsTable;
