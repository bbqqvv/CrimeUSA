// src/components/Sidebar.tsx
import { FiHome, FiFileText, FiBriefcase, FiLogOut } from 'react-icons/fi';
import Image from 'next/image';

const Sidebar = () => {
  return (
    <div className="flex flex-col w-64 bg-gray-800 text-white h-screen">
      <div className="flex items-center p-4 border-b border-gray-700">
        <Image src="/images/anh1.png" alt="Avatar" width={40} height={40} className="rounded-full" />
        <span className="ml-3 font-semibold">KIỂM DUYỆT</span>
      </div>
      <nav className="flex-grow p-4">
        <ul>
          <li className="mb-2">
            <a href="#" className="flex items-center p-2 text-gray-300 hover:bg-gray-700 rounded">
              <FiHome className="mr-3" /> Dashboard
            </a>
          </li>
          <li className="mb-2">
            <a href="#" className="flex items-center p-2 bg-blue-500 text-white rounded">
              <FiFileText className="mr-3" /> Reports
            </a>
          </li>
          <li className="mb-2">
            <a href="#" className="flex items-center p-2 text-gray-300 hover:bg-gray-700 rounded">
              <FiBriefcase className="mr-3" /> Cases
            </a>
          </li>
        </ul>
      </nav>
      <div className="p-4 border-t border-gray-700">
        <button className="flex items-center w-full p-2 bg-gray-700 hover:bg-gray-600 rounded">
          <FiLogOut className="mr-3" /> Logout
        </button>
      </div>
    </div>
  );
};

export default Sidebar;