// Step1.tsx
import { useState } from "react";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectTrigger,
  SelectValue,
  SelectContent,
  SelectItem,
} from "@/components/ui/select";
import { Textarea } from "@/components/ui/textarea";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import {
  Table,
  TableHeader,
  TableBody,
  TableHead,
  TableRow,
  TableCell,
} from "@/components/ui/table";
import { Calendar } from "@/components/ui/calendar";
import {
  Popover,
  PopoverTrigger,
  PopoverContent,
} from "@/components/ui/popover";
import { format } from "date-fns";
import { Edit, Trash2 } from "lucide-react";
import { useRouter } from "next/navigation";
// import Calendar hoặc DatePicker nếu có

export default function Step2({ data, onNext, onBack }: any) {
  const [form, setForm] = useState(data);
  const [date, setDate] = useState<Date | undefined>(undefined);
  const [open, setOpen] = useState(false);
  const [showConfirm, setShowConfirm] = useState(false);
  const [showDelete, setShowDelete] = useState(false);
  const router = useRouter();
  const [showForm, setShowForm] = useState(false);
  // Dữ liệu lấy từ props (database)
  //const relevantParties = data.relevantParties || [];
  //const initialEvidence = data.initialEvidence || [];
  // Lấy dữ liệu từ session

  const relevantParties = typeof window !== 'undefined'
    ? JSON.parse(sessionStorage.getItem('relevantParties') || '[]')
    : [];
  const initialEvidence = typeof window !== 'undefined'
    ? JSON.parse(sessionStorage.getItem('initialEvidence') || '[]')
    : []


  const handleChange = (e: any) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    setShowConfirm(true);
  };

  const handleConfirmYes = () => {
    setShowConfirm(false);
    onNext(form);
  };

  const handleDelete = () => {
    setShowDelete(true);
  };

  const handleDeleteYes = () => {
    setShowDelete(false);
    // Xử lý xoá thực tế ở đây nếu cần
  };

  const handleDeleteEvidence = (id: number) => {
  const updatedEvidence = initialEvidence.filter((item: any) => item.id !== id)
  sessionStorage.setItem('initialEvidence', JSON.stringify(updatedEvidence))
  // Có thể cần thêm state để trigger re-render
}

  return (
    <div className="w-full max-w-screen-md mx-auto py-8">
      {/* Incident Information */}
      <div className="my-8">
        <div className="flex items-center mb-8">
          <div className="flex-1 border-t border-gray-300" />
          <h2 className="mx-4 font-semibold text-lg sm:text-2xl">
            Incident Information
          </h2>
          <div className="flex-1 border-t border-gray-300" />
        </div>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-x-10 gap-y-6">
          <div className="space-y-2">
            <Label htmlFor="typeOfCrime" className="text-base font-semibold">
              Type of crime <span className="text-red-500">*</span>
            </Label>
            <Select>
              <SelectTrigger className="w-full">
                <SelectValue placeholder="Select an option" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="crimes-against-persons">
                  Crimes Against Persons
                </SelectItem>
                <SelectItem value="crimes-against-property">
                  Crimes Against Property
                </SelectItem>
                <SelectItem value="white-collar-crimes">
                  White-Collar Crimes
                </SelectItem>
                <SelectItem value="cyber-crimes">Cyber Crimes</SelectItem>
                <SelectItem value="drug-related-crimes">
                  Drug-related Crimes
                </SelectItem>
                <SelectItem value="public-order-crimes">
                  Public Order Crimes
                </SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="space-y-2">
            <Label htmlFor="severity" className="text-base font-semibold">
              Severity <span className="text-red-500">*</span>
            </Label>
            <Select>
              <SelectTrigger className="w-full">
                <SelectValue placeholder="Select an option" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="minor">Minor</SelectItem>
                <SelectItem value="moderate">Moderate</SelectItem>
                <SelectItem value="serious">Serious</SelectItem>
                <SelectItem value="critical">Critical</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="space-y-2">
            <Label htmlFor="datetime" className="text-base font-semibold">
              Datetime of occurrence <span className="text-red-500">*</span>
            </Label>
            <Popover open={open} onOpenChange={setOpen}>
              <PopoverTrigger asChild>
                <Button
                  variant="outline"
                  className="w-full justify-start text-left font-normal"
                >
                  {date ? (
                    format(date, "dd/MM/yyyy")
                  ) : (
                    <span className="text-muted-foreground">Choose</span>
                  )}
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-auto p-0" align="start">
                <Calendar
                  mode="single"
                  selected={date}
                  onSelect={setDate}
                  initialFocus
                />
              </PopoverContent>
            </Popover>
          </div>
          <div className="space-y-2">
            <Label htmlFor="address" className="text-base font-semibold">
              Detailed address
            </Label>
            <Input type="text" name="address" className="w-full" />
          </div>
        </div>
        <div className="mt-6 space-y-2">
          <Label htmlFor="description" className="text-base font-semibold">
            Description of the incident
          </Label>
          <Textarea
            name="description"
            placeholder="Briefly describe what happened, including key facts such as time, location, and main events."
            className="w-full"
          />
        </div>
      </div>

      <div className="w-full max-w-screen-md mx-auto py-8">

        {/* Relevant Parties */}
        <div className="my-8">
          <div className="flex items-center mb-4">
            <div className="flex-1 border-t border-gray-300" />
            <h2 className="mx-4 font-semibold text-lg sm:text-2xl">
              Relevant Parties
            </h2>
            <div className="flex-1 border-t border-gray-300" />
          </div>
          <div className="overflow-x-auto rounded-lg border border-gray-200 bg-white">
            <Table>
              <TableHeader>
                <TableRow className="bg-[#F8F8F8]">
                  <TableHead className="text-center font-semibold">ID</TableHead>
                  <TableHead className="text-center font-semibold">
                    Relevant Role
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Name
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Statement
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Attachments
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Action
                  </TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {relevantParties.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={6} className="text-center text-gray-400">
                      No data
                    </TableCell>
                  </TableRow>
                ) : (
                  relevantParties.map((party: any) => (
                    <TableRow key={party.id} className="border-t border-gray-200">
                      <TableCell className="text-center font-medium">
                        #{party.id}
                      </TableCell>
                      <TableCell className="text-center">
                        {party.relation}
                      </TableCell>
                      <TableCell className="text-center">
                        {party.fullname || '—'}
                      </TableCell>
                      <TableCell className="text-center max-w-xs truncate">
                        {party.description || '—'}
                      </TableCell>
                      <TableCell className="text-center">
                        <span className="text-[#3B82F6]">
                          {party.attachments}
                        </span>
                      </TableCell>
                      <TableCell className="text-center">
                        <button className="inline-flex items-center mr-2 text-[#6C63FF] hover:text-blue-700">
                          <Edit size={18} />
                        </button>
                        <button
                          className="inline-flex items-center text-[#F44336] hover:text-red-700"
                          onClick={handleDelete}
                        >
                          <Trash2 size={18} />
                        </button>
                      </TableCell>
                    </TableRow>
                  ))
                )}
              </TableBody>
            </Table>
          </div>
          <div className="flex justify-end mt-2">
            <Button
              variant="outline"
              className="bg-[#F3F6F9] text-[#434343] font-semibold rounded-md px-8"
               onClick={() => router.push("/reporter/relevant")}
             // onClick={() => setShowForm(true)}
            >
              ADD
            </Button>
          </div>
        </div>


      </div>

      {/* Initial Evidence */}
      {
        <div className="my-8">
          <div className="flex items-center mb-4">
            <div className="flex-1 border-t border-gray-300" />
            <h2 className="mx-4 font-semibold text-lg sm:text-2xl">
              Initial Evidence
            </h2>
            <div className="flex-1 border-t border-gray-300" />
          </div>
          <div className="overflow-x-auto rounded-lg border border-gray-200 bg-white">
            <Table>
              <TableHeader>
                <TableRow className="bg-[#F8F8F8]">
                  <TableHead className="text-center font-semibold">ID</TableHead>
                  <TableHead className="text-center font-semibold">
                    Types of Evidence
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Location
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Description
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Attachments
                  </TableHead>
                  <TableHead className="text-center font-semibold">
                    Action
                  </TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {initialEvidence.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={6} className="text-center text-gray-400">
                      No data
                    </TableCell>
                  </TableRow>
                ) : (
                  initialEvidence.map((evidence: any) => (
                    <TableRow key={evidence.id} className="border-t border-gray-200">
                      <TableCell className="text-center font-medium">
                        #{evidence.id}
                      </TableCell>
                      <TableCell className="text-center">
                        {evidence.evidenceType}
                      </TableCell>
                      <TableCell className="text-center">
                        {evidence.location}
                      </TableCell>
                      <TableCell className="text-center max-w-xs truncate">
                        {evidence.description}
                      </TableCell>
                      <TableCell className="text-center">
                        <span className="text-[#3B82F6]">
                          {evidence.attachments}
                        </span>
                      </TableCell>
                      <TableCell className="text-center">
                        <button className="inline-flex items-center mr-2 text-[#6C63FF] hover:text-blue-700">
                          <Edit size={18} />
                        </button>
                        <button
                          className="inline-flex items-center text-[#F44336] hover:text-red-700"
                          onClick={() => handleDeleteEvidence(evidence.id)}
                        >
                          <Trash2 size={18} />
                        </button>
                      </TableCell>
                    </TableRow>
                  ))
                )}
              </TableBody>
            </Table>
          </div>
          <div className="flex justify-end mt-2">
            <Button
              variant="outline"
              className="bg-[#F3F6F9] text-[#434343] font-semibold rounded-md px-8"
              onClick={() => router.push("/reporter/initial")}
            >
              ADD
            </Button>
          </div>
        </div>
      }


      {/* Nút điều hướng */}
      <div className="flex justify-end gap-4 mt-8">
        <Button variant="outline" className="w-32" onClick={onBack}>
          Back
        </Button>
        <Button className="w-32 bg-black text-white" onClick={handleSubmit}>
          Submit
        </Button>
      </div>

      {/* Modal CONFIRM */}
      {showConfirm && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/30">
          <div className="bg-white rounded-xl shadow-xl p-8 w-full max-w-md">
            <div className="flex items-start gap-4 mb-4">
              <div className="w-2 h-10 rounded bg-blue-300" />
              <div>
                <div className="text-xl font-bold mb-1">
                  Declaration & Confirmation
                </div>
                <ol className="text-gray-700 text-sm list-decimal pl-5">
                  <li>
                    I hereby declare that all the information provided in this
                    report is true and accurate to the best of my knowledge.
                  </li>
                  <li>
                    I accept full legal responsibility for any false or
                    misleading information submitted.
                  </li>
                </ol>
              </div>
            </div>
            <div className="flex justify-end gap-4 mt-6">
              <Button variant="outline" onClick={() => setShowConfirm(false)}>
                Cancel
              </Button>
              <Button
                className="bg-black text-white"
                onClick={handleConfirmYes}
              >
                Yes
              </Button>
            </div>
          </div>
        </div>
      )}
      {/* Modal DELETE */}
      {showDelete && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/30">
          <div className="bg-white rounded-xl shadow-xl p-8 w-full max-w-md">
            <div className="flex items-start gap-4 mb-4">
              <div className="w-2 h-10 rounded bg-red-200" />
              <div>
                <div className="text-xl font-bold mb-1 text-red-600">
                  Delete
                </div>
                <div className="text-gray-700 text-sm">
                  Are you sure you want to delete this record?
                </div>
              </div>
            </div>
            <div className="flex justify-end gap-4 mt-6">
              <Button variant="outline" onClick={() => setShowDelete(false)}>
                Cancel
              </Button>
              <Button className="bg-black text-white" onClick={handleDeleteYes}>
                Yes
              </Button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
