package OOP.Projects.FinalProjectModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoodTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        List<Mood> moodList = new ArrayList<>();

        System.out.println("== Welcome To MoodTrackerApp ==");
        System.out.println("Choose an option from menu to perform a specific tasks");
        while (running) {
            System.out.println("1. To add a mood");
            System.out.println("2. To delete a mood");
            System.out.println("3. To edit mode notes");
            System.out.println("4. To search for a mood");
            System.out.println("5. Exit the app");
            System.out.print("Enter Your Choice: ");

            String option = input.nextLine();

            try {
                switch (option) {
                    case "1" -> {
                        System.out.print("Enter Mood Name: ");
                        String moodName = input.nextLine();

                        System.out.print("Enter Mood Date and Time (format: dd MMM, yyyy  hh:mm:ss): ");
                        LocalDateTime moodDateTime = parseDateTime(input.nextLine());

                        System.out.print("Enter mood notes: ");
                        String notes = getMoodNotes(input);

                        moodList.add(new Mood(moodName, moodDateTime.toLocalDate(), moodDateTime.toLocalTime(), notes));
                    }
//                case "2" -> {
//
//                }
//
//                case "3" -> {
//
//                }
//
//                case "4" -> {
//
//                }

                    case "5" -> {
                        running = false;
                        System.out.println("Thank You For Using Our Trip Planner");
                        System.out.println(moodList);
                    }

                    default -> System.out.println("Invalid Choice!!! Try Again");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error! Invalid Date and Time Format. Please Use this Format: dd MMM, yyyy hh:mm:ss AM/PM");
            }
        }




















//        try (FileWriter fileWriter = new FileWriter("src/OOP/Projects/FinalProjectModule/MoodTracker.txt;");) {
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private static String getMoodNotes(Scanner input) {
        System.out.println("Notes Instruction");
        System.out.println("To add new line press enter..");
        System.out.println("Once your write all notes press enter on a blank line to close note taker..");
        System.out.print("Enter The Notes: ");
        String result = input.nextLine();
        while (true) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            }
            result = result.concat("\n" + line);
        }

        return result;
    }

    private static LocalDateTime parseDateTime(String s) throws DateTimeParseException {
        return LocalDateTime.parse(s, Mood.DATE_TIME_FORMATTER);
    }
}
