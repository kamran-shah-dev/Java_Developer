package oop.projects.EmployeeLeaveManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Contract for anything that can be approved or denied by a reviewer.
 */
interface Approvable {

    /**
     * Approves this item.
     *
     * @param approvedBy name of the person approving
     * @return {@code true} if the approval was applied, {@code false} otherwise
     */
    boolean approve(String approvedBy);

    /**
     * Denies this item.
     *
     * @param deniedBy name of the person denying
     * @param reason   why the item was denied
     * @return {@code true} if the denial was applied, {@code false} otherwise
     */
    boolean deny(String deniedBy, String reason);
}

/**
 * Base class for all leave requests made by an {@link Employee}.
 * <p>
 * Holds the data common to every leave type (id, employee, dates, status)
 * and keeps an audit trail of status changes. Subclasses supply the
 * eligibility rules for their leave type by implementing
 * {@link #processApplication()}.
 * <p>
 * Dates are stored as strings in the format {@code dd MMM, yyyy}
 * (for example {@code 10 Jan, 2025}).
 *
 * @see SickLeaveRequest
 * @see VacationLeaveRequest
 * @see MaternityLeaveRequest
 */
public abstract class LeaveRequest implements Approvable {

    /** Unique identifier of this request. */
    protected int requestId;

    /** The employee who submitted this request. */
    protected Employee employee;

    /** First day of leave, formatted as {@code dd MMM, yyyy}. */
    protected String startDate;

    /** Last day of leave, formatted as {@code dd MMM, yyyy}. */
    protected String endDate;

    /** Current status, such as {@code "Pending"}, {@code "Approved"} or {@code "Rejected"}. */
    protected String status;

    /** Formatter used to parse {@link #startDate} and {@link #endDate}. */
    protected final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM, yyyy");

    /** Pending status for the application. */
    public static final String PENDING = "Pending";

    /** Approved status for the application. */
    public static final String APPROVED = "Approved";

    /** Rejected status for the application. */
    public static final String REJECTED = "Rejected";

    /**
     * Creates an empty request. Fields must be set by the caller or subclass.
     */
    public LeaveRequest() {
    }

    /**
     * Creates a leave request.
     *
     * @param requestId unique id of the request
     * @param employee  the employee requesting leave
     * @param startDate first day of leave, in {@code dd MMM, yyyy} format
     * @param endDate   last day of leave, in {@code dd MMM, yyyy} format
     * @param status    initial status, usually {@code "Pending"}
     */
    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    /**
     * Returns the employee who submitted this request.
     *
     * @return the requesting employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Returns the unique id of this request.
     *
     * @return the request id
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Returns the current status of this request.
     *
     * @return the status string
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the start date of this request.
     *
     * @return the startDate string
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of this request.
     *
     * @return the endDate string
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Approves this request on behalf of a reviewer.
     * <p>
     * Contract: a request that is still pending is moved to the approved
     * state, and the change is recorded in the status history with
     * {@code approvedBy} as the author.
     *
     * @param approvedBy name of the person approving the request
     * @return {@code true} if the request was approved, {@code false} if the
     *         approval could not be applied
     */
    @Override
    public boolean approve(String approvedBy) {
        if (!status.equals(PENDING)) {
            System.out.println("Request " + requestId + " is already " + status);
            return false;
        }
        if (!processApplication()) {
            System.out.println("Request " + requestId + "fails the eligibility criteria");
            return false;
        }
        changeStatus(APPROVED, approvedBy);
        return true;
    }

    /**
     * Denies this request on behalf of a reviewer.
     * <p>
     * Contract: a request that is still pending is moved to the rejected
     * state, and the change is recorded in the status history with
     * {@code deniedBy} as the author.
     *
     * @param deniedBy name of the person denying the request
     * @param reason   why the request was denied
     * @return {@code true} if the request was denied, {@code false} if the
     *         denial could not be applied
     */
    @Override
    public boolean deny(String deniedBy, String reason) {
        if (!status.equals(PENDING)) {
            System.out.println("Request " + requestId + " is already " + status);
            return false;
        }
        System.out.println("Request " + requestId + " is denied by " + deniedBy + " due to " + reason);
        changeStatus(REJECTED, deniedBy);
        return true;
    }

    /**
     * Checks this request against the eligibility rules of its leave type.
     * <p>
     * Implementations may print a message explaining a failure.
     *
     * @return {@code true} if the request meets the rules, {@code false} otherwise
     */
    abstract public boolean processApplication();

    /**
     * Implementations returns the field specific to request type.
     */
    abstract public String proofOrActiveWorkDays();

    /**
     * Calculates the length of the leave in days.
     * <p>
     * The end date is <em>exclusive</em>: a leave from 10 Jan to 11 Jan
     * has a duration of 1.
     *
     * @return number of days between the start and end dates
     * @throws java.time.format.DateTimeParseException if either date does not match
     *         the {@code dd MMM, yyyy} format
     */
    protected int getDuration() {
        LocalDate sDate = LocalDate.parse(startDate, DATE_TIME_FORMATTER);
        LocalDate eDate = LocalDate.parse(endDate, DATE_TIME_FORMATTER);

        return (int) ChronoUnit.DAYS.between(sDate, eDate);
    }

    /**
     * Returns a readable summary of this request.
     *
     * @return a string containing the id, employee, dates and status
     */
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

    /** Audit trail of every status change made to this request. */
    private final ArrayList<StatusChange> statusHistory = new ArrayList<>();

    /**
     * One entry in the audit trail: a single status transition.
     */
    static class StatusChange {
        private final String oldStatus;
        private final String newStatus;
        private final String changeDate;
        private final String changedBy;

        /**
         * Creates a status change record.
         *
         * @param oldStatus  status before the change
         * @param newStatus  status after the change
         * @param changeDate date of the change (ISO format)
         * @param changedBy  who made the change
         */
        public StatusChange(String oldStatus, String newStatus, String changeDate, String changedBy) {
            this.oldStatus = oldStatus;
            this.newStatus = newStatus;
            this.changeDate = changeDate;
            this.changedBy = changedBy;
        }

        /**
         * Returns a readable summary of this change.
         *
         * @return old status, new status, date and author
         */
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

    /**
     * Sets a new status and records the change in the audit trail,
     * stamped with today's date.
     *
     * @param newStatus the status to set
     * @param changeBy  who is making the change
     */
    public void changeStatus(String newStatus, String changeBy) {
        String oldStatus = this.status;
        this.status = newStatus;

        statusHistory.add(new StatusChange(oldStatus, newStatus, LocalDate.now().toString(), changeBy));
    }

    /**
     * Prints every recorded status change to standard output,
     * or a message if there are none.
     */
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

/**
 * A sick leave request.
 * <p>
 * Leave longer than 2 days (by {@link #getDuration()}) requires a medical certificate.
 */
class SickLeaveRequest extends LeaveRequest {

    /** Whether a medical certificate was submitted with the request. */
    protected boolean medicalCertificateProvided;

    /**
     * Creates a sick leave request.
     *
     * @param requestId                  unique id of the request
     * @param employee                   the requesting employee
     * @param startDate                  first day of leave ({@code dd MMM, yyyy})
     * @param endDate                    last day of leave ({@code dd MMM, yyyy})
     * @param status                     initial status
     * @param medicalCertificateProvided {@code true} if a certificate was submitted
     */
    public SickLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status, boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, status);
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    @Override
    public String proofOrActiveWorkDays() {
        return Boolean.toString(medicalCertificateProvided);
    }

    /**
     * Checks the sick leave rule: no certificate means a maximum duration of 2 days.
     *
     * @return {@code false} if there is no certificate and the duration exceeds 2 days;
     *         {@code true} otherwise
     */
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

/**
 * A vacation leave request.
 * <p>
 * The employee must have been active for at least 120 working days.
 */
class VacationLeaveRequest extends LeaveRequest {

    /** Number of days the employee has actively worked. */
    private final int activeWorkDays;

    /**
     * Creates a vacation leave request.
     *
     * @param requestId      unique id of the request
     * @param employee       the requesting employee
     * @param startDate      first day of leave ({@code dd MMM, yyyy})
     * @param endDate        last day of leave ({@code dd MMM, yyyy})
     * @param status         initial status
     * @param activeWorkDays days the employee has actively worked
     */
    public VacationLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status, int activeWorkDays) {
        super(requestId, employee, startDate, endDate, status);
        this.activeWorkDays = activeWorkDays;
    }

    @Override
    public String proofOrActiveWorkDays() {
        return Integer.toString(activeWorkDays);
    }

    /**
     * Returns the active work days of an employee.
     *
     * @return the active work days
     */
    public int getActiveWorkDays() {
        return activeWorkDays;
    }

    /**
     * Checks the vacation rule: at least 120 active work days.
     *
     * @return {@code true} if the employee has met the qualifying period,
     *         {@code false} otherwise
     */
    @Override
    public boolean processApplication() {
        int QUALIFYING_PERIOD = 120;

        if (activeWorkDays < QUALIFYING_PERIOD) {
            System.out.println("You do not quality for vacation leave. You were not regular for 120 days");
            return false;
        }
        return true;
    }
}

/**
 * A maternity leave request.
 * <p>
 * Leave longer than 4 days (by {@link #getDuration()}) requires a pregnancy
 * or delivery report.
 */
class MaternityLeaveRequest extends LeaveRequest {

    /** Whether a pregnancy or delivery report was submitted. */
    private final boolean pregnancyOrDeliveryReportProvided;

    /**
     * Creates a maternity leave request.
     *
     * @param requestId                         unique id of the request
     * @param employee                          the requesting employee
     * @param startDate                         first day of leave ({@code dd MMM, yyyy})
     * @param endDate                           last day of leave ({@code dd MMM, yyyy})
     * @param status                            initial status
     * @param pregnancyOrDeliveryReportProvided {@code true} if a report was submitted
     */
    public MaternityLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String status, boolean pregnancyOrDeliveryReportProvided) {
        super(requestId, employee, startDate, endDate, status);
        this.pregnancyOrDeliveryReportProvided = pregnancyOrDeliveryReportProvided;
    }

    @Override
    public String proofOrActiveWorkDays() {
        return Boolean.toString(pregnancyOrDeliveryReportProvided);
    }

    /**
     * Checks the maternity rule: no report means a maximum duration of 4 days.
     *
     * @return {@code false} if there is no report and the duration exceeds 4 days;
     *         {@code true} otherwise
     */
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