/**
 * LEFT SIDEBAR COMPONENT
 * 
 * A collapsible navigation sidebar for the police report system.
 * Contains three main sections with expandable subsections:
 * 1. Initial Response
 * 2. Scene Information  
 * 3. Initial Investigation Report
 * 
 * FEATURES:
 * - Collapsible sections with smooth animations
 * - Dynamic styling (blue when open, gray when closed)
 * - Hover effects on navigation items
 * - Individual state management for each section
 * - Responsive chevron icon rotation
 * - Clean bordered design with rounded corners
 * 
 * USAGE:
 * This component is typically used in a layout file to provide
 * consistent navigation across all pages in the phase 2 section.
 */

"use client";

import { useState } from "react";
import { useRouter, useParams } from "next/navigation";
import { ChevronDown } from "lucide-react";
import { cn } from "@/lib/utils";
import {
    Collapsible,
    CollapsibleContent,
    CollapsibleTrigger,
} from "@/components/ui/collapsible";

export default function LeftSidebar() {
    const router = useRouter();
    const params = useParams();
    
    // Get role and reportsId from URL parameters
    const role = params.role as string;
    const reportsId = params.reportsId as string;
    
    const [initialResponseOpen, setInitialResponseOpen] = useState(true);
    const [sceneInfoOpen, setSceneInfoOpen] = useState(false);
    const [investigationOpen, setInvestigationOpen] = useState(false);

    const handleNavigation = (path: string) => {
        const fullPath = `/${role}/reports/${reportsId}${path}`;
        router.push(fullPath);
    };

    return (
        <aside className="w-92 bg-white shadow-sm border-b">
            
            {/* INITIAL RESPONSE SECTION */}
            <div className="pt-3 px-3">
                <div className="border border-gray-300 rounded-lg overflow-hidden">
                    <Collapsible
                        open={initialResponseOpen}
                        onOpenChange={setInitialResponseOpen}
                    >
                        <div className="flex">
                            <button
                                onClick={() => handleNavigation('/initial-response')}
                                className={cn(
                                    "flex-1 text-left p-3 transition-all duration-200 hover:bg-blue-200",
                                    initialResponseOpen
                                        ? "bg-blue-100 text-blue-900"
                                        : "bg-gray-200 text-gray-700 hover:bg-gray-300"
                                )}
                            >
                                <span className="font-medium text-sm">Initial Response</span>
                            </button>
                            
                            <CollapsibleTrigger asChild>
                                <button
                                    className={cn(
                                        "px-3 py-3 transition-all duration-200 hover:bg-blue-200 border-l border-gray-300",
                                        initialResponseOpen
                                            ? "bg-blue-100 text-blue-900"
                                            : "bg-gray-200 text-gray-700 hover:bg-gray-300"
                                    )}
                                >
                                    <ChevronDown
                                        size={16}
                                        className={cn(
                                            "transition-transform duration-200",
                                            initialResponseOpen ? "rotate-0" : "-rotate-90"
                                        )}
                                    />
                                </button>
                            </CollapsibleTrigger>
                        </div>
                        
                        <CollapsibleContent className="data-[state=open]:animate-collapsible-down data-[state=closed]:animate-collapsible-up">
                            <div className="border-t border-gray-300 bg-white">
                                <button
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Time of dispatching forces to the scene
                                </button>
                                <button
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Time of arrival at the scene
                                </button>
                                <button
                                    onClick={() => handleNavigation('/initial-response/officers')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    List of officers assigned to the scene
                                </button>
                                <button
                                    onClick={() => handleNavigation('/initial-response/assessment')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Preliminary assessment of the scene situation
                                </button>
                                <button
                                    onClick={() => handleNavigation('/initial-response/preservation')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Scene preservation measures taken
                                </button>
                                <button
                                    onClick={() => handleNavigation('/initial-response/medical-support')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Information on medical/rescue support provided
                                </button>
                            </div>
                        </CollapsibleContent>
                    </Collapsible>
                </div>
            </div>

            {/* SCENE INFORMATION SECTION */}
            <div className="pt-3 px-3">
                <div className="border border-gray-300 rounded-lg overflow-hidden">
                    <Collapsible
                        open={sceneInfoOpen}
                        onOpenChange={setSceneInfoOpen}
                    >
                        <div className="flex">
                            <button
                                onClick={() => handleNavigation('/scene-information')}
                                className={cn(
                                    "flex-1 text-left p-3 transition-all duration-200 hover:bg-blue-200",
                                    sceneInfoOpen
                                        ? "bg-blue-100 text-blue-900"
                                        : "bg-gray-200 text-gray-700 hover:bg-gray-300"
                                )}
                            >
                                <span className="font-medium text-sm">Scene Information</span>
                            </button>
                            <CollapsibleTrigger asChild>
                                <button
                                    className={cn(
                                        "px-3 py-3 transition-all duration-200 hover:bg-blue-200 border-l border-gray-300",
                                        sceneInfoOpen
                                            ? "bg-blue-100 text-blue-900"
                                            : "bg-gray-200 text-gray-700 hover:bg-gray-300"
                                    )}
                                >
                                    <ChevronDown
                                        size={16}
                                        className={cn(
                                            "transition-transform duration-200",
                                            sceneInfoOpen ? "rotate-0" : "-rotate-90"
                                        )}
                                    />
                                </button>
                            </CollapsibleTrigger>
                        </div>
                        <CollapsibleContent className="data-[state=open]:animate-collapsible-down data-[state=closed]:animate-collapsible-up">
                            <div className="border-t border-gray-300 bg-white">
                                <button
                                    onClick={() => handleNavigation('/scene-information/statements')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Initial Statements
                                </button>
                                <button
                                    onClick={() => handleNavigation('/scene-information/statements/details')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Scene Description
                                </button>
                                <button
                                    onClick={() => handleNavigation('/scene-information/media')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Images and Videos
                                </button>
                                <button
                                    onClick={() => handleNavigation('/scene-information/evidence')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Preliminary Physical Evidence Information
                                </button>
                                <button
                                    onClick={() => handleNavigation('/scene-information/sketch')}
                                    className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-900 cursor-pointer transition-colors duration-150"
                                >
                                    Scene Sketch
                                </button>
                            </div>
                        </CollapsibleContent>
                    </Collapsible>
                </div>
            </div>
        </aside>
    );
}