package OOP.Projects.FinalProjectModule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Mood {
    private String moodName;
    private LocalDate date;
    private LocalTime time;
    private String notes;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM, yyyy hh:mm:ss a");

    public Mood(String moodName, LocalDate date, LocalTime time, String notes) {
        this.moodName = moodName;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    public String getMoodName() {
        return moodName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        String dateFormatted = this.date.format(DateTimeFormatter.ofPattern("dd MMM, yyyy"));
        return """
                Mood: %s
                Date: %s
                Time: %s
                Notes:\s
                "%s"
                """.formatted(moodName, dateFormatted, time, notes);
    }
}
