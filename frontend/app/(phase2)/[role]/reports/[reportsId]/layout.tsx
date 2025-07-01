interface LayoutProps {
  children: React.ReactNode;
}

export default function ReportDetailLayout({ children }: LayoutProps) {
  return (
    <div className="min-h-screen bg-gray-50">
      <div className="container mx-auto  py-8">
        <div className="flex gap-8">
          {children}
        </div>
      </div>
    </div>
  );
}
