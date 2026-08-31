package OOP.AccessSpecifiers;

public class NonAccessSpecifiers {
    public static void main(String[] args) {
        Employee employee1 = new Employee(5);
       // employee1.employeeId = 5; // Error! the value in object once set cannot
        // be changed for final
        // attributes
        // Once set at class level with final keyword
        // cannot be changed then
        // employee1.COMPANY_NAME = "TECH-NEW_WORLD";

        System.out.println("Company Name: " + Employee.COMPANY_NAME);
        System.out.println("Employee ID: " + employee1.employeeId);

    }
}

class Employee {
    // 1. Every employee shares this EXACT same value in memory
    public static final String COMPANY_NAME = "TechCorp";

    // 2. Blank final: Every employee gets their OWN unique, unchangeable ID
    public final int employeeId;

    // Constructor: This is the ONLY place you can assign the blank final
    public Employee(int id) {
        this.employeeId = id;
    }
}
