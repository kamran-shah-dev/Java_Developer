package FileHandling;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileAndDirectoryManagementProj {
    public static void FileManagement(File fileInstance) {
        Scanner input = new Scanner(System.in);
        System.out.println("""
                To create a file press 1
                To delete a file press 2
                To rename a file press 3
                """);
        System.out.print("Enter your choice: ");
        String choice = input.nextLine();

        switch (choice) {
            case "1" -> {
                String parentFileName = fileInstance.getParent();

                File parentDir = new File(parentFileName);

                if (!parentDir.exists()) {
                    boolean isParentDirectoryCreated = parentDir.mkdir();
                    if (isParentDirectoryCreated) {
                        try {
                            boolean isFileCreated = parentDir.createNewFile();
                            if (isFileCreated) {
                                System.out.println("File created in parent directory" + parentDir.getName());
                            } else {
                                System.out.println("File could not be created, but parent directory created");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Parent directory could not be created");
                    }
                }
            }
            case "2" -> {
                boolean isDeleted = fileInstance.delete();
                if (isDeleted) {
                    System.out.println("file deleted successfully.");
                } else {
                    System.out.println("file could not be deleted.");
                }
            }
            case "3" -> {
                System.out.println("Enter the new name for the file " + fileInstance.getName());
                String newFileName = input.nextLine();

                boolean isRenamed = fileInstance.renameTo(new File(fileInstance.getPath(), newFileName));
                if (isRenamed) {
                    System.out.println("file renamed successfully.");
                } else {
                    System.out.println("file could not be renamed.");
                }
            }
            default -> System.out.println("Not a valid input");
        }
    }

    public static void DirectoryManagement(File dirInstance) {

    }
}
