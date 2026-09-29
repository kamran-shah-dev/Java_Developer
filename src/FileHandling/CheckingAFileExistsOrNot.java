package FileHandling;

import java.io.File;

public class CheckingAFileExistsOrNot {
    public static void main(String[] args) {
        String fileName = "text.txt";
        String path = "src/FileHandling/Files/";

        File file = new File(path + fileName);

        if (file.exists()) {
            if (file.isFile()) {
                System.out.println(fileName + " is a file");
            } else {
                System.out.println(fileName + " is a directory");
            }
        } else {
            System.out.println(fileName + " not found");
        }
    }
}
