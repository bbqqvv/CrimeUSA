'use client'
import ImageSlider from "@/components/image-slider";
import Image from "next/image";
import { Button } from "@/components/ui/button";

export default function Home() {

  const sampleImages = [
    {
      src: "/images/1.png",
      alt: "Beautiful landscape with mountains",
      title: "Mountain Vista",
      description: "A breathtaking view of snow-capped mountains during golden hour",
    },
    {
      src: "/images/2.png",
      alt: "Ocean waves at sunset",
      title: "Ocean Sunset",
      description: "Peaceful waves rolling onto the shore as the sun sets",
    },
    {
      src: "/images/3.png",
      alt: "Dense forest with sunlight",
      title: "Forest Path",
      description: "Sunlight filtering through tall trees in a peaceful forest",
    }
  ]

  return (
    <div>
      <div className="container mx-auto px-4 py-10 justify-center items-center">
        <div className="w-full">
          <ImageSlider
            images={sampleImages.slice(0, 3)}
            autoPlay={true}
            showDots={true}
            showArrows={true}
            showPlayPause={false}
            className="max-w-2xl"
          />
        </div>
      </div>
      <div className="w-full text-center mt-10 items-center justify-center">
        <h1 className="font-bold text-4xl">How you can help?</h1>
        <div className="flex mx-auto px-100 justify-center items-center space-x-2">
          <div className="flex justify-center gap-8">
            <div className="flex flex-col items-center justify-between px-4 py-10 h-60 text-center">
              <Image
                src="/images/comment.png"
                alt="comment"
                width={90}
                height={90}
                className="object-contain"
              />
              <p>Tell us what happened.</p>
            </div>

            <div className="flex flex-col items-center justify-between px-4 py-10 h-60 text-center">
              <Image
                src="/images/Group 11.png"
                alt="group"
                width={90}
                height={90}
                className="object-contain"
              />
              <p>Your contribution & our mission</p>
            </div>

            <div className="flex flex-col items-center justify-between px-4 py-10 h-60 text-center">
              <Image
                src="/images/health_and_safety.png"
                alt="safety"
                width={90}
                height={90}
                className="object-contain"
              />
              <p>Protect yourself and others</p>
            </div>
          </div>

        </div>
        <Button className="bg-blue-700 w-75 h-15 text-lg">
          <a href="./report">
            File a report
          </a>
        </Button>
        <div className="w-200 h-px bg-gray-400 mt-10 mx-auto" />
        <div className="w-full text-center mt-10 px-4">
          <h1 className="font-bold text-4xl mb-12">How you can help?</h1>

          <div className="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-8">
            {/* Card 1 */}
            <div className="flex flex-col items-center p-6 bg-white text-center">
              <Image
                src="/images/image (2).png"
                alt="CompStat"
                width={320}
                height={320}
                className="object-contain mb-4"
              />
              <h2 className="text-xl font-semibold mb-2">CompStat & Crime Stats</h2>
              <p className="text-sm text-gray-600">
                Access crime statistics, traffic data, reports, and CompStat 2.0, an advanced digital crime-tracking system that delivers block-by-block data.
              </p>
            </div>

            {/* Card 2 */}
            <div className="flex flex-col items-center p-6 bg-white text-center">
              <Image
                src="/images/image (1).png"
                alt="Body-worn Cameras"
                width={320}
                height={320}
                className="object-contain mb-4"
              />
              <h2 className="text-xl font-semibold mb-2">Body-worn Cameras</h2>
              <p className="text-sm text-gray-600">
                Body-worn cameras have come to the NYPD. What you need to know.
              </p>
            </div>

            {/* Card 3 */}
            <div className="flex flex-col items-center p-6 bg-white text-center">
              <Image
                src="/images/image.png"
                alt="Help is Available"
                width={320}
                height={320}
                className="object-contain mb-4"
              />
              <h2 className="text-xl font-semibold mb-2">Help Is Available</h2>
              <p className="text-sm text-gray-600">
                Before cops can help others, they must first take care of themselves. Help is available.
              </p>
            </div>
          </div>
        </div>

      </div>
    </div>

  );
}
