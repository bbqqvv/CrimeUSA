
import { CanHelp } from "@/components/home/can-help";
import { Hero } from "@/components/home/hero";
import { ProgramsResources } from "@/components/home/programs-resources";
import { Separator } from "@radix-ui/react-dropdown-menu";

export default function HomePage() {
    return (
        <main>
            <div className="p-10">
                <Hero />
            </div>
            <CanHelp />
            <Separator className="my-4" />
            <ProgramsResources />
        </main>
    );
}