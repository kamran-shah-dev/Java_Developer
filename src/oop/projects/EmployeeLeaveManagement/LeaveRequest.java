package oop.projects.EmployeeLeaveManagement;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

interface Approvable {
    public boolean approve(String approvedBy);
    boolean deny(String deniedBy, String reason);
}
public abstract class LeaveRequest implements Approvable {
    protected int requestId;
    protected Employee employee;
    protected String startDate;
    protected String endDate;
    protected String status;

    private final int QUALIFYING_PERIOD = 120;


    public LeaveRequest() {
    }

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    abstract public boolean processApplication();

    protected int getDuration(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public int getQUALIFYING_PERIOD() {
        return QUALIFYING_PERIOD;
    }
}

class SickLeaveRequest extends LeaveRequest {
    protected boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status, boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, status);
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    @Override
    public boolean approve(String approvedBy) {
        return false;
    }

    @Override
    public boolean deny(String deniedBy, String reason) {
        return false;
    }

    @Override
    public boolean processApplication() {

        if (!medicalCertificateProvided && getDuration(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dddd MMM, yyyy")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dddd MMM, yyyy"))) > 2) {
            System.out.println("Sick Leave cannot be granted for more than two days without a medical certificate");
            return false;
        }
        System.out.println("Processing Sick Leave");
        return true;
    }
}

class VacationLeaveRequest extends LeaveRequest {
    private int activeWorkDays;

    public VacationLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status, int activeWorkDays) {
        super(requestId, employee, startDate, endDate, status);
        this.activeWorkDays = activeWorkDays;
    }

    @Override
    public boolean approve(String approvedBy) {
        return false;
    }

    @Override
    public boolean deny(String deniedBy, String reason) {
        return false;
    }

    @Override
    public boolean processApplication() {
        if (activeWorkDays < getQUALIFYING_PERIOD()) {
            System.out.println("You do not quality for vacation leave. You were not regular for 120 days");
            return false;
        }
        return true;
    }
}

class MaternityLeaveRequest extends LeaveRequest {

    private boolean pregnancyOrDeliveryReportProvided;

    public MaternityLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status, boolean pregnancyOrDeliveryReportProvided) {
        super(requestId, employee, startDate, endDate, status);
        this.pregnancyOrDeliveryReportProvided = pregnancyOrDeliveryReportProvided;
    }

    @Override
    public boolean approve(String approvedBy) {
        return false;
    }

    @Override
    public boolean deny(String deniedBy, String reason) {
        return false;
    }

    @Override
    public boolean processApplication() {
        if (!pregnancyOrDeliveryReportProvided && getDuration(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dddd MMM, yyyy")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dddd MMM, yyyy"))) > 4) {
            System.out.println("Maternity Leave cannot be granted for more than 4 days without a proof submitted");
            return false;
        }
        System.out.println("Processing Maternity Leave");
        return true;
    }
}
