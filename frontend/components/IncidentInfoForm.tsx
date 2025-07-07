import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from "@/components/ui/select";
import { Textarea } from "@/components/ui/textarea";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { Popover, PopoverTrigger, PopoverContent } from "@/components/ui/popover";
import { Calendar } from "@/components/ui/calendar";
import { format } from "date-fns";

export default function IncidentInfoForm({ form, setForm, date, setDate }: any) {
    const [open, setOpen] = useState(false);

    const handleChange = (e: any) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    return (
        <div className="my-8">
            <div className="flex items-center mb-8">
                <div className="flex-1 border-t border-gray-300" />
                <h2 className="mx-4 font-semibold text-lg sm:text-2xl">Incident Information</h2>
                <div className="flex-1 border-t border-gray-300" />
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-x-10 gap-y-6">
                <div className="space-y-2">
                    <Label htmlFor="typeOfCrime">Type of crime *</Label>
                    <Select onValueChange={(val) => setForm({ ...form, typeOfCrime: val })}>
                        <SelectTrigger className="w-full">
                            <SelectValue placeholder="Select an option" />
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="crimes-against-persons">Crimes Against Persons</SelectItem>
                            <SelectItem value="crimes-against-property">Crimes Against Property</SelectItem>
                            <SelectItem value="white-collar-crimes">White-Collar Crimes</SelectItem>
                            <SelectItem value="cyber-crimes">Cyber Crimes</SelectItem>
                            <SelectItem value="drug-related-crimes">Drug-related Crimes</SelectItem>
                            <SelectItem value="public-order-crimes">Public Order Crimes</SelectItem>
                        </SelectContent>
                    </Select>
                </div>

                <div className="space-y-2">
                    <Label htmlFor="severity">Severity *</Label>
                    <Select onValueChange={(val) => setForm({ ...form, severity: val })}>
                        <SelectTrigger className="w-full">
                            <SelectValue placeholder="Select an option" />
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="minor">Minor</SelectItem>
                            <SelectItem value="moderate">Moderate</SelectItem>
                            <SelectItem value="serious">Serious</SelectItem>
                            <SelectItem value="critical">Critical</SelectItem>
                        </SelectContent>
                    </Select>
                </div>

                <div className="space-y-2">
                    <Label htmlFor="datetime">Datetime of occurrence *</Label>
                    <Popover open={open} onOpenChange={setOpen}>
                        <PopoverTrigger asChild>
                            <Button variant="outline" className="w-full justify-start text-left font-normal">
                                {date ? format(date, "dd/MM/yyyy") : <span className="text-muted-foreground">Choose</span>}
                            </Button>
                        </PopoverTrigger>
                        <PopoverContent align="start" className="p-0">
                            <Calendar mode="single" selected={date} onSelect={setDate} initialFocus />
                        </PopoverContent>
                    </Popover>
                </div>

                <div className="space-y-2">
                    <Label htmlFor="address">Detailed address</Label>
                    <Input name="address" className="w-full" onChange={handleChange} />
                </div>
            </div>
            <div className="mt-6 space-y-2">
                <Label htmlFor="description">Description of the incident</Label>
                <Textarea
                    name="description"
                    placeholder="Briefly describe what happened..."
                    className="w-full"
                    onChange={handleChange}
                />
            </div>
        </div>
    );
}
