import { MessageCircle, Shield, Users } from 'lucide-react';
import React from 'react';
import { Button } from '../ui/button';
import Link from 'next/link';
export const CanHelp = () => {
    return (
        <section className="text-center py-10 px-4 md:px-6 space-y-6 bg-gray-50">
            <h2 className="text-2xl font-bold text-gray-800">How You Can Help?</h2>

            <div className="flex flex-col md:flex-row justify-center items-center gap-8 text-sm">
                <div className="flex flex-col items-center space-y-2 max-w-[200px] text-gray-700">
                    <MessageCircle className="w-8 h-8 text-blue-600" />
                    <p>Tell us what happened.</p>
                </div>

                <div className="flex flex-col items-center space-y-2 max-w-[200px] text-gray-700">
                    <Users className="w-8 h-8 text-blue-600" />
                    <p>Your contribution & our mission.</p>
                </div>

                <div className="flex flex-col items-center space-y-2 max-w-[200px] text-gray-700">
                    <Shield className="w-8 h-8 text-blue-600" />
                    <p>Protect yourself and others.</p>
                </div>
            </div>

            <div>
                <Link href="/reporter">
                    <Button className="mt-4">File A Report</Button>
                </Link>
            </div>
        </section>
    );
};
