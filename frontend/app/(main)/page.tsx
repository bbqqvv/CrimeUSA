
import { CanHelp } from "@/components/home/can-help";
import { Hero } from "@/components/home/hero";
import { LogoSection } from "@/components/home/header";
import { ProgramsResources } from "@/components/home/programs-resources";
import { Separator } from "@radix-ui/react-dropdown-menu";

export default function HomePage() {
    return (
        <main>
            <LogoSection />
            <Hero />
            <CanHelp />
            <Separator />
            <ProgramsResources />
        </main>
    );
}