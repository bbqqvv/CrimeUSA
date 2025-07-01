import Footer from "@/components/layout/admin/footer"
import Header from "@/components/layout/admin/header"


export default function MainLayout({
    children,
}: {
    children: React.ReactNode
}) {
    return (
        <div className="flex flex-col min-h-screen bg-white text-black">
            <Header />
            <main className="flex-grow">{children}</main>
            <Footer />
        </div>
    )
}
