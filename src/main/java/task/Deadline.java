package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;
    private boolean hasTime;
    private static final DateTimeFormatter INPUT_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_DATE_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_WITH_TIME = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    private static final DateTimeFormatter DISPLAY_DATE_ONLY = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter SAVE_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter SAVE_DATE_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Deadline(String description, String by) {
        super(description);
        String trimmedBy = by.trim();
        try {
            this.by = LocalDateTime.parse(trimmedBy, INPUT_WITH_TIME);
            this.hasTime = true;
        } catch (DateTimeParseException e) {
            this.by = LocalDate.parse(trimmedBy, INPUT_DATE_ONLY).atStartOfDay();
            this.hasTime = false;
        }
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    public String toFileString() {
        String formattedBy = hasTime ? by.format(SAVE_WITH_TIME) : by.format(SAVE_DATE_ONLY);
        return "D | " + getIsDone() + " | " + description + " | " + formattedBy;
    }

    @Override
    public String currentStatus() {
        String formattedBy = hasTime ? by.format(DISPLAY_WITH_TIME) : by.format(DISPLAY_DATE_ONLY);
        return "[D]" + super.currentStatus() + " " + description + " (by: " + formattedBy + ")";
    }

}