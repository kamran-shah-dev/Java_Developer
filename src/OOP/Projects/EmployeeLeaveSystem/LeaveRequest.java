package OOP.Projects.EmployeeLeaveSystem;

import java.util.Date;

public class LeaveRequest {
    private Date startDate;
    private Date endDate;
    private int leaveBalance;
    private Employee employee;
    private String leaveApplicationStatus;

    public LeaveRequest(Date startDate, Date endDate, int leaveBalance, Employee employee, String leaveApplicationStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveBalance = leaveBalance;
        this.employee = employee;
        this.leaveApplicationStatus = leaveApplicationStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getLeaveApplicationStatus() {
        return leaveApplicationStatus;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setLeaveBalance(int leaveBalance) {
        if (leaveBalance < 0) {
            System.out.println("Leave balance cannot be negative.");
        } else {
            this.leaveBalance = leaveBalance;
        }
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setLeaveApplicationStatus(String leaveApplicationStatus) {
        this.leaveApplicationStatus = leaveApplicationStatus;
    }

    public void updateLeaveBalance (Employee employee) {
        if (this.leaveBalance == 0) {
            System.out.println("Leave balance cannot go negative");
        } else {
            this.leaveBalance--;
        }
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", leaveBalance=" + leaveBalance +
                ", employee=" + employee +
                ", leaveApplicationStatus='" + leaveApplicationStatus + '\'' +
                '}';
    }
}
