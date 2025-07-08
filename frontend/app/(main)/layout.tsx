import { Footer } from "@/components/layout/main/Footer"
import { Header } from "@/components/layout/main/Header"


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
