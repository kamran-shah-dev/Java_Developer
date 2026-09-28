package FileHandling;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CopyTextFromFileOneToFileTwo {
    public static void main(String[] args) {

        // System.out.println(System.getProperty("user.dir"));
        // To find how java is reading the directory.
        // Currently, it outputs D:\Full Stack\Backend\Java\Java Developer. That means we have to follow up
        // The path after the path we got from user.dir Means we have to provide the next folder
        // src/NextFolder/NextFolder/file.anyfile

        /*
            Scanner fileReader = new Scanner(new FileReader("src/FileHandling/Files/text.txt"));
            while (fileReader.hasNext()) {
                System.out.print(fileReader.next());
            }
            fileReader.close();
        */


        // Read the entire file content as a string
        /*
        Path path = Paths.get("src/FileHandling/Files/text.txt");
        try {
            String content = Files.readString(path);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         */

        Scanner userInput = new Scanner(System.in);
        Scanner fileInput;

        while (true) {
            System.out.println("Enter the file name to read it's content or exit to quit the program");
            System.out.print("FileName or Exit: ");
            String input = userInput.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            String basePath = "src/FileHandling/Files/";

            try {
                fileInput = new Scanner(new FileReader(basePath + input));

                while (fileInput.hasNext()) {
                    System.out.println(fileInput.nextLine());
                }

            } catch (FileNotFoundException e) {
                System.out.println("File Not Found : Error " + e.getMessage());
            }
        }

    }
}
