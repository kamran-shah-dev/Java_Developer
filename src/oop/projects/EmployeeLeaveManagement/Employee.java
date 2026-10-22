package oop.projects.EmployeeLeaveManagement;

import java.util.ArrayList;

public class Employee {

    private ArrayList<LeaveRequest> leaveHistory = new ArrayList<>();

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
}
