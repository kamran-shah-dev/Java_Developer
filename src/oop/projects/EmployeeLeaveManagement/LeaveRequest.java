package oop.projects.EmployeeLeaveManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

interface Approvable {
    boolean approve(String approvedBy);
    boolean deny(String deniedBy, String reason);
}
public abstract class LeaveRequest implements Approvable {
    protected int requestId;
    protected Employee employee;
    protected String startDate;
    protected String endDate;
    protected String status;

    protected final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM, yyyy");


    public LeaveRequest() {
    }

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    abstract public boolean processApplication();

    protected int getDuration() {
        LocalDate sDate = LocalDate.parse(startDate, DATE_TIME_FORMATTER);
        LocalDate eDate = LocalDate.parse(endDate, DATE_TIME_FORMATTER);
        return (int) ChronoUnit.DAYS.between(sDate, eDate);
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "requestId=" + requestId +
                ", employee=" + employee +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    private ArrayList<StatusChange> statusHistory = new ArrayList<>();

    class StatusChange {
        private String oldStatus;
        private String newStatus;
        private String changeDate;
        private String changedBy;

        public StatusChange(String oldStatus, String newStatus, String changeDate, String changedBy) {
            this.oldStatus = oldStatus;
            this.newStatus = newStatus;
            this.changeDate = changeDate;
            this.changedBy = changedBy;
        }

        @Override
        public String toString() {
            return "StatusChange{" +
                    "oldStatus='" + oldStatus + '\'' +
                    ", newStatus='" + newStatus + '\'' +
                    ", changeDate='" + changeDate + '\'' +
                    ", changedBy='" + changedBy + '\'' +
                    '}';
        }
    }

    public void changeStatus(String newStatus, String changeBy) {
        String oldStatus = this.status;
        this.status = newStatus;

        statusHistory.add(new StatusChange(oldStatus, newStatus, LocalDate.now().toString(), changeBy));
    }

    public void displayStatusHistory() {
        if (statusHistory.isEmpty()) {
            System.out.println("Status Change History is Emptry");
        } else {
            for (StatusChange status : statusHistory) {
                System.out.println(status);
            }
        }
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

        if (!medicalCertificateProvided && getDuration() > 2) {
            System.out.println("Sick Leave cannot be granted for more than two days without a medical certificate");
            return false;
        }
        System.out.println("Processing Sick Leave");
        return true;
    }
}

class VacationLeaveRequest extends LeaveRequest {
    private int activeWorkDays;
    private final int QUALIFYING_PERIOD = 120;

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
        if (activeWorkDays < QUALIFYING_PERIOD) {
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
        if (!pregnancyOrDeliveryReportProvided && getDuration() > 4) {
            System.out.println("Maternity Leave cannot be granted for more than 4 days without a proof submitted");
            return false;
        }
        System.out.println("Processing Maternity Leave");
        return true;
    }
}
