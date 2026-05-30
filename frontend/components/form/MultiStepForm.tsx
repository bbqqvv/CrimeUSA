// MultiStepForm.tsx
import { useState } from 'react';
import Step1 from './Step1';
import Step2 from './Step2';
import Step3 from './Step3';
import StepIndicator from '../ui/StepIndicator';
import { useEffect } from 'react';
export default function MultiStepForm() {
  const [step, setStep] = useState(1);
  const [maxStepReached, setMaxStepReached] = useState(1);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitError, setSubmitError] = useState<string | null>(null);
  const [formData, setFormData] = useState({
    // Step1
    fullName: '',
    email: '',
    phone: '',
    address: '',
    relationship: '',

    // Step2
    typeOfCrime: '',
    severity: '',
    incidentDate: '',
    incidentAddress: '',
    incidentDescription: '',
    relevantParties: [] as any[],
    initialEvidence: [] as any[],
  });

  const handleNext = (data: any) => {
    setFormData((prev) => ({ ...prev, ...data }));
    setStep((prev) => {
      const nextStep = prev + 1;
      if (nextStep > maxStepReached) setMaxStepReached(nextStep);
      return nextStep;
    });
  };

  const handleBack = () => setStep((prev) => prev - 1);

  const handleStep2Submit = async (step2Data: any) => {
    setIsSubmitting(true);
    setSubmitError(null);

    const mergedData = { ...formData, ...step2Data };
    setFormData(mergedData);

    const requestBody = {
      typeReport: step2Data.typeOfCrime || 'crimes-against-persons',
      description: step2Data.description || 'No description provided',
      caseLocation: step2Data.incidentAddress || step2Data.address || 'Unknown Location',
      reporterFullname: mergedData.fullName || 'Anonymous',
      reporterEmail: mergedData.email || 'anonymous@example.com',
      reporterPhoneNumber: mergedData.phone || '0000000000'
    };

    try {
      const response = await fetch('http://localhost:8080/api/v1/reports', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
      });

      if (!response.ok) {
        const errData = await response.json().catch(() => ({}));
        throw new Error(errData.message || `Failed to submit report: ${response.statusText}`);
      }

      const result = await response.json();
      console.log('Report submitted successfully:', result);

      if (typeof window !== 'undefined') {
        sessionStorage.removeItem('relevantParties');
        sessionStorage.removeItem('initialEvidence');
      }

      setStep(3);
      setMaxStepReached(3);
    } catch (error: any) {
      console.error('Error submitting report:', error);
      setSubmitError(error.message || 'An error occurred during submission.');
    } finally {
      setIsSubmitting(false);
    }
  };

  const updateFormData = (data: any) => {
    setFormData((prev) => ({ ...prev, ...data }));
  };
  const nextStep = () => {
    setStep((prev) => {
      const next = prev + 1;
      if (next > maxStepReached) setMaxStepReached(next);
      return next;
    });
  };
  const prevStep = () => {
    setStep((prev) => prev - 1);
  };

  useEffect(() => {
    const resume = sessionStorage.getItem('resumeStep');
    if (resume) {
      const resumeStep = Number(resume);
      setStep(resumeStep);
      setMaxStepReached(resumeStep);
      sessionStorage.removeItem('resumeStep');
    }

    // Load saved data from sessionStorage safely after mount to avoid hydration mismatch
    const savedRelevant = sessionStorage.getItem('relevantParties');
    const savedEvidence = sessionStorage.getItem('initialEvidence');
    if (savedRelevant || savedEvidence) {
      setFormData((prev) => ({
        ...prev,
        relevantParties: savedRelevant ? JSON.parse(savedRelevant) : [],
        initialEvidence: savedEvidence ? JSON.parse(savedEvidence) : [],
      }));
    }
  }, []);

  const handleStepChange = (n: number) => {
    if (n <= maxStepReached) {
      setStep(n);
    }
  };

  return (
    <div className='max-w-3xl mx-auto px-4 py-10'>
      <StepIndicator
        currentStep={step}
        maxStepReached={maxStepReached}
        onStepChange={handleStepChange}
      />
      <div className='mt-10'>
        {step === 1 && <Step1 data={formData} onNext={handleNext} />}
        {step === 2 && (
          <Step2
            data={formData}
            onBack={handleBack}
            onNext={handleStep2Submit}
            onUpdate={updateFormData}
            isSubmitting={isSubmitting}
            submitError={submitError}
          />
        )}
        {step === 3 && (
          <Step3 data={formData} onBack={handleBack} />
        )}
      </div>
    </div>
  );
}
