interface ProgressStepsProps {
  currentStep: number
  totalSteps: number
}

export function ProgressSteps({ currentStep, totalSteps }: ProgressStepsProps) {
  return (
    <div className="flex items-center justify-center mb-12">
      <div className="flex items-center space-x-8">
        {Array.from({ length: totalSteps }, (_, i) => i + 1).map((step, index) => (
          <div key={step} className="flex items-center">
            <div className="flex flex-col items-center">
              <div
                className={`w-12 h-12 rounded-full flex items-center justify-center text-white font-bold ${
                  currentStep === step ? "bg-black" : "bg-gray-300"
                }`}
              >
                {step}
              </div>
              <span className="mt-2 text-sm font-medium">Step {step}</span>
            </div>
            {index < totalSteps - 1 && <div className="w-16 h-px bg-gray-300 ml-8"></div>}
          </div>
        ))}
      </div>
    </div>
  )
}
