package OOP.Projects.EmployeeLeaveSystem;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeeDepartment;
    private String employeeEmail;

    // Constructor
    public Employee(int employeeId, String employeeName, String employeeDepartment, String employeeEmail) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDepartment = employeeDepartment;
        this.employeeEmail = employeeEmail;
    }

    // Getter methods
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    // Setter methods
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    @Override
    public String toString() {
        return """
                Employee {
                    "employeeId="  %d,
                    "employeeName=" %s,
                    "employeeDepartment=" %s,
                    "employeeEmail=" %s
                }""".formatted(employeeId, employeeName, employeeDepartment, employeeEmail);
    }
}
