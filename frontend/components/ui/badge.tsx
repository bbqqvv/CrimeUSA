// src/components/ui/Badge.tsx
interface BadgeProps {
  status: 'Approved' | 'Pending' | 'Rejected' | 'Not Urgent' | 'Urgent';
}

const Badge = ({ status }: BadgeProps) => {
  const baseClasses = 'px-3 py-1 text-xs font-semibold rounded-full';
  const statusClasses = {
    Approved: 'bg-green-100 text-green-800',
    Pending: 'bg-yellow-100 text-yellow-800',
    Rejected: 'bg-red-100 text-red-800',
    'Not Urgent': 'bg-gray-100 text-gray-800',
    Urgent: 'bg-orange-100 text-orange-800',
  };

  return <span className={`${baseClasses} ${statusClasses[status]}`}>{status}</span>;
};

export default Badge;