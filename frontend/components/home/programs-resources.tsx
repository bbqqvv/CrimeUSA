"use client";

import React from "react";
import { Card, CardContent, CardTitle } from "../ui/card";
import Image from "next/image";

export const ProgramsResources = () => {
    const cards = [
        {
            title: "CompStat & Crime Stats",
            image: "/images/crime-stats.png",
            description: "Access crime statistics, traffic data, reports, and CompStat 2.0.",
        },
        {
            title: "Body-worn Cameras",
            image: "/images/crime-stats1.png",
            description: "Body-worn cameras have come to the NYPD. What you need to know.",
        },
        {
            title: "Help is Available",
            image: "/images/crime-stats2.png",
            description: "Before cops can help others, they must take care of themselves.",
        },
    ];

    return (
        <section className="px-4 md:px-6 py-10 bg-gray-50">
            <div className="max-w-screen-xl mx-auto">
                <h2 className="text-2xl md:text-3xl font-bold mb-10 text-center text-gray-800">
                    Programs and Resources
                </h2>

                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8 justify-items-center">
                    {cards.map((card, index) => (
                        <Card
                            key={index}
                            className="w-full max-w-sm bg-white border shadow-sm hover:shadow-md transition-shadow overflow-hidden"
                        >
                            <div className="relative w-full h-60 sm:h-56 md:h-90">
                                <Image
                                    src={card.image}
                                    alt={card.title}
                                    fill
                                    className="object-cover"
                                    sizes="(max-width: 768px) 100vw, 33vw"
                                />
                            </div>
                            <CardContent className="p-4">
                                <CardTitle className="text-blue-800 text-lg mb-2">{card.title}</CardTitle>
                                <p className="text-sm text-gray-600">{card.description}</p>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            </div>
        </section>
    );
};
