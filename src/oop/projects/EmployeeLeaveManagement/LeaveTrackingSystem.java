package oop.projects.EmployeeLeaveManagement;

import java.util.*;

public class LeaveTrackingSystem {

    private HashMap<Integer, Employee> employeeDirectory = new HashMap<>();
    private HashMap<Integer, LeaveRequest> requestDirectory = new HashMap<>();
    private ArrayList<LeaveRequest> allLeaveRequests = new ArrayList<>();
    private HashSet<String> departmentsWithPendingRequests = new HashSet<>();
    private Queue<LeaveRequest> pendingRequests = new LinkedList<>();

    // Pending Request operations (Queue Operations)
    public void updatePendingRequests() {
        if (allLeaveRequests.isEmpty())
            return;
        for (LeaveRequest req : allLeaveRequests) {
            if (req.getStatus().equals("Pending")) {
                pendingRequests.offer(req);
            }
        }
    }

    public LeaveRequest removeFromPendingRequests() {
        if (allLeaveRequests.isEmpty()) {
            return null;
        }
        return pendingRequests.poll();
    }

    // Employee operations
    public void addEmployee(Employee employee) {
        employeeDirectory.put(employee.getId(), employee);
    }

    public Employee getEmployee(int id) {
        Employee e = null;
        if (employeeDirectory.containsKey(id)) {
            e = employeeDirectory.get(id);
        }
        return e;
    }

    public boolean removeEmployee(int id) {
        if (employeeDirectory.containsKey(id)) {
            employeeDirectory.remove(id);
            return true;
        }
        return false;
    }

    // Leave request operations
    public void addLeaveRequest(LeaveRequest request) {
        requestDirectory.put(request.getRequestId(), request);
    }

    public LeaveRequest getLeaveRequest(int id) {
        LeaveRequest e = null;
        if (requestDirectory.containsKey(id)) {
            e = requestDirectory.get(id);
        }
        return e;
    }

    public boolean removeLeaveRequest(int id) {
        if (requestDirectory.containsKey(id)) {
            requestDirectory.remove(id);
            return true;
        }
        return false;
    }

    // Pending Request (Queue) Operations

    public void updateDepartmentsWithPendingRequests () {
        if (allLeaveRequests.isEmpty())
            return;
        for (LeaveRequest req : allLeaveRequests) {
            if (req.getStatus().equals("Pending")) {
                departmentsWithPendingRequests.add(req.getEmployee().getDepartment());
            }
        }
    }

    public boolean hasDepartmentPendingRequests (String department) {
        return departmentsWithPendingRequests.contains(department);
    }

    public static void main(String[] args) {
        LeaveTrackingSystem leaveTrackingSystem = new LeaveTrackingSystem();

                Employee[] emp = {
                        new Employee("Ayesha Khan",   1,  "HR"),
                        new Employee("Bilal Ahmed",   2,  "Engineering"),
                        new Employee("Sana Malik",    3,  "Finance"),
                        new Employee("Usman Tariq",   4,  "Engineering"),
                        new Employee("Fatima Noor",   5,  "Marketing"),
                        new Employee("Hamza Ali",     6,  "Sales"),
                        new Employee("Zainab Raza",   7,  "HR"),
                        new Employee("Imran Sheikh",  8,  "IT"),
                        new Employee("Hira Baloch",   9,  "Finance"),
                        new Employee("Daniyal Shah",  10, "Operations")
                };

                // ---- 15 leave requests (5 sick, 6 vacation, 4 maternity) ----
                ArrayList<LeaveRequest> requests = new ArrayList<>();

                // Sick: (id, employee, start, end, status, medicalCertificateProvided)
                requests.add(new SickLeaveRequest(101, emp[1], "10 Jan, 2025", "11 Jan, 2025", "Pending", false)); // 1 day, ok
                requests.add(new SickLeaveRequest(102, emp[3], "14 Feb, 2025", "18 Feb, 2025", "Pending", false)); // 4 days, no cert -> reject
                requests.add(new SickLeaveRequest(103, emp[5], "03 Mar, 2025", "07 Mar, 2025", "Pending", true));  // 4 days, cert -> ok
                requests.add(new SickLeaveRequest(104, emp[7], "20 Apr, 2025", "21 Apr, 2025", "Pending", false)); // 1 day, ok
                requests.add(new SickLeaveRequest(105, emp[0], "05 May, 2025", "09 May, 2025", "Pending", false)); // 4 days, no cert -> reject

                // Vacation: (id, employee, start, end, status, activeWorkDays)
                requests.add(new VacationLeaveRequest(106, emp[2], "01 Jun, 2025", "15 Jun, 2025", "Pending", 150)); // ok
                requests.add(new VacationLeaveRequest(107, emp[4], "10 Jul, 2025", "20 Jul, 2025", "Pending", 90));  // reject
                requests.add(new VacationLeaveRequest(108, emp[9], "01 Aug, 2025", "10 Aug, 2025", "Pending", 200)); // ok
                requests.add(new VacationLeaveRequest(109, emp[1], "01 Dec, 2025", "10 Dec, 2025", "Pending", 300)); // ok
                requests.add(new VacationLeaveRequest(110, emp[8], "15 Oct, 2025", "25 Oct, 2025", "Pending", 60));  // reject
                requests.add(new VacationLeaveRequest(111, emp[5], "20 Dec, 2025", "31 Dec, 2025", "Pending", 130)); // ok

                // Maternity: (id, employee, start, end, status, reportProvided)
                requests.add(new MaternityLeaveRequest(112, emp[6], "01 Feb, 2025", "30 Apr, 2025", "Pending", true));  // ok
                requests.add(new MaternityLeaveRequest(113, emp[8], "15 Nov, 2025", "15 Dec, 2025", "Pending", false)); // long, no report -> reject
                requests.add(new MaternityLeaveRequest(114, emp[4], "01 Mar, 2025", "03 Mar, 2025", "Pending", false)); // 2 days, ok
                requests.add(new MaternityLeaveRequest(115, emp[2], "01 Oct, 2025", "31 Dec, 2025", "Pending", true));  // ok

                // ---- Process each request ----
                System.out.println("===== PROCESSING =====");
                int approved = 0, rejected = 0;
                for (LeaveRequest req : requests) {
                    System.out.println("\n[Request " + req.getRequestId() + "] "
                            + req.getEmployee().getName() + " - " + req.getClass().getSimpleName());

                    req.getEmployee().addHistory(req);

                    boolean ok = req.processApplication();
                    req.changeStatus(ok ? "Approved" : "Rejected", "System");
                    if (ok) approved++; else rejected++;

                    System.out.println("Result: " + req.getStatus());
                }

                // ---- Per-employee history ----
                System.out.println("\n===== EMPLOYEE LEAVE HISTORY =====");
                for (Employee e : emp) {
                    System.out.println("\n" + e.getName() + " (" + e.getDepartment() + ")");
                    e.displayAllLeaveRequest();
                }

                // ---- Lookup by id ----
                System.out.println("\n===== LOOKUP =====");
                System.out.println(emp[1].getLeaveRequestById(109));
                System.out.println(emp[1].getLeaveRequestById(999)); // null

                // ---- Status history of one request ----
                System.out.println("\n===== STATUS HISTORY (Request 102) =====");
                emp[3].getLeaveRequestById(102).displayStatusHistory();

                // ---- Summary ----
                System.out.println("\n===== SUMMARY =====");
                System.out.println("Total: " + requests.size() + " | Approved: " + approved + " | Rejected: " + rejected);
            }


}
