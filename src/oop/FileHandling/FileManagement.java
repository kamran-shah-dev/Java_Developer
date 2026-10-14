package FileHandling;

import java.io.File;
import java.io.IOException;
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
                    System.out.println("""
                            To create a file with given name press 1
                            To create a directory with given name press 2
                            To do nothing and continue, press any other key""");
                    String choice = input.nextLine();
                    if (choice.equals("1")) {
                        String parentDirectory = file.getParent();

                        File parentDir = new File(parentDirectory);
                        if (!parentDir.exists()) {
                            boolean isDirCreated = parentDir.mkdir();

                            if (!isDirCreated) {
                                System.out.println("Directory could not be created");
                                continue;
                            }
                        }

                        try {
                            boolean isFileCreated = file.createNewFile();
                            if (!isFileCreated) {
                                System.out.println("File could not be created");
                            } else {
                                System.out.println("File created");
                            }
                        } catch (IOException e) {
                            System.out.println("Could not create file" + e.getMessage());
                        }
                    } else if (choice.equals("2")) {
                        boolean isCreated = file.mkdir();

                        if (isCreated) {
                            System.out.println("Directory created");
                        } else {
                            System.out.println("Directory could not be created");
                        }
                    }
                }
            } else {
                break;
            }
        }
    }
}
