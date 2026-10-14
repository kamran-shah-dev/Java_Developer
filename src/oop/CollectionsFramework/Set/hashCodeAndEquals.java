package CollectionsFramework.Set;

import java.util.HashMap;
import java.util.Objects;

public class hashCodeAndEquals {
    public static void main(String[] args) {
        Employee e1 = new Employee("Kamran" , 1);
        Employee e2 = new Employee("Adnan" , 2);
        Employee e3 = new Employee("Kamran" , 1);

        HashMap<Employee, String> employeeDesignations = new HashMap<>();
        employeeDesignations.put(e1, "Team Lead");
        employeeDesignations.put(e2, "Reporter");
        employeeDesignations.put(e3, "Manager");
        System.out.println(employeeDesignations);
    }
}

class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}