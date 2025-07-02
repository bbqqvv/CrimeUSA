'use client';

import { FiHome, FiFileText, FiBriefcase, FiLogOut } from 'react-icons/fi';
import Image from 'next/image';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import clsx from 'clsx';

const navItems = [
  { label: 'Dashboard', icon: FiHome, href: '/dashboard' },
  { label: 'Reports', icon: FiFileText, href: '/dashboard/reports' },
  { label: 'Cases', icon: FiBriefcase, href: '/dashboard/cases' },
];

const Sidebar = () => {
  const pathname = usePathname();

  return (
    <aside className="flex flex-col w-64 bg-gray-800 text-white h-screen">
      {/* Header */}
      <div className="flex items-center p-4 border-b border-gray-700">
        <Image
          src="/images/anh1.png"
          alt="Avatar"
          width={40}
          height={40}
          className="rounded-full"
        />
        <span className="ml-3 font-semibold text-sm">KIỂM DUYỆT</span>
      </div>

      {/* Navigation */}
      <nav className="flex-grow p-4">
        <ul>
          {navItems.map(({ label, icon: Icon, href }) => (
            <li key={label} className="mb-2">
              <Link
                href={href}
                className={clsx(
                  'flex items-center p-2 rounded transition-all',
                  pathname === href
                    ? 'bg-blue-500 text-white'
                    : 'text-gray-300 hover:bg-gray-700'
                )}
              >
                <Icon className="mr-3" size={18} />
                <span className="text-sm font-medium">{label}</span>
              </Link>
            </li>
          ))}
        </ul>
      </nav>

      {/* Logout */}
      <div className="p-4 border-t border-gray-700">
        <button className="flex items-center w-full p-2 bg-gray-700 hover:bg-gray-600 rounded">
          <FiLogOut className="mr-3" size={18} />
          <span className="text-sm font-medium">Logout</span>
        </button>
      </div>
    </aside>
  );
};

export default Sidebar;
