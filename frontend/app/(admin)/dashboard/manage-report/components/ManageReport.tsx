"use client";

import React, { useState } from "react";
import Image from "next/image";
import { Button } from "@/components/ui/button";
import Avatar from "../../../../../public/images/default-user.svg";

const statusOptions = ["Approved", "Pending", "Rejected"];
const crimeTypes = [
    "Crimes Against Persons",
    "Crimes Against Property",
    "White-Collar Crimes",
    "Cyber Crimes",
    "Drug-related Crimes",
    "Public Order Crimes",
];
const severities = ["Minor", "Moderate", "Serious", "Critical"];
const dateOptions = ["Today", "This Week", "This Month", "This Year"];

const Dropdown = ({ options, label }: { options: string[]; label: string }) => {
    const [isOpen, setIsOpen] = useState(false);
    const [selected, setSelected] = useState(label);

    return (
        <div className="relative">
            <button
                onClick={() => setIsOpen(!isOpen)}
                className={`px-4 py-1 rounded-full text-sm font-medium flex items-center space-x-1
                ${selected !== label || isOpen
                        ? "bg-white text-black"
                        : "border border-white/30 text-white"
                    }`}
            >
                <span>{selected}</span>
                <span className="text-xs">{isOpen ? "▲" : "▼"}</span>
            </button>
            {isOpen && (
                <ul className="absolute left-0 mt-2 bg-white text-black rounded shadow z-20 min-w-full max-h-60 overflow-y-auto">
                    {options.map((option, index) => (
                        <li
                            key={index}
                            onClick={() => {
                                setSelected(option);
                                setIsOpen(false);
                            }}
                            className="px-4 py-2 text-sm hover:bg-gray-100 cursor-pointer"
                        >
                            {option}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default function ManageReport() {
    const reports = [
        { id: "#20462", type: "White-Collar Crimes", severity: "Moderate", date: "13/05/2022", reporter: "b", status: "Approved" },
        { id: "#20462", type: "Crimes Against Property", severity: "Moderate", date: "13/05/2022", reporter: "Nguyen van a", status: "Approved" },
        { id: "#18933", type: "White-Collar Crimes", severity: "Moderate", date: "22/05/2022", reporter: "c", status: "Approved" },
        { id: "#45169", type: "White-Collar Crimes", severity: "Minor", date: "15/06/2022", reporter: "d", status: "Pending" },
        { id: "#1171423", type: "White-Collar Crimes", severity: "Moderate", date: "25/09/2022", reporter: "acvfdhbbbbbbb", status: "Rejected" },
        { id: "#117124", type: "White-Collar Crimes", severity: "Minor", date: "25/05/2022", reporter: "acvfdhbbbbbbb", status: "Approved" },
        { id: "#111234", type: "White-Collar Crimes", severity: "Moderate", date: "25/04/2022", reporter: "acvfdhbbbbbbb", status: "Rejected" },
        { id: "#11712", type: "White-Collar Crimes", severity: "Minor", date: "25/03/2022", reporter: "acvfdhbbbbbbb", status: "Approved" },
        { id: "#1132", type: "White-Collar Crimes", severity: "Moderate", date: "25/02/2022", reporter: "acvfdhbbbbbbb", status: "Pending" },
        { id: "#1132", type: "White-Collar Crimes", severity: "Moderate", date: "25/01/2022", reporter: "acvfdhbbbbbbb", status: "Pending" },
        { id: "#1132", type: "White-Collar Crimes", severity: "Moderate", date: "25/07/2022", reporter: "acvfdhbbbbbbb", status: "Pending" },
        { id: "#1132", type: "White-Collar Crimes", severity: "Minor", date: "25/09/2022", reporter: "acvfdhbbbbbbb", status: "Rejected" },
    ];

    return (
        <div className="flex h-screen">
            {/* Sidebar */}
            <div className="w-1/5 bg-gray-800 text-white p-4 flex flex-col">
                <div className="flex items-center mb-8">
                    <Image src={Avatar} alt="User Avatar" width={50} height={50} className="rounded-full" />
                    <div className="ml-4">
                        <h2 className="text-xl font-semibold">KIỂM DUYỆT</h2>
                    </div>
                </div>
                <nav className="flex-1">
                    <ul>
                        <li className="mb-4">
                            <a href="#" className="text-gray-300 hover:text-white flex items-center">
                                <span className="mr-2">🏠</span> Dashboard
                            </a>
                        </li>
                        <li className="mb-4">
                            <a href="#" className="text-white font-bold flex items-center">
                                <span className="mr-2">📊</span> Reports
                            </a>
                        </li>
                        <li className="mb-4">
                            <a href="#" className="text-gray-300 hover:text-white flex items-center">
                                <span className="mr-2">📁</span> Cases
                            </a>
                        </li>
                    </ul>
                </nav>
                <Button className="mt-auto bg-red-600 flex items-center justify-center">Logout</Button>
            </div>

            {/* Main Content */}
            <div className="w-4/5 bg-[#667A8A] p-8">
                <div className="flex gap-5 text-white px-6 py-4 rounded-lg mb-6">
                    <span className="text-sm font-medium whitespace-nowrap">Filter:</span>
                    <Dropdown label="All" options={[]} />
                    <Dropdown label="Status" options={statusOptions} />
                    <Dropdown label="Crime Type" options={crimeTypes} />
                    <Dropdown label="Severity" options={severities} />
                    <Dropdown label="Created at" options={dateOptions} />
                </div>

                <table className="w-full table-auto bg-white shadow rounded">
                    <thead>
                        <tr className="bg-gray-200">
                            <th className="px-4 py-2 text-left">Report ID</th>
                            <th className="px-4 py-2 text-left">Type of Crime</th>
                            <th className="px-4 py-2 text-left">Severity</th>
                            <th className="px-4 py-2 text-left">Date</th>
                            <th className="px-4 py-2 text-left">Reporter</th>
                            <th className="px-4 py-2 text-left">Status</th>
                            <th className="px-4 py-2 text-left">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {reports.map((report, index) => (
                            <tr key={index} className="border-b hover:bg-gray-100">
                                <td className="px-4 py-2">{report.id}</td>
                                <td className="px-4 py-2">{report.type}</td>
                                <td className="px-4 py-2">{report.severity}</td>
                                <td className="px-4 py-2">{report.date}</td>
                                <td className="px-4 py-2">{report.reporter}</td>
                                <td className="px-4 py-2">
                                    <span
                                        className={`px-2 py-1 rounded text-white ${report.status === "Approved"
                                            ? "bg-green-500"
                                            : report.status === "Pending"
                                                ? "bg-yellow-500"
                                                : "bg-red-500"
                                            }`}
                                    >
                                        {report.status}
                                    </span>
                                </td>
                                <td className="px-4 py-2 text-blue-500 cursor-pointer">View Detail</td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <div className="flex justify-center space-x-2 items-center mt-4">
                    <Button variant="outline" className="cursor-pointer">Previous</Button>
                    <Button variant="default" className="cursor-pointer">1</Button>
                    <Button variant="outline" className="cursor-pointer">2</Button>
                    <Button variant="outline" className="cursor-pointer">3</Button>
                    <Button variant="outline" className="cursor-pointer">Next</Button>
                </div>
            </div>
        </div>
    );
}
