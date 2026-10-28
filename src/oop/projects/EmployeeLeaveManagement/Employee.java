package oop.projects.EmployeeLeaveManagement;

import java.util.ArrayList;

/**
 * An employee who can submit {@link LeaveRequest}s.
 * <p>
 * Each employee has a contact email, a leave balance, and a personal
 * list of the requests they have submitted (their leave history).
 *
 * @see LeaveRequest
 */
public class Employee {

    /** Full name of the employee. */
    private final String name;

    /** Unique employee id. */
    private final int id;

    /** Department the employee works in. */
    private final String department;

    /** Contact email address of the employee. */
    private final String employeeEmail;

    /** Leave the employee has available, in days. */
    private final int leaveBalance;

    /** All leave requests submitted by this employee, in the order they were added. */
    private final ArrayList<LeaveRequest> leaveHistory = new ArrayList<>();

    /**
     * Creates an employee with an empty leave history.
     *
     * @param name          full name of the employee
     * @param id            unique employee id
     * @param department    department the employee works in
     * @param employeeEmail contact email address
     * @param leaveBalance  leave available to the employee, in days
     */
    public Employee(String name, int id, String department, String employeeEmail, int leaveBalance) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.employeeEmail = employeeEmail;
        this.leaveBalance = leaveBalance;
    }

    /**
     * Returns the employee's name.
     *
     * @return the full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the employee's id.
     *
     * @return the unique employee id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the employee's department.
     *
     * @return the department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the employee's email address.
     *
     * @return the contact email
     */
    public String getEmployeeEmail() {
        return employeeEmail;
    }

    /**
     * Returns the employee's leave balance.
     *
     * @return leave available, in days
     */
    public int getLeaveBalance() {
        return leaveBalance;
    }

    /**
     * Adds a request to this employee's leave history.
     * <p>
     * This is not called automatically when a request is created; the
     * caller is responsible for adding each request.
     *
     * @param request the leave request to record
     */
    public void addHistory(LeaveRequest request) {
        leaveHistory.add(request);
    }

    /**
     * Returns this employee's leave history.
     * <p>
     * Prints a message to standard output when the history is empty.
     * The returned list is the employee's actual internal list, not a copy,
     * so changes made to it affect this employee.
     *
     * @return the list of leave requests, or {@code null} if the history is empty
     */
    public ArrayList<LeaveRequest> getLeaveHistory() {
        ArrayList<LeaveRequest> retriveLeaveHistory = null;
        if (leaveHistory.isEmpty())
            System.out.println("Leave History" + " is Empty");
        else
            retriveLeaveHistory = leaveHistory;
        return retriveLeaveHistory;
    }

    /**
     * Finds one of this employee's leave requests by its id.
     * <p>
     * Prints a message to standard output when the history is empty.
     *
     * @param requestId the id of the request to look for
     * @return the matching request, or {@code null} if no request with that id
     *         exists in this employee's history
     */
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

    /**
     * Prints every request in this employee's leave history to standard
     * output, or a message if the history is empty.
     */
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

    /**
     * Returns a readable summary of this employee: name, id, department and
     * leave history. The email address and leave balance are not included.
     *
     * @return a string describing this employee
     */
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