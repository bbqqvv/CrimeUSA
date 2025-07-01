import React from 'react';

export const Header: React.FC = () => {
  return (
    <header className="bg-black text-white">
      <div className="container mx-auto px-4 py-3 flex items-center justify-between">
        <div className="flex items-center space-x-4">
          <div className="w-[66px] h-[23px]">
            <img src="/images/logo/Logo-text.png" alt="NYC Logo" className="w-full h-full" />
          </div>
          <div className="text-sm font-bold text-gray-300">New York City Police Department</div>
        </div>
        <div className="flex items-center space-x-6 text-sm">
          <div className="flex items-center space-x-1 font-bold">
            <span>English</span>
            <svg className="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
              <path fillRule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clipRule="evenodd" />
            </svg>
          </div>
        </div>
      </div>
    </header>
  );
};