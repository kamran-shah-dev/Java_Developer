package CollectionsFramework.ComparatorAndComparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class CustomComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}

class CustomComparatorString implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o2.length() - o1.length();
    }
}

class Employee {
    private String employeeName;
    private double employeeSalary;

    public Employee(String employeeName, double employeeSalary) {
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeSalary=" + employeeSalary +
                '}';
    }
}

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(0);
        list.add(8);
        list.add(7);
        list.add(6);

        list.sort(new CustomComparator()); // Sort in ascending order
        System.out.println(list);

        // Sort in descending order
        list.sort((a, b) -> b - a); // Using functional interface
        System.out.println(list);

        List<String> wordsList = new ArrayList<>();
        wordsList.add("Absurd");
        wordsList.add("Imbalance");
        wordsList.add("Blunder");

        wordsList.sort(new CustomComparatorString());
        System.out.println(wordsList);

        List<Employee> Employees = new ArrayList<>();
        Employees.add(new Employee("Adnan" , 50000.0));
        Employees.add(new Employee("Bahar" , 40000.0));
        Employees.add(new Employee("Zemal" , 90000.0));
        Employees.add(new Employee("Chahar" , 90000.0));
        Employees.add(new Employee("Dawood" , 100000.0));

//        Employees.sort((o1, o2) -> (int) (o1.getEmployeeSalary() - o2.getEmployeeSalary()));
//        System.out.println(Employees);

        // Another way to do it
        /*Employees.sort((o1, o2) -> {
            if (o1.getEmployeeSalary() - o2.getEmployeeSalary() > 0) {
                return 1;
            }
            if (o1.getEmployeeSalary() - o2.getEmployeeSalary() < 0) {
                return -1;
            }
            else {
                // both are equal either return 0 or compare the second attributes of object
                return o1.getEmployeeName().compareTo(o2.getEmployeeName());
            }
        });*/

        // But java has made it easy. Lets see how
        /*
        Comparator<Employee> employeeComparator =
                Comparator.comparing(Employee::getEmployeeSalary).thenComparing(Employee::getEmployeeName);

        Collections.sort(Employees, employeeComparator);
        System.out.println(Employees);

         */

        // TO sort in reverse order
        Comparator<Employee> employeeComparator =
                Comparator.comparing(Employee::getEmployeeSalary).thenComparing(Employee::getEmployeeName).reversed();

        Collections.sort(Employees, employeeComparator);
        System.out.println(Employees);
    }
}
