package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline date (and optional time).
 * Accepts dates in {@code yyyy-MM-dd} or {@code yyyy-MM-dd HHmm} format.
 * Displays dates in {@code MMM dd yyyy} or {@code MMM dd yyyy h:mma} format.
 * File format: {@code D | isDone | description | by}
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private boolean hasTime;
    private static final DateTimeFormatter INPUT_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_DATE_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_WITH_TIME = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    private static final DateTimeFormatter DISPLAY_DATE_ONLY = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter SAVE_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter SAVE_DATE_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a new Deadline task with the given description and deadline string.
     * Attempts to parse the deadline as {@code yyyy-MM-dd HHmm} first, then falls
     * back to {@code yyyy-MM-dd}.
     *
     * @param description The description of the task.
     * @param by          The deadline string in {@code yyyy-MM-dd} or {@code yyyy-MM-dd HHmm} format.
     * @throws DateTimeParseException If the deadline string cannot be parsed in either format.
     */
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

    /**
     * Returns the deadline as a {@link LocalDateTime}.
     *
     * @return The deadline date and time.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns the pipe-delimited string representation for file storage.
     * The date is saved in input format to preserve the original precision.
     *
     * @return The file-formatted string in the format {@code D | isDone | description | by}.
     */
    public String toFileString() {
        String formattedBy = hasTime ? by.format(SAVE_WITH_TIME) : by.format(SAVE_DATE_ONLY);
        return "D | " + getIsDone() + " | " + description + " | " + formattedBy;
    }

    /**
     * Returns the display-formatted string showing the task type, status, and deadline.
     *
     * @return A string in the format {@code [D][status] description (by: formatted date)}.
     */
    @Override
    public String currentStatus() {
        String formattedBy = hasTime ? by.format(DISPLAY_WITH_TIME) : by.format(DISPLAY_DATE_ONLY);
        return "[D]" + super.currentStatus() + " " + "(by: " + formattedBy + ")";
    }

}