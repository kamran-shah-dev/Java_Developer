package FileHandling;

import java.io.File;
import java.util.Scanner;

public class FileManagement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 to create a file or \nany other key to exit");
            if (input.nextLine().equals("1")) {
                System.out.print("Enter the path: ");
                String path = input.nextLine();

                System.out.println("Enter file name: ");
                String fileName = input.nextLine();

                File file = new File(path + fileName);

                if (file.exists()) {
                    if (file.isFile()) {
                        System.out.println(fileName + " is a file");
                    } else {
                        System.out.println(fileName + " is a directory");
                    }
                } else {
                    System.out.println(fileName + " is a not a valid file or directory");
                    System.out.println("To create a file with given name press 1\n"
                            + "To create a directory with given name press 2\n"
                            + "To do nothing and continue, press any other key");
                    if (input.nextLine().equals("1")) {

                    } else if (input.nextLine().equals("2")) {
                        boolean created = file.mkdir();
                        if (created) {
                            System.out.println(fileName + " directory created");
                        } else {
                            System.out.println(fileName + " directory could not be created");
                        }
                    }
                }
            } else {
                break;
            }
        }
    }
}
