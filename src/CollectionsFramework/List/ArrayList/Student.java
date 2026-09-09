package CollectionsFramework.List.ArrayList;

import java.util.*;

public class Student {
    private final String name;
    private final int age;
    private final String major;

    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }


    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                '}';
    }

    public static void main(String[] s) {
        try {
            Scanner scanner = new Scanner(System.in);
            ArrayList<Student> studentsList = new ArrayList<>();

            label:
            while (true) {
                System.out.println(
                        """
                           \n
                           Press 1 to add a student
                           2 to view all the students
                           3 to remove a student from list
                           4 to sort students based on their age
                           Any other key to exit
                        """);

                String userAction = scanner.nextLine();

                switch (userAction) {
                    case "1":
                        System.out.print("Enter the Student Name: ");
                        String studentName = scanner.nextLine();

                        System.out.print("Enter Student Age: ");
                        int studentAge = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter the Student's Major: ");
                        String studentMajor = scanner.nextLine();

                        studentsList.add(new Student(studentName, studentAge, studentMajor));
                        System.out.println("Student Added to the List");

                        break;
                    case "2":
                        studentsList.forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Enter the index of the student you want to remove ");
                        int rmvIdx = Integer.parseInt(scanner.nextLine());

                        if (rmvIdx > (studentsList.size() - 1)) {
                            System.out.println("There is no such index position in the list");
                        } else {
                            studentsList.remove(rmvIdx);
                            System.out.println("The Student has been removed from the list");
                        }
                        break;
                    case "4":
                        studentsList.sort(Comparator.comparingInt(Student::getAge));
                        break;
                    default:
                        break label;
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
