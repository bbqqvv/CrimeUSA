import React from "react";

const reports = [
    { id: 1, title: "Monthly Sales", status: "Completed", date: "2025-06-30" },
    { id: 2, title: "User Feedback", status: "Pending", date: "2025-07-01" },
    { id: 3, title: "System Audit", status: "In Progress", date: "2025-06-28" },
];

const ManageReportPage = () => {
    return (
        <main style={{ padding: "2rem" }}>
            <h1>Manage Reports</h1>
            <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "2rem" }}>
                <thead>
                    <tr style={{ background: "#f5f5f5" }}>
                        <th style={{ border: "1px solid #ddd", padding: "0.75rem" }}>ID</th>
                        <th style={{ border: "1px solid #ddd", padding: "0.75rem" }}>Title</th>
                        <th style={{ border: "1px solid #ddd", padding: "0.75rem" }}>Status</th>
                        <th style={{ border: "1px solid #ddd", padding: "0.75rem" }}>Date</th>
                        <th style={{ border: "1px solid #ddd", padding: "0.75rem" }}>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {reports.map((report) => (
                        <tr key={report.id}>
                            <td style={{ border: "1px solid #ddd", padding: "0.75rem", textAlign: "center" }}>{report.id}</td>
                            <td style={{ border: "1px solid #ddd", padding: "0.75rem" }}>{report.title}</td>
                            <td style={{ border: "1px solid #ddd", padding: "0.75rem" }}>{report.status}</td>
                            <td style={{ border: "1px solid #ddd", padding: "0.75rem" }}>{report.date}</td>
                            <td style={{ border: "1px solid #ddd", padding: "0.75rem", textAlign: "center" }}>
                                <button style={{ marginRight: "0.5rem" }}>View</button>
                                <button>Edit</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </main>
    );
};

export default ManageReportPage;