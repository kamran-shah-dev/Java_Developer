package oop.projects.EmployeeLeaveManagement;

import java.util.*;

public class LeaveTrackingSystem {

    private HashMap<Integer, Employee> employeeDirectory = new HashMap<>();
    private HashMap<Integer, LeaveRequest> requestDirectory = new HashMap<>();
    private ArrayList<LeaveRequest> allLeaveRequests = new ArrayList<>();
    private HashSet<String> departmentsWithPendingRequests = new HashSet<>();
    private Queue<LeaveRequest> pendingRequests = new LinkedList<>();

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

}
