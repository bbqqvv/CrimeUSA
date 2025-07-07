    // // src/components/Header.tsx
    // 'use client';

    // import { useState } from 'react';
    // import { FiFilter } from 'react-icons/fi';
    // import { DateRange } from 'react-day-picker';

    // import FilterDropdown from './ui/FilterDropdown';
    // import { DateRangePicker } from './ui/DateRangePicker';

    // // Dữ liệu mẫu cho các dropdown
    // const statusOptions = [
    // { value: 'Approved', color: 'text-green-800', dotColor: 'bg-green-500' },
    // { value: 'Pending',  color: 'text-yellow-800', dotColor: 'bg-yellow-500' },
    // { value: 'Rejected', color: 'text-red-800', dotColor: 'bg-red-500' },
    // ];
    // const crimeTypeOptions = [
    // 'Crimes Against Persons',
    // 'Crimes Against Property',
    // 'White-Collar Crimes',
    // 'Cyber Crimes',
    // 'Drug-related Crimes',
    // 'Public Order Crimes'
    // ];
    // const severityOptions = ['Minor', 'Moderate', 'Severe', 'Critical'];




    // const Header = () => {
    //     // Định nghĩa kiểu cho một lựa chọn. Có thể là chuỗi hoặc đối tượng có màu sắc.
    // type DropdownOption = string | { 
    // value: string; 
    // color?: string;
    // dotColor?: string;
    // };


    //     // State để lưu trữ khoảng ngày được chọn từ DateRangePicker
    //     const [dateRange, setDateRange] = useState<DateRange | undefined>();

    //     // Hàm xử lý cho các dropdown đơn giản
    //     const handleSelect = (filterType: string, value: string) => {
    //         console.log(`Filter selected - ${filterType}: ${value}`);
    //         // Thêm logic filter của bạn ở đây
    //     };

    //     // Khi khoảng ngày thay đổi, bạn cũng có thể xử lý logic ở đây
    //     // Ví dụ: console.log('Selected date range:', dateRange);

    //     return (
    //         <div className="flex items-center justify-between mb-6">
    //             {/* Phần tiêu đề Filter */}
    //             <div className="flex items-center">
    //                 <FiFilter />
    //                 <span className="ml-2 font-medium text-gray-700">Filter</span>
    //             </div>

    //             {/* Phần các bộ lọc */}
    //             <div className="flex items-center">
    //                 <button className="bg-white border border-gray-300 rounded-md px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">
    //                     All
    //                 </button>
                    
    //                 <FilterDropdown 
    //                     label="Status" 
    //                     options={statusOptions} 
    //                     onSelect={(value) => handleSelect('Status', value)} 
    //                 />
    //                 <FilterDropdown 
    //                     label="Crime Type" 
    //                     options={crimeTypeOptions} 
    //                     onSelect={(value) => handleSelect('Crime Type', value)} 
    //                 />
    //                 <FilterDropdown 
    //                     label="Severity" 
    //                     options={severityOptions} 
    //                     onSelect={(value) => handleSelect('Severity', value)} 
    //                 />
                    
    //                 {/* Thay thế Dropdown "Created at" bằng DateRangePicker */}
    //                 <div className="ml-3">
    //                 <DateRangePicker date={dateRange} setDate={setDateRange} />
    //                 </div>
    //             </div>
    //         </div>
    //     );
    // };

    // export default Header;

    // src/components/Header.tsx
'use client';

import { useState } from 'react';
import { FiFilter } from 'react-icons/fi';
import { DateRange } from 'react-day-picker';

import FilterDropdown from './ui/FilterDropdown';
import { DateRangePicker } from './ui/DateRangePicker';

// Dữ liệu cho các bộ lọc
const statusOptions = [
  { value: 'Approved', color: 'text-green-800', dotColor: 'bg-green-500' },
  { value: 'Pending',  color: 'text-yellow-800', dotColor: 'bg-yellow-500' },
  { value: 'Rejected', color: 'text-red-800', dotColor: 'bg-red-500' },
];

const crimeTypeOptions = [
  'Crimes Against Persons',
  'Crimes Against Property',
  'White-Collar Crimes',
  'Cyber Crimes',
  'Drug-related Crimes',
  'Public Order Crimes'
];

const severityOptions = ['Minor', 'Moderate', 'Severe', 'Critical'];

const Header = () => {
    // State để lưu trữ khoảng ngày được chọn
    const [dateRange, setDateRange] = useState<DateRange | undefined>();

    // Hàm xử lý khi chọn một mục trong dropdown
    const handleSelect = (filterType: string, value: string) => {
        console.log(`Filter selected - ${filterType}: ${value}`);
        // Thêm logic filter của bạn ở đây nếu cần
    };

    return (
        <div className="flex items-center justify-between mb-6">
            <div className="flex items-center">
                <FiFilter />
                <span className="ml-2 font-medium text-gray-700">Filter</span>
            </div>

            <div className="flex items-center">
                <button className="bg-white border border-gray-300 rounded-md px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">
                    All
                </button>
                
                <FilterDropdown 
                    label="Status" 
                    options={statusOptions} 
                    onSelect={(value) => handleSelect('Status', value)} 
                />
                <FilterDropdown 
                    label="Crime Type" 
                    options={crimeTypeOptions} 
                    onSelect={(value) => handleSelect('Crime Type', value)} 
                />
                <FilterDropdown 
                    label="Severity" 
                    options={severityOptions} 
                    onSelect={(value) => handleSelect('Severity', value)} 
                />
                
                <div className="ml-3">
                  <DateRangePicker date={dateRange} setDate={setDateRange} />
                </div>
            </div>
        </div>
    );
};

export default Header;