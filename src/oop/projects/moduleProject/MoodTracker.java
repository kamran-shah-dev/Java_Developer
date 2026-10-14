package oop.projects.moduleProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MoodTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<Mood> moodList = new ArrayList<>();

        System.out.println("== Welcome To MoodTrackerApp ==");
        System.out.println("Choose an option from menu to perform a specific tasks");
        while (true) {
            System.out.println("a. To add a mood");
            System.out.println("d. To delete a mood");
            System.out.println("e. To edit mode notes");
            System.out.println("s. To search for a mood");
            System.out.println("w. To write to a file");
            System.out.println("Exit. To exit the app");
            System.out.print("Enter Your Choice: ");

            String option = input.nextLine();

            if (option.equalsIgnoreCase("exit")) {
                System.out.println("Thank You For Using Our Trip Planner");
                break;
            }

            String moodName;
            LocalDateTime moodDateTime;

            try {
                switch (option) {
                    case "a" -> {
                        System.out.print("Enter Mood Name: ");
                        moodName = input.nextLine();

                        System.out.print("Enter Mood Date and Time (format: dd MMM, yyyy  hh:mm:ss): ");
                        moodDateTime = parseDateTime(input.nextLine());

                        System.out.print("Enter mood notes: ");
                        String notes = getMoodNotes(input);

                        Mood mood = new Mood(moodName, moodDateTime.toLocalDate(), moodDateTime.toLocalTime(), notes);
                        if (!moodList.contains(mood)) {
                            moodList.add(new Mood(moodName, moodDateTime.toLocalDate(), moodDateTime.toLocalTime(), notes));
                        } else {
                            System.out.println("Two moods cannot be at the same date and same time. But can be at same date with different time");
                        }
                    }
                    case "d" -> {
                        if (!moodList.isEmpty()) {
                            System.out.println("To delete by date only press 1 \nTo delete by name" +
                                    "date and time press 2");
                            System.out.print("Enter your choice: ");
                            option = input.nextLine();
                            if (option.equals("1")) {
                                System.out.print("Enter the date you want to delete all moods for: ");
                                LocalDate date = parseDate(input.nextLine());
                                moodList.removeIf(mood -> mood.getDate().equals(date));
                                // The above line is a function of collections that uses a predicate to remove
                                // All elements from a set based on a condition
                            } else if (option.equals("2")) {
                                System.out.print("Enter Mood Name: ");
                                moodName = input.nextLine();

                                System.out.print("Enter Mood Date and Time (format: dd MMM, yyyy  hh:mm:ss): ");
                                moodDateTime = parseDateTime(input.nextLine());
                                moodList.removeIf(mood -> mood.equals(new Mood(moodDateTime.toLocalDate(), moodDateTime.toLocalTime()))
                                        && mood.getMoodName().equalsIgnoreCase(moodName));
                            } else {
                                System.out.println("Invalid Input!!! Try Again...");
                            }
                        } else {
                            System.out.println("List is empty!! Try adding some moods..");
                        }
                    }

                    case "e" -> {
                        if (!moodList.isEmpty()) {
                            System.out.print("Enter Mood Date and Time (format: dd MMM, yyyy  hh:mm:ss): ");
                            moodDateTime = parseDateTime(input.nextLine());

                            for (Mood mood : moodList) {
                                if (mood.getDate().equals(moodDateTime.toLocalDate())
                                        && mood.getTime().equals(moodDateTime.toLocalTime())) {
                                    String notes = mood.getNotes();
                                    if (notes.isBlank()) {
                                        System.out.println("No notes were added. Add notes!");
                                        mood.setNotes(getMoodNotes(input));
                                    } else {
                                        mood.setNotes(editNotes(input, notes));
                                    }
                                    break;
                                }
                            }
                        }  else {
                            System.out.println("List is empty!! Try adding some moods..");
                        }
                    }

                    case "s" -> {
                        if (!moodList.isEmpty()) {
                            System.out.println("To search moods by date only press 1 \nTo search mood by name" +
                                    "date and time press 2");
                            System.out.print("Enter your choice: ");
                            option = input.nextLine();
                            if (option.equals("1")) {
                                System.out.print("Enter the date you want to search all moods for: ");
                                LocalDate date = parseDate(input.nextLine());
                                int moodCount = 0;
                                for (Mood mood : moodList) {
                                    if (mood.getDate().equals(date)) {
                                        System.out.println(mood);
                                        moodCount++;
                                    }
                                }
                                if (moodCount == 0)
                                    System.out.println("No moods exists for date: " + date);
                            } else if (option.equals("2")) {
                                System.out.print("Enter Mood Name: ");
                                moodName = input.nextLine();

                                System.out.print("Enter Mood Date and Time (format: dd MMM, yyyy  hh:mm:ss): ");
                                moodDateTime = parseDateTime(input.nextLine());

                                Mood searchedMood = null;
                                for (Mood mood : moodList) {
                                    if (mood.equals(new Mood(moodDateTime.toLocalDate(), moodDateTime.toLocalTime()))
                                            && mood.getMoodName().equalsIgnoreCase(moodName)) {
                                        searchedMood = mood;
                                        break;
                                    }
                                }
                                if (searchedMood != null) {
                                    System.out.println("Found the mood");
                                    System.out.println(searchedMood);
                                } else {
                                    System.out.println("Mood does not exists");
                                }
                            } else {
                                System.out.println("Invalid Input!!! Try Again...");
                            }
                        }  else {
                            System.out.println("List is empty!! Try adding some moods..");
                        }
                    }

                    case "w" -> {
                        if (!moodList.isEmpty()) {
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/oop/projects/moduleProject/MoodTracker.txt"))) {
                                for (Mood mood : moodList) {
                                    writer.write(mood.toString());
                                    writer.newLine();
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            System.out.println("List is empty!! Try adding some moods..");
                        }
                    }
                    default -> System.out.println("Invalid Choice!!! Try Again");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error! Invalid Date and Time Format. Please Use this Format: dd MMM, yyyy hh:mm:ss AM/PM");
            }
        }

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

    public static String editNotes (Scanner input, String notes) {
        String updatedNotes;
        do {
            System.out.println(notes);
            System.out.print("What word or sentence you want to replace: ");
            String oldContent = input.nextLine();
            System.out.print("Enter the new word or sentence: ");
            String newContent = input.nextLine();
            updatedNotes = notes.replace(oldContent, newContent);
            System.out.print("Do you want to update another word or sentence (y/n): ");
        } while (!input.nextLine().equalsIgnoreCase("n"));
        return updatedNotes;
    }

    private static LocalDateTime parseDateTime(String s) throws DateTimeParseException {
        return LocalDateTime.parse(s, Mood.DATE_TIME_FORMATTER);
    }

    private static LocalDate parseDate(String s) throws DateTimeParseException {

        return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd MMM, yyyy"));
    }

}
