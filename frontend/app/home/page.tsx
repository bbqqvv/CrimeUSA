"use client";
import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";


const officersInit = [
  { name: "Brandie", role: "Patrol Officer", phone: "(225) 555-0118" },
  { name: "Brandie", role: "Patrol Officer", phone: "(225) 555-0118" },
  { name: "Brandie", role: "Detective", phone: "(225) 555-0118" },
];

const preservationInit = [
  "Immediate perimeter established using police tape (approx. 30-meter radius)",
  "Vehicle stabilized to prevent further movement.",
  "Photographic documentation of the scene commenced at 22:26.",
];

const medicalInit = [
  { id: "EMS45", type: "Medical Emergency", time: "08:00 PM" },
  { id: "RES-012", type: "Patrol Officer", time: "08:00 PM" },
  { id: "RES-012", type: "Detective", time: "08:00 PM" },
];

export default function Page() {
  const [officers, setOfficers] = useState(officersInit);
  const [preservations, setPreservations] = useState(preservationInit);
  const [medical, setMedical] = useState(medicalInit);

  return (
    <div className="flex min-h-screen bg-gray-100 p-4">

       {/* Main Content */}
      <section className="flex-1 bg-white rounded-r-lg shadow p-6 ml-2">
        <h2 className="text-lg font-bold bg-blue-100 text-blue-900 px-4 py-2 rounded mb-6">INITIAL RESPONSE</h2>
        
        {/* Time of dispatch */}
        <div id="dispatch" className="mb-4">
          <label className="block font-semibold mb-1">TIME OF DISPATCHING FORCES TO THE SCENE</label>
          <Button variant="outline">Choose</Button>
        </div>
        
        {/* Time of arrival */}
        <div id="arrival" className="mb-4">
          <label className="block font-semibold mb-1">TIME OF ARRIVAL AT THE SCENE</label>
          <div className="flex items-center gap-2">
            <Input className="w-20" value="09:32" readOnly />
            <Button variant="outline">AM</Button>
            <Button variant="outline">PM</Button>
          </div>
        </div>
        
        {/* Officers */}
        <div id="officers" className="mb-4">
          <div className="flex justify-between items-center mb-1">
            <label className="font-semibold">LIST OF OFFICERS ASSIGNED TO THE SCENE</label>
            <Button size="sm" variant="outline">ADD</Button>
          </div>
          <table className="w-full text-sm border">
            <thead>
              <tr className="bg-gray-100">
                <th className="p-2">Full Name</th>
                <th className="p-2">Role</th>
                <th className="p-2">Phone Number</th>
                <th className="p-2"></th>
              </tr>
            </thead>
            <tbody>
              {officers.map((o, i) => (
                <tr key={i} className="border-t">
                  <td className="p-2">{o.name}</td>
                  <td className="p-2">{o.role}</td>
                  <td className="p-2">{o.phone}</td>
                  <td className="p-2 flex gap-2">
                    <Button size="icon" variant="ghost">✏️</Button>
                    <Button size="icon" variant="ghost">🗑️</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        
        {/* Preliminary assessment */}
        <div id="preservation" className="mb-4">
          <div className="flex justify-between items-center mb-1">
            <label className="font-semibold">PRELIMINARY ASSESSMENT OF THE SCENE SITUATION</label>
            <Button size="sm" variant="outline">ADD</Button>
          </div>
          <Textarea className="w-full" value="A s" readOnly />
        </div>
        
        {/* Scene preservation */}
        <div id="preservation" className="mb-4">
          <div className="flex justify-between items-center mb-1">
            <label className="font-semibold">SCENE PRESERVATION MEASURES TAKEN</label>
            <Button size="sm" variant="outline">ADD</Button>
          </div>
          <table className="w-full text-sm border">
            <thead>
              <tr className="bg-gray-100">
                <th className="p-2">#</th>
                <th className="p-2">Preservation Measures</th>
                <th className="p-2"></th>
              </tr>
            </thead>
            <tbody>
              {preservations.map((p, i) => (
                <tr key={i} className="border-t">
                  <td className="p-2">{i + 1}</td>
                  <td className="p-2">{p}</td>
                  <td className="p-2 flex gap-2">
                    <Button size="icon" variant="ghost">✏️</Button>
                    <Button size="icon" variant="ghost">🗑️</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        
        {/* Medical/Rescue */}
        <div id="medical" className="mb-4">
          <div className="flex justify-between items-center mb-1">
            <label className="font-semibold">INFORMATION ON MEDICAL/RESCUE SUPPORT PROVIDED</label>
            <Button size="sm" variant="outline">ADD</Button>
          </div>
          <table className="w-full text-sm border">
            <thead>
              <tr className="bg-gray-100">
                <th className="p-2">Medical/Rescue Unit ID</th>
                <th className="p-2">Type of Support Provided</th>
                <th className="p-2">Time of Arrival</th>
                <th className="p-2"></th>
              </tr>
            </thead>
            <tbody>
              {medical.map((m, i) => (
                <tr key={i} className="border-t">
                  <td className="p-2">{m.id}</td>
                  <td className="p-2">{m.type}</td>
                  <td className="p-2">{m.time}</td>
                  <td className="p-2 flex gap-2">
                    <Button size="icon" variant="ghost">✏️</Button>
                    <Button size="icon" variant="ghost">🗑️</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        
        {/* Actions */}
        <div className="flex justify-end gap-4 mt-8">
          <Button variant="outline">Cancel</Button>
          <Button>Save</Button>
        </div>
      </section>
    </div>
  );
}