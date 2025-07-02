// Step1.tsx
import { useState } from 'react';

export default function Step2({ data, onNext }: any) {
    const [form, setForm] = useState(data);

    const handleChange = (e: any) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = () => {
        onNext(form);
    };

    return (
        <div>
            <h2 className="text-xl font-bold mb-4">Reporter Information</h2>


            <button onClick={handleSubmit} className="mt-6 px-4 py-2 bg-black text-white rounded">
                Next
            </button>
        </div>
    );
}
