package oop.projects.EmployeeLeaveManagement;

import java.util.ArrayList;

public class Employee {
    private String name;
    private int id;
    private String department;
    private ArrayList<LeaveRequest> leaveHistory = new ArrayList<>();

    public Employee(String name, int id, String department) {
        this.name = name;
        this.id = id;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void addHistory(LeaveRequest request) {
        leaveHistory.add(request);
    }

    public ArrayList<LeaveRequest> getLeaveHistory() {
        ArrayList<LeaveRequest> retriveLeaveHistory = null;
        if (leaveHistory.isEmpty())
            System.out.println("Leave History" + " is Empty");
        else
            retriveLeaveHistory = leaveHistory;
        return retriveLeaveHistory;
    }

    public LeaveRequest getLeaveRequestById(int requestId) {
        LeaveRequest returnRequestByID = null;
        if (leaveHistory.isEmpty()) {
            System.out.println("Leave History" + " is Empty");
        }
        else {
            for (LeaveRequest req : leaveHistory) {
                if (req.requestId == requestId) {
                    returnRequestByID = req;
                    break;
                }
            }
        }
        return returnRequestByID;
    }

    public void displayAllLeaveRequest() {
        if (leaveHistory.isEmpty()) {
            System.out.println("Leave History" + " is Empty");
        }
        else {
            for (LeaveRequest req : leaveHistory) {
                System.out.println(req);
            }
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", department='" + department + '\'' +
                ", leaveHistory=" + leaveHistory +
                '}';
    }
}
