// MultiStepForm.tsx
import { useState } from 'react';
import Step1 from './Step1';
import Step2 from './Step2';
import Step3 from './Step3';
import StepIndicator from '../ui/StepIndicator';

export default function MultiStepForm() {
    const [step, setStep] = useState(1);
    const [formData, setFormData] = useState({
        fullName: '',
        email: '',
        phone: '',
        address: '',
        relationship: '',
        relevantParties: typeof window !== 'undefined'
            ? JSON.parse(sessionStorage.getItem('relevantParties') || '[]')
            : []
    });

    const handleNext = (data: any) => {
        setFormData((prev) => ({ ...prev, ...data }));
        setStep((prev) => prev + 1);
    };

    const handleBack = () => setStep((prev) => prev - 1);

    const handleSubmit = () => {
        console.log('Submitted:', formData);
    };
    const nextStep = (newData: any) => {
        setFormData(prev => ({ ...prev, ...newData }))
        setStep(prev => prev + 1)
    }

    const prevStep = () => {
        setStep(prev => prev - 1)
    }
    return (
        <div className="max-w-3xl mx-auto px-4 py-10">
            <StepIndicator currentStep={step} onStepChange={(n) => setStep(n)} />
            <div className="mt-10">
                {step === 1 && <Step1 data={formData} onNext={handleNext} />}
                {step === 2 && <Step2 data={formData} onNext={nextStep} onBack={prevStep} />}
                {step === 3 && <Step3 data={formData} onBack={handleBack} onSubmit={handleSubmit} />}
            </div>
        </div>
    );
}