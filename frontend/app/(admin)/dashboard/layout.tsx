import Footer from "@/components/layout/admin/footer";
import Header from "@/components/layout/admin/header";
import Sidebar from "@/components/Sidebar";

export default function MainLayout({
    children,
}: {
    children: React.ReactNode;
}) {
    return (
        <div className="flex flex-col min-h-screen bg-white text-black">
            <div className="flex flex-1">
                <Sidebar />
                <main className="flex-grow p-6">{children}</main>
            </div>
        </div>
    );
}
