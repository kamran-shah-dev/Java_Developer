package oop.projects.EmployeeLeaveManagement;

import java.io.*;
import java.util.*;

public class LeaveTrackingSystem {

    private final HashMap<Integer, Employee> employeeDirectory = new HashMap<>();
    private final HashMap<Integer, LeaveRequest> requestDirectory = new HashMap<>();
    private final ArrayList<LeaveRequest> allLeaveRequests = new ArrayList<>();
    private final HashSet<String> departmentsWithPendingRequests = new HashSet<>();
    private final Queue<LeaveRequest> pendingRequests = new LinkedList<>();

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

    public void displayEmployees() {
        if (employeeDirectory.isEmpty()) {
            System.out.println("No employees in the directory");
            return;
        }
        for (Employee employee : employeeDirectory.values()) {
            System.out.println(employee);
        }
    }

    // Leave request operations
    public void addLeaveRequest(LeaveRequest request) {
        requestDirectory.put(request.getRequestId(), request);
        allLeaveRequests.add(request);
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

    public void displayLeaveRequests() {
        if (requestDirectory.isEmpty()) {
            System.out.println("No leave requests in the directory");
            return;
        }
        for (LeaveRequest request : requestDirectory.values()) {
            System.out.println(request);
        }
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

        File employeeData = new File("src/oop/projects/EmployeeLeaveManagement/files/employees.csv");
        File leaveRequestData = new File("src/oop/projects/EmployeeLeaveManagement/files/leaverequests.csv");

        // Get employee data in the employeeDirectory Hashmap

        getDataFromFilesIntoCollection(employeeData, leaveTrackingSystem, true);
        getDataFromFilesIntoCollection(leaveRequestData, leaveTrackingSystem, false);
        leaveTrackingSystem.displayEmployees();
        leaveTrackingSystem.displayLeaveRequests();
        leaveTrackingSystem.updateDepartmentsWithPendingRequests();
        String department = "HR";
        if(leaveTrackingSystem.hasDepartmentPendingRequests(department)) {
            System.out.println(department + " has pending requests");
        } else {
            System.out.println(department + " has no pending requests");
        }

    }

    private static void getDataFromFilesIntoCollection(File fileData, LeaveTrackingSystem leaveTrackingSystem, boolean isEmployeeData) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileData))) {
            String data = reader.readLine();
            if (isEmployeeData) {
                while ((data = reader.readLine()) != null) {
                    String[] dataParts = data.split(",");
                    int id = Integer.parseInt(dataParts[0].trim());
                    String name = dataParts[1].trim();
                    String department = dataParts[2].trim();
                    leaveTrackingSystem.addEmployee(new Employee(name, id, department));
                }
            } else {
                while ((data = reader.readLine()) != null) {
                    String[] dataParts = data.split(",");
                    int reqId = Integer.parseInt(dataParts[0].trim());
                    int empId = Integer.parseInt(dataParts[1].trim());
                    String reqType = dataParts[2].trim();
                    String startDate = dataParts[3].trim().concat(", ").concat(dataParts[4].trim());
                    startDate = startDate.replaceAll("\"" , "");
                    String endDate = dataParts[5].trim().concat(", ").concat(dataParts[6].trim());
                    endDate = endDate.replaceAll("\"" , "");
                    String status = dataParts[7].trim();
                    String proofOrActiveWorkDays = dataParts[8].trim();

                    Employee employee = leaveTrackingSystem.getEmployee(empId);
                    if (reqType.equals("SICK")) {
                        LeaveRequest sickLeaveRequest = new SickLeaveRequest(reqId, employee, startDate, endDate, status, proofOrActiveWorkDays.equals("TRUE"));
                        leaveTrackingSystem.addLeaveRequest(sickLeaveRequest);
                    } else if (reqType.equals("MATERNITY")) {
                        LeaveRequest maternityLeaveRequest = new MaternityLeaveRequest(reqId, employee, startDate, endDate, status, proofOrActiveWorkDays.equals("TRUE"));
                        leaveTrackingSystem.addLeaveRequest(maternityLeaveRequest);
                    } else {
                        LeaveRequest vacationLeaveRequest = new VacationLeaveRequest(reqId, employee, startDate, endDate, status, Integer.parseInt(proofOrActiveWorkDays.trim()));
                        leaveTrackingSystem.addLeaveRequest(vacationLeaveRequest);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found.. Try Again!! " + e.getMessage());
        }
    }
}
