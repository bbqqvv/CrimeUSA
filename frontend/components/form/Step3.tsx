// Step1.tsx
import { useState } from 'react';

export default function Step3({ data, onNext }: any) {
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
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <input name="fullName" value={form.fullName} onChange={handleChange} placeholder="Full name" />
                <input name="email" value={form.email} onChange={handleChange} placeholder="Email" />
                <input name="phone" value={form.phone} onChange={handleChange} placeholder="Phone number" />
                <input name="address" value={form.address} onChange={handleChange} placeholder="Address" />
            </div>

            {/* Relationship radio group */}
            <div className="mt-4">
                <p className="mb-2">Relationship to the incident *</p>
                {['Victim', 'Witness', 'Offender', 'Anonymous'].map((r) => (
                    <label key={r} className="block">
                        <input
                            type="radio"
                            name="relationship"
                            value={r}
                            checked={form.relationship === r}
                            onChange={handleChange}
                        />{' '}
                        {r}
                    </label>
                ))}
            </div>

            <button onClick={handleSubmit} className="mt-6 px-4 py-2 bg-black text-white rounded">
                Next
            </button>
        </div>
    );
}
