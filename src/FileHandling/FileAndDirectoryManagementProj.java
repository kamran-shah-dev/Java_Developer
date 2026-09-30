package FileHandling;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileAndDirectoryManagementProj {
    public static void FileManagement(File fileInstance) {
        Scanner input = new Scanner(System.in);
        System.out.println("""
                To rename a file press 1
                To delete a file press 2
                Any other key to exit File Management
                """);
        System.out.print("Enter your choice: ");
        String choice = input.nextLine();

        if (choice.equals("1")){
            boolean isDeleted = fileInstance.delete();
            if (isDeleted) {
                System.out.println("file deleted successfully.");
            } else {
                System.out.println("file could not be deleted.");
            }
        } else if (choice.equals("2")) {
            System.out.println("Enter the new name for the file " + fileInstance.getName());
            String newFileName = input.nextLine();
            boolean isRenamed = fileInstance.renameTo(new File(fileInstance.getPath(), newFileName));
            if (isRenamed) {
                System.out.println("file renamed successfully.");
            } else {
                System.out.println("file could not be renamed.");
            }
        } else {
            System.out.println("Exiting file management ...");
        }
    }

    public static void DirectoryManagement(File dirInstance) {
        Scanner input = new Scanner(System.in);
        System.out.println("""
                To rename directory press 1
                To delete directory press 2
                Any other key to exit directory management
                """);
        System.out.print("Enter your choice: ");
        String choice = input.nextLine();

        if (choice.equals("1")){
            boolean isDeleted = dirInstance.delete();
            if (isDeleted) {
                System.out.println("Directory deleted successfully.");
            } else {
                System.out.println("Directory could not be deleted.");
            }
        } else if (choice.equals("2")) {
            System.out.println("Enter the new name for the directory " + dirInstance.getName());
            String newDirectoryName = input.nextLine();

            boolean isRenamed = dirInstance.renameTo(new File(dirInstance.getPath(), newDirectoryName));
            if (isRenamed) {
                System.out.println("directory renamed successfully.");
            } else {
                System.out.println("directory could not be renamed.");
            }
        } else {
            System.out.println("Exiting directory management ...");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("""
                    Press 1 for file and directory management
                    Press any other key to exit
                    """);
            String userChoice = input.nextLine();
            if (userChoice.equals("1")) {
                System.out.print("Enter file name with path: ");
                String fileName = input.nextLine();
                File file = new File(fileName);

                if (file.exists()) {
                    if (file.isFile()) {
                        System.out.println(file.getName() + " is a file. ");
                        System.out.println("Entering file management...");
                        FileManagement(file);
                    } else {
                        System.out.println(file.getName() + " is a directory. ");
                        System.out.println("Entering directory management...");
                        DirectoryManagement(file);
                    }
                } else {
                    System.out.println(fileName + " is a not a valid file or directory");
                    System.out.println("""
                            To create a file with given name press 1
                            To create a directory with given name press 2
                            To do nothing and continue, press any other key""");
                    userChoice = input.nextLine();
                    String parentDirectory = file.getParent();

                    File parentDir = new File(parentDirectory);

                    if (!parentDir.exists()) {
                        System.out.println("Parent directory did not exists...");
                        System.out.println("Creating parent directory...");
                        boolean isParentDirectoryCreated = parentDir.mkdir();
                        if (isParentDirectoryCreated) {
                            System.out.println("Parent Directory created");
                        } else {
                            System.out.println("Parent Directory could not be created");
                        }
                    }
                    if (userChoice.equals("1")) {
                        try {
                            boolean isFileCreated = file.createNewFile();
                            if (isFileCreated) {
                                System.out.println("File created.");
                            } else {
                                System.out.println("File could not be created");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (userChoice.equals("2")) {
                        boolean isDirectoryCreated = file.mkdir();
                        if (isDirectoryCreated) {
                            System.out.println("Directory created");
                        } else {
                            System.out.println("Directory could not be created");
                        }
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
            } else {
                break;
            }
        }
    }
}
