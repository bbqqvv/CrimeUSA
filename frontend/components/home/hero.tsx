"use client"

import useEmblaCarousel from "embla-carousel-react"
import Image from "next/image"
import React from "react"

const images = [
    "/images/banner1.png",
    "/images/banner2.png",
    "/images/banner3.png",
]

export const Hero = () => {
    const [emblaRef] = useEmblaCarousel({ loop: true })

    return (
        <div className="overflow-hidden relative h-[60vh] md:h-[80vh] lg:h-[60vh]">
            <div
                className="flex touch-pan-y h-full"
                ref={emblaRef}
                role="region"
                aria-label="Image Carousel"
            >
                {images.map((img, index) => (
                    <div
                        className="flex-[0_0_100%] relative h-full"
                        key={index}
                    >
                        <Image
                            src={img}
                            alt={`Slide ${index + 1}`}
                            fill
                            className="object-cover"
                            priority={index === 0}
                            sizes="(max-width: 768px) 100vw, 1920px"
                        />
                    </div>
                ))}
            </div>
        </div>
    )
}
