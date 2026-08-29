package OOP.Projects.EmployeeLeaveSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeManagement {
    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

        Employee employee1 = new Employee(123, "Kamran" , "CS" , "emp@hotmail.com");
        Date startDate, endDate;
        try {
            startDate = formatter.parse("08-25-2026");
            endDate = formatter.parse("12-13-2026");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        LeaveRequest leaveRequest = new LeaveRequest(startDate, endDate, 20, employee1, "Pending");

        System.out.println(leaveRequest);
    }
}
