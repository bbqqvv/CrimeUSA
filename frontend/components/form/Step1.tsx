'use client';

import { useState } from 'react';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { Button } from '@/components/ui/button';
import { Separator } from '@radix-ui/react-dropdown-menu';

type FormData = {
  // Step1
  fullName: string;
  email: string;
  phone: string;
  address: string;
  relationship: string;

  // Step2
  typeOfCrime?: string;
  severity?: string;
  dateOfOccurrence?: string; // or Date
  incidentAddress?: string;
  incidentDescription?: string;
  relevantParties?: any[];
  initialEvidence?: any[];
};

type Props = {
  data: FormData;
  onNext: (data: FormData) => void;
};

export default function Step1({ data, onNext }: Props) {
  const [localData, setLocalData] = useState<FormData>(data);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setLocalData((prev) => ({ ...prev, [id]: value }));
  };

  const handleRelationshipChange = (value: string) => {
    setLocalData((prev) => ({ ...prev, relationship: value }));
  };

  const handleNextClick = () => {
    onNext(localData);
  };

  const relationshipOptions = [
    { value: 'victim', label: 'Victim' },
    { value: 'witness', label: 'Witness' },
    { value: 'offender', label: 'Offender' },
    { value: 'anonymous', label: 'Anonymous' },
  ];

  return (
    <div className='w-full max-w-screen-md mx-auto'>
      {/* Title */}
      <div className='flex items-center mb-8'>
        <Separator className='flex-1' />
        <h2 className='mx-4 font-semibold text-lg sm:text-2xl'>
          Reporter Information
        </h2>
        <Separator className='flex-1' />
      </div>

      {/* Form fields */}
      <div className='grid grid-cols-1 md:grid-cols-2 gap-x-10 gap-y-6'>
        <div className='space-y-2'>
          <Label htmlFor='fullName'>
            Full name <span className='text-red-500'>*</span>
          </Label>
          <Input
            id='fullName'
            value={localData.fullName}
            onChange={handleChange}
          />
        </div>
        <div className='space-y-2'>
          <Label htmlFor='email'>
            Email <span className='text-red-500'>*</span>
          </Label>
          <Input
            id='email'
            type='email'
            value={localData.email}
            onChange={handleChange}
          />
        </div>
        <div className='space-y-2'>
          <Label htmlFor='phone'>
            Phone number <span className='text-red-500'>*</span>
          </Label>
          <Input id='phone' value={localData.phone} onChange={handleChange} />
        </div>
        <div className='space-y-2'>
          <Label htmlFor='address'>Address</Label>
          <Input
            id='address'
            value={localData.address}
            onChange={handleChange}
          />
        </div>
      </div>

      {/* Relationship */}
      <div className='mt-8 space-y-4'>
        <Label className='text-base sm:text-xl'>
          Relationship to the incident <span className='text-red-500'>*</span>
        </Label>
        <RadioGroup
          value={localData.relationship}
          onValueChange={handleRelationshipChange}
          className='space-y-4 mt-4'
        >
          {relationshipOptions.map((option) => (
            <div key={option.value} className='flex items-center space-x-4'>
              <RadioGroupItem
                value={option.value}
                id={option.value}
                className='w-5 h-5 border-2 border-gray-500'
              />
              <Label htmlFor={option.value} className='text-base sm:text-lg'>
                {option.label}
              </Label>
            </div>
          ))}
        </RadioGroup>
      </div>

      {/* Next Button */}
      <div className='flex justify-end mt-12'>
        <Button
          className='w-40 h-[50px] bg-[#434343] text-white font-semibold rounded-lg'
          onClick={handleNextClick}
        >
          Next
        </Button>
      </div>
    </div>
  );
}
