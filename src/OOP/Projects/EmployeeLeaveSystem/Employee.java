package OOP.Projects.EmployeeLeaveSystem;

public class Employee implements Cloneable{
    private String Name;
    private double Salary;
    private int Age;

    // Default constructor
    public Employee() {
        Name = null;
        Salary = 0.0;
        Age = 18;
    }

    // Parameterized Constructor
    public Employee(String Name, double Salary, int Age) {
        this.Name = Name;
        this.Salary = Salary;
        this.Age = Age;
    }

    public String getName() {
        return Name;
    }

    public double getSalary() {
        return Salary;
    }

    public int getAge() {
        return Age;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            System.out.println("Salary cannot be negative or zero.");
        } else {
            Salary = salary;
        }
    }

    public void setAge(int age) {
        if (Age <= 0) {
            System.out.println("Age cannot be negative or zero.");
        } else {
            Age = age;
        }
    }

    public void giveRaise(double percentage) {
        if (Salary == 0) {
            System.out.println("Employee data not set.");
            return;
        }
        this.Salary += (this.Salary * percentage);
        System.out.println("Raise Given to: " + this.Name);
    }

    public double annualSalary() {
        double annualSalary = 0.0;
        if (Salary == 0) {
            System.out.println("Employee data not set.");
        } else {
            annualSalary = Salary * 12;
        }
        return annualSalary;
    }
    public void employeeDetails() {
        System.out.printf("""
                Name: %s
                Monthly Salary: %.4f
                Annual Salary: %.4f
                Age: %d
                %n""", Name, Salary, annualSalary(), Age);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void compareEmployees(Employee other) {
        if (other == null || other.Salary == 0.0) {
            System.out.println("Cannot compare because the second employee lacks details.");
        } else {
            if(this.Salary > other.Salary) {
                System.out.println(this.Name + "'s Salary is greater.");
            } else if (this.Salary < other.Salary) {
                System.out.println(other.Name + "'s Salary is greater.");
            } else {
                System.out.println(this.Name + "'s Salary = " + other.Name +"'s Salary");
            }
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee emp = new Employee("Kamran" , 45000.0000, 24);
        Employee emp1 = new Employee();
        emp.employeeDetails();
        emp1.employeeDetails();

        Employee emp2 = (Employee) emp.clone();
        emp2.employeeDetails();

        System.out.println("Box");
        emp.giveRaise(10.0);
        System.out.println("Box");
        emp1.giveRaise(10.0);
        System.out.println("Box");
        emp.employeeDetails();
        System.out.println("Box");
        emp1.employeeDetails();


        emp2.employeeDetails();

        emp2.compareEmployees(emp);
    }
}
