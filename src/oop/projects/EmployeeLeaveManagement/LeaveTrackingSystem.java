package oop.projects.EmployeeLeaveManagement;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class LeaveTrackingSystem {

    private final HashMap<Integer, Employee> employeeDirectory = new HashMap<>();
    private final HashMap<Integer, LeaveRequest> requestDirectory = new HashMap<>();
    private final ArrayList<LeaveRequest> allLeaveRequests = new ArrayList<>();
    private final HashSet<String> departmentsWithPendingRequests = new HashSet<>();
    private final Queue<LeaveRequest> pendingRequests = new LinkedList<>();

    // Pending Request operations (Queue Operations)

    public LeaveRequest removeFromPendingRequests() {
        if (allLeaveRequests.isEmpty()) {
            return null;
        }
        return pendingRequests.poll();
    }

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

    public void displayEmployees() {
        if (employeeDirectory.isEmpty()) {
            System.out.println("No employees in the directory");
            return;
        }
        for (Employee employee : employeeDirectory.values()) {
            System.out.println(employee);
        }
    }

    // Leave request operations
    public void addLeaveRequest(LeaveRequest request) {
        requestDirectory.put(request.getRequestId(), request);
        allLeaveRequests.add(request);
    }

    public LeaveRequest getLeaveRequest(int id) {
        LeaveRequest e = null;
        if (requestDirectory.containsKey(id)) {
            e = requestDirectory.get(id);
        }
        return e;
    }

    public void displayLeaveRequests() {
        if (requestDirectory.isEmpty()) {
            System.out.println("No leave requests in the directory");
            return;
        }
        for (LeaveRequest request : requestDirectory.values()) {
            System.out.println(request);
        }
    }

    public boolean hasDepartmentPendingRequests (String department) {
        return departmentsWithPendingRequests.contains(department);
    }

    public static void main(String[] args) {
        LeaveTrackingSystem leaveTrackingSystem = new LeaveTrackingSystem();

        File employeeData = new File("src/oop/projects/EmployeeLeaveManagement/files/employees.csv");
        File leaveRequestData = new File("src/oop/projects/EmployeeLeaveManagement/files/leaverequests.csv");

        getDataFromFilesIntoCollection(employeeData, leaveTrackingSystem, true);
        getDataFromFilesIntoCollection(leaveRequestData, leaveTrackingSystem, false);
        // Application
        try (Scanner input = new Scanner(System.in)) {
            boolean isRunning = true;
            while (isRunning) {
                printMenu();
                String option = input.nextLine();
                switch (option) {
                    case "1":
                        System.out.println("=========== Employees ==========");
                        leaveTrackingSystem.displayEmployees();
                        break;
                    case "2":
                        System.out.println("=========== Leave Requests ==========");
                        leaveTrackingSystem.displayLeaveRequests();
                        break;
                    case "3":
                        findEmployee(leaveTrackingSystem, input);
                        break;
                    case "4":
                        findLeaveRequest(leaveTrackingSystem, input);
                        break;
                    case "5":
                        if(addEmployeeFromInput(leaveTrackingSystem, input)) {
                            System.out.println("Employee Added.");
                        } else {
                            System.out.println("Employee Could Not Be Added");
                        }
                        break;
                    case "6":
                        if(removeEmployeeFromInput(leaveTrackingSystem, input)) {
                            System.out.println("Employee Added.");
                        } else {
                            System.out.println("Employee Could Not Be Added");
                        }
                        break;
                    case "7":
                        if(submitLeaveRequest(leaveTrackingSystem, input)) {
                            System.out.println("Leave Requested Submitted. ");
                        } else {
                            System.out.println("Leave Request Could Not Be Submitted");
                        }
                        break;
                    case "8":
                        if(reviewNextPendingRequest(leaveTrackingSystem, input)) {
                            System.out.println("Employee Added.");
                        } else {
                            System.out.println("Employee Could Not Be Added");
                        }
                        break;
                    case "9":
                        checkDepartmentPendingRequests(leaveTrackingSystem, input);
                        break;
                    case "10":
                        leaveTrackingSystem.saveEmployeeData(employeeData);
                        leaveTrackingSystem.saveLeaveRequestData(leaveRequestData);
                        break;
                    case "0":
                        isRunning = false;
                        break;
                }
            }
        }
    }



    private static void printMenu() {
        System.out.println("===== Leave Tracking System =====");
        System.out.println(" 1. Display all employees");
        System.out.println(" 2. Display all leave requests");
        System.out.println(" 3. Find an employee by ID");
        System.out.println(" 4. Find a leave request by ID");
        System.out.println(" 5. Add an employee");
        System.out.println(" 6. Remove an employee");
        System.out.println(" 7. Submit a new leave request");
        System.out.println(" 8. Review the next pending request");
        System.out.println(" 9. Check if a department has pending requests");
        System.out.println("10. Save employee data");
        System.out.println(" 0. Exit");
    }

    // ---------- Menu actions ----------

    private static void findEmployee(LeaveTrackingSystem system, Scanner scanner) {
        int id = readInt(scanner, "Employee ID: ", 0, Integer.MAX_VALUE);
        Employee employee = system.getEmployee(id);
        if (employee == null) {
            System.out.println("No employee with ID " + id + ".");
        } else {
            System.out.println(employee);
        }
    }

    private static void findLeaveRequest(LeaveTrackingSystem system, Scanner scanner) {
        int id = readInt(scanner, "Request ID: ", 0, Integer.MAX_VALUE);
        LeaveRequest request = system.getLeaveRequest(id);
        if (request == null) {
            System.out.println("No leave request with ID " + id + ".");
        } else {
            System.out.println(request);
        }
    }

    /** @return true if an employee was added */
    private static boolean addEmployeeFromInput(LeaveTrackingSystem system, Scanner scanner) {
        int id = readInt(scanner, "Employee ID: ", 0, Integer.MAX_VALUE);
        if (system.getEmployee(id) != null) {
            System.out.println("An employee with ID " + id + " already exists.");
            return false;
        }
        String name = readText(scanner, "Name: ");
        String department = readText(scanner, "Department: ");
        String email = readText(scanner, "Email: ");
        int leaveBalance = readInt(scanner, "Leave balance (days): ", 0, Integer.MAX_VALUE);

        system.addEmployee(new Employee(name, id, department, email, leaveBalance));
        System.out.println("Employee added.");
        return true;
    }

    /** @return true if an employee was removed */
    private static boolean removeEmployeeFromInput(LeaveTrackingSystem system, Scanner scanner) {
        int id = readInt(scanner, "Employee ID to remove: ", 0, Integer.MAX_VALUE);
        if (system.getEmployee(id) == null) {
            System.out.println("No employee with ID " + id + ".");
            return false;
        }
        // Removing an employee who still has requests would leave those requests
        // pointing at someone who is no longer in the directory.
        for (LeaveRequest req : system.allLeaveRequests) {
            if (req.getEmployee() != null && req.getEmployee().getId() == id) {
                System.out.println("Cannot remove: this employee still has leave requests.");
                return false;
            }
        }
        if (system.removeEmployee(id)) {
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee could not be removed.");
        }
        return true;
    }

    /** @return true if a request was submitted */
    private static boolean submitLeaveRequest(LeaveTrackingSystem system, Scanner scanner) {
        int employeeId = readInt(scanner, "Employee ID: ", 0, Integer.MAX_VALUE);
        Employee employee = system.getEmployee(employeeId);
        if (employee == null) {
            System.out.println("No employee with ID " + employeeId + ".");
            return false;
        }

        System.out.println("Leave type: 1) Sick   2) Vacation   3) Maternity");
        int type = readInt(scanner, "Type: ", 1, 3);

        // Same pattern LeaveRequest uses to parse its dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        LocalDate start = readDate(scanner, "Start date (e.g. 10 Jan, 2025): ", formatter);
        LocalDate end = readDate(scanner, "End date   (e.g. 11 Jan, 2025): ", formatter);
        if (end.isBefore(start)) {
            System.out.println("The end date cannot be before the start date.");
            return false;
        }
        // Re-format so every stored date has exactly the same shape
        String startDate = start.format(formatter);
        String endDate = end.format(formatter);

        int requestId = nextRequestId(system);
        LeaveRequest request;
        if (type == 1) {
            boolean certificate = readYesNo(scanner, "Medical certificate provided? (y/n): ");
            request = new SickLeaveRequest(requestId, employee, startDate, endDate, "Pending", certificate);
        } else if (type == 2) {
            int activeWorkDays = readInt(scanner, "Active work days: ", 0, Integer.MAX_VALUE);
            request = new VacationLeaveRequest(requestId, employee, startDate, endDate, "Pending", activeWorkDays);
        } else {
            boolean report = readYesNo(scanner, "Pregnancy/delivery report provided? (y/n): ");
            request = new MaternityLeaveRequest(requestId, employee, startDate, endDate, "Pending", report);
        }

        system.addLeaveRequest(request);
        // Keep the pending queue and department set in step with the new request
        system.pendingRequests.offer(request);
        system.departmentsWithPendingRequests.add(employee.getDepartment());

        System.out.println("Leave request " + requestId + " submitted (Pending).");
        return true;
    }

    /** @return true if the request at the front of the queue was approved or denied */
    private static boolean reviewNextPendingRequest(LeaveTrackingSystem system, Scanner scanner) {
        LeaveRequest request = system.pendingRequests.peek();
        if (request == null) {
            System.out.println("There are no pending requests.");
            return false;
        }

        System.out.println("Next pending request:");
        System.out.println(request);
        System.out.println("1) Approve   2) Deny   3) Skip (send to back of queue)   0) Back");
        int decision = readInt(scanner, "Decision: ", 0, 3);

        if (decision == 0) {
            return false;
        }
        if (decision == 3) {
            // Queue in action: take the head and put it at the back
            system.pendingRequests.offer(system.removeFromPendingRequests());
            System.out.println("Request skipped.");
            return false;
        }

        String reviewer = readText(scanner, "Your name: ");
        boolean applied;
        if (decision == 1) {
            applied = request.approve(reviewer);
        } else {
            String reason = readText(scanner, "Reason for denial: ");
            applied = request.deny(reviewer, reason);
        }

        if (!applied) {
            System.out.println("The decision could not be applied. The request stays pending.");
            return false;
        }

        system.removeFromPendingRequests();
        system.refreshDepartmentsWithPendingRequests();
        System.out.println("Request " + request.getRequestId() + " is now " + request.getStatus() + ".");
        return true;
    }

    private static void checkDepartmentPendingRequests(LeaveTrackingSystem system, Scanner scanner) {
        String typed = readText(scanner, "Department: ");

        // Match the name ignoring case, using the spelling stored on the employees
        String department = null;
        for (Employee employee : system.employeeDirectory.values()) {
            if (employee.getDepartment().equalsIgnoreCase(typed)) {
                department = employee.getDepartment();
                break;
            }
        }
        if (department == null) {
            System.out.println("No department named \"" + typed + "\".");
        } else if (system.hasDepartmentPendingRequests(department)) {
            System.out.println(department + " has pending requests.");
        } else {
            System.out.println(department + " has no pending requests.");
        }
    }

    // ---------- Helpers on the system ----------

    /** Rebuilds the department set from the queue, so it never lists a department with nothing pending. */
    private void refreshDepartmentsWithPendingRequests() {
        departmentsWithPendingRequests.clear();
        for (LeaveRequest req : pendingRequests) {
            departmentsWithPendingRequests.add(req.getEmployee().getDepartment());
        }
    }

    private static int nextRequestId(LeaveTrackingSystem system) {
        int max = 0;
        for (int id : system.requestDirectory.keySet()) {
            max = Math.max(max, id);
        }
        return max + 1;
    }

    // ---------- Input helpers (each one repeats until the input is valid) ----------

    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number.");
            }
        }
    }

    /** Non-empty text with no comma, because values are saved to a comma-separated file. */
    private static String readText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("This cannot be empty.");
            } else if (line.contains(",")) {
                System.out.println("Commas are not allowed (they would break the CSV file).");
            } else {
                return line;
            }
        }
    }

    private static boolean readYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) {
                return true;
            }
            if (line.equals("n") || line.equals("no")) {
                return false;
            }
            System.out.println("Please answer y or n.");
        }
    }

    private static LocalDate readDate(Scanner scanner, String prompt, DateTimeFormatter formatter) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine().trim(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use the format dd MMM, yyyy, for example 10 Jan, 2025.");
            }
        }
    }

    private static void getDataFromFilesIntoCollection(File fileData, LeaveTrackingSystem leaveTrackingSystem, boolean isEmployeeData) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileData))) {
            String data = reader.readLine();
            if (isEmployeeData) {
                while ((data = reader.readLine()) != null) {
                    String[] dataParts = data.split(",");
                    int id = Integer.parseInt(dataParts[0].trim());
                    String name = dataParts[1].trim();
                    String department = dataParts[2].trim();
                    String email = dataParts[3].trim();
                    int leaveBalance = Integer.parseInt(dataParts[4].trim());
                    leaveTrackingSystem.addEmployee(new Employee(name, id, department, email, leaveBalance));
                }
            } else {
                while ((data = reader.readLine()) != null) {
                    String[] dataParts = data.split(",");
                    int reqId = Integer.parseInt(dataParts[0].trim());
                    int empId = Integer.parseInt(dataParts[1].trim());
                    String reqType = dataParts[2].trim();
                    String startDate = dataParts[3].trim().concat(", ").concat(dataParts[4].trim());
                    startDate = startDate.replaceAll("\"" , "");
                    String endDate = dataParts[5].trim().concat(", ").concat(dataParts[6].trim());
                    endDate = endDate.replaceAll("\"" , "");
                    String status = dataParts[7].trim();
                    String proofOrActiveWorkDays = dataParts[8].trim();

                    Employee employee = leaveTrackingSystem.getEmployee(empId);
                    if (reqType.equals("SICK")) {
                        LeaveRequest sickLeaveRequest = new SickLeaveRequest(reqId, employee, startDate, endDate, status, proofOrActiveWorkDays.equals("TRUE"));
                        leaveTrackingSystem.addLeaveRequest(sickLeaveRequest);
                    } else if (reqType.equals("MATERNITY")) {
                        LeaveRequest maternityLeaveRequest = new MaternityLeaveRequest(reqId, employee, startDate, endDate, status, proofOrActiveWorkDays.equals("TRUE"));
                        leaveTrackingSystem.addLeaveRequest(maternityLeaveRequest);
                    } else {
                        LeaveRequest vacationLeaveRequest = new VacationLeaveRequest(reqId, employee, startDate, endDate, status, Integer.parseInt(proofOrActiveWorkDays.trim()));
                        leaveTrackingSystem.addLeaveRequest(vacationLeaveRequest);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found.. Try Again!! " + e.getMessage());
        }
    }

    public void saveEmployeeData(File employeeDataFile) {
        try (FileWriter writer = new FileWriter(employeeDataFile)) {
            // Write header
            writer.write("ID,Name,Department,Email,LeaveBalance\n");

            // Write each employee
            for (Employee employee : employeeDirectory.values()) {
                writer.write(
                        employee.getId() + "," +
                                employee.getName() + "," +
                                employee.getDepartment() + "," +
                                employee.getEmployeeEmail() + "," +
                                employee.getLeaveBalance() + "\n"
                );
            }

            System.out.println("Employee data saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    public void saveLeaveRequestData(File leaveRequestDataFile) {
        try (FileWriter writer = new FileWriter(leaveRequestDataFile)) {
            // Write header
            writer.write("requestId, employeeId, type, startDate, endDate, status, proof/activeWorkDays\n");

            // Write each employee
            for (LeaveRequest request : requestDirectory.values()) {
                String requestTypeFromClassName = request.getClass().getSimpleName();
                String type = requestTypeFromClassName.equals("SickLeaveRequest") ?
                                "SICK" : requestTypeFromClassName.equals("VacationLeaveRequest") ?
                                    "VACATION" : "MATERNITY";
                String extras = request.proofOrActiveWorkDays();

                writer.write(
                        request.getRequestId() + "," +
                                request.getEmployee().getId() + "," +
                                type + "," +
                                "\"" + request.getStartDate() + "\"" + "," +
                                "\"" + request.getEndDate() + "\"" + "," +
                                request.getStatus() + "," +
                                extras + "\n"
                );
            }

            System.out.println("Employee data saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

}
