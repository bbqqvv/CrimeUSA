
import InitialEvidenceForm from "@/components/form/InitialEvidenceForm"
import RelevantPartiesForm from "@/components/form/RelevantPartiesForm"
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import IncidentInfoForm from "@/components/IncidentInfoForm";
import RelevantPartiesTable from "@/components/RelevantPartiesTable";
import InitialEvidenceTable from "@/components/InitialEvidenceTable";
import ConfirmModal from "@/components/ConfirmModal";
import DeleteModal from "@/components/DeleteModal";

export default function Step2({ data, onNext, onBack }: any) {
  const router = useRouter();
  const [form, setForm] = useState(data);
  const [date, setDate] = useState<Date | undefined>(undefined); // ✅ ADD THIS LINE

  const [showConfirm, setShowConfirm] = useState(false);
  const [showDelete, setShowDelete] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState<{
    id: number;
    type: "relevant" | "initial";
  } | null>(null);

  const [relevantParties, setRelevantParties] = useState<any[]>([]);
  const [initialEvidence, setInitialEvidence] = useState<any[]>([]);
  const router = useRouter();
  const [showForm, setShowForm] = useState(false);
  // Dữ liệu lấy từ props (database)
  //const relevantParties = data.relevantParties || [];
  //const initialEvidence = data.initialEvidence || [];
  // Lấy dữ liệu từ session
  const [showInitialModal, setShowInitialModal] = useState(false);
  const [showRelevantModal, setShowRelevantModal] = useState(false);
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
  useEffect(() => {
    const parties = sessionStorage.getItem("relevantParties");
    const evidence = sessionStorage.getItem("initialEvidence");
    if (parties) setRelevantParties(JSON.parse(parties));
    if (evidence) setInitialEvidence(JSON.parse(evidence));
  }, []);

  const handleDelete = (id: number, type: "relevant" | "initial") => {
    setDeleteTarget({ id, type });
    setShowDelete(true);
  };
  const [selectedParty, setSelectedParty] = useState<any>(null)
  const handleDeleteYes = () => {
    if (!deleteTarget) return;

    if (deleteTarget.type === "relevant") {
      const updated = relevantParties.filter((p) => p.id !== deleteTarget.id);
      setRelevantParties(updated);
      sessionStorage.setItem("relevantParties", JSON.stringify(updated));
    } else {
      const updated = initialEvidence.filter((e) => e.id !== deleteTarget.id);
      setInitialEvidence(updated);
      sessionStorage.setItem("initialEvidence", JSON.stringify(updated));
    }

    setShowDelete(false);
    setDeleteTarget(null);
  };

  const handleSubmit = () => setShowConfirm(true);
  const handleConfirmSubmit = () => {
    setShowConfirm(false);
    onNext(form);
  };

  return (
    <div className="w-full max-w-screen-md mx-auto py-8">
      <h2 className="text-2xl font-semibold text-center mb-8">Incident Information</h2>
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
                  <TableHead className="text-center font-semibold">
                    ID
                  </TableHead>
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
                    <TableCell
                      colSpan={6}
                      className="text-center text-gray-400"
                    >
                      No data
                    </TableCell>
                  </TableRow>
                ) : (
                  relevantParties.map((party: any) => (
                    <TableRow
                      key={party.id}
                      className="border-t border-gray-200"
                    >
                      <TableCell className="text-center font-medium">
                        #{party.id}
                      </TableCell>
                      <TableCell className="text-center">
                        {party.relation}
                      </TableCell>
                      <TableCell className="text-center">
                        {party.fullname || "—"}
                      </TableCell>
                      <TableCell className="text-center max-w-xs truncate">
                        {party.description || "—"}
                      </TableCell>
                      <TableCell className="text-center">
                        <span className="text-[#3B82F6]">
                          {party.attachments}
                        </span>
                      </TableCell>
                      <TableCell className="text-center">
                        <button
                          className="inline-flex items-center mr-2 text-[#6C63FF] hover:text-blue-700"
                          onClick={() => {
                            setSelectedParty(party)         // <== set dữ liệu cần sửa
                            setShowRelevantModal(true)      // mở modal
                          }}
                        >
                          <Edit size={18} />
                        </button>
                        <button
                          className="inline-flex items-center text-[#F44336] hover:text-red-700"
                          onClick={() => handleDeleteRelevant(party.id)}
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
              onClick={() => setShowRelevantModal(true)}
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
                  <TableHead className="text-center font-semibold">
                    ID
                  </TableHead>
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
                    <TableCell
                      colSpan={6}
                      className="text-center text-gray-400"
                    >
                      No data
                    </TableCell>
                  </TableRow>
                ) : (
                  initialEvidence.map((evidence: any) => (
                    <TableRow
                      key={evidence.id}
                      className="border-t border-gray-200"
                    >
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
                        <button
                          className="inline-flex items-center mr-2 text-[#6C63FF] hover:text-blue-700"
                          onClick={() =>
                            router.push(`/reporter/initial?id=${evidence.id}`)
                          }
                        >
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
              onClick={() => setShowInitialModal(true)}
            >
              ADD
            </Button>
          </div>
        </div>
      }

      <div className="flex justify-end gap-4 mt-8">
        <button className="btn border px-4 py-2 rounded-xl" onClick={onBack}>Back</button>
        <button className="btn bg-black text-white px-4 py-2 rounded-xl" onClick={handleSubmit}>Submit</button>
      </div>

      {showConfirm && (
        <ConfirmModal
          onClose={() => setShowConfirm(false)}
          onConfirm={handleConfirmSubmit}
        />
      )}
      {showDelete && (
        <DeleteModal
          onClose={() => setShowDelete(false)}
          onConfirm={handleConfirmDelete}
        />
      )}


      {showRelevantModal && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/30">
          <div className="bg-white p-4 rounded-xl w-[90%] max-w-4xl max-h-[90vh] overflow-auto">
            <RelevantPartiesForm
              onClose={() => setShowRelevantModal(false)}
              onSubmitted={() => {
                setShowRelevantModal(false);
                const data = JSON.parse(sessionStorage.getItem("relevantParties") || "[]");
                setRelevantParties(data);
              }}
            />
          </div>
        </div>
      )}

      {showInitialModal && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/30">
          <div className="bg-white p-4 rounded-xl w-[90%] max-w-4xl max-h-[90vh] overflow-auto">
            <InitialEvidenceForm
              onClose={() => setShowInitialModal(false)}
              onSubmitted={() => {
                setShowInitialModal(false);
                const data = JSON.parse(sessionStorage.getItem("initialEvidence") || "[]");
                setInitialEvidence(data);
              }}
            />
          </div>
        </div>
      )}


    </div>

  );
}
