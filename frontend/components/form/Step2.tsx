"use client";
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
  const [showConfirm, setShowConfirm] = useState(false);
  const [showDelete, setShowDelete] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState<{
    id: number;
    type: "relevant" | "initial";
  } | null>(null);

  const [relevantParties, setRelevantParties] = useState<any[]>([]);
  const [initialEvidence, setInitialEvidence] = useState<any[]>([]);

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

  const handleConfirmDelete = () => {
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

      <IncidentInfoForm form={form} setForm={setForm} />

      <RelevantPartiesTable
        data={relevantParties || []}
        onEdit={(id) => router.push(`/reporter/relevant?id=${id}`)}
        onDelete={(id) => handleDelete(id, "relevant")}
        onAdd={() => router.push("/reporter/relevant")}
      />

      <InitialEvidenceTable
        data={initialEvidence || []}
        onEdit={(id) => router.push(`/reporter/initial?id=${id}`)}
        onDelete={(id) => handleDelete(id, "initial")}
        onAdd={() => router.push("/reporter/initial")}
      />

      <div className="flex justify-end gap-4 mt-8">
        <button className="btn border px-4 py-2" onClick={onBack}>Back</button>
        <button className="btn bg-black text-white px-4 py-2" onClick={handleSubmit}>Submit</button>
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
    </div>
  );
}
