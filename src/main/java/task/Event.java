package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end date/time.
 * Accepts dates in {@code yyyy-MM-dd} or {@code yyyy-MM-dd HHmm} format.
 * Displays dates in {@code MMM dd yyyy} or {@code MMM dd yyyy h:mma} format.
 * File format: {@code E | isDone | description | from | to}
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean fromHasTime;
    private boolean toHasTime;

    private static final DateTimeFormatter INPUT_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_DATE_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_WITH_TIME = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    private static final DateTimeFormatter DISPLAY_DATE_ONLY = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter SAVE_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter SAVE_DATE_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a new Event task with the given description, start, and end date/time.
     * Each date string is parsed as {@code yyyy-MM-dd HHmm} first, falling back
     * to {@code yyyy-MM-dd} if no time component is present.
     *
     * @param description The description of the event.
     * @param from        The start date/time string.
     * @param to          The end date/time string.
     * @throws DateTimeParseException If either date string cannot be parsed in either format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from.trim());
        this.fromHasTime = from.trim().contains(" ");
        this.to = parseDateTime(to.trim());
        this.toHasTime = to.trim().contains(" ");
    }

    /**
     * Parses a date/time string, attempting {@code yyyy-MM-dd HHmm} first,
     * then falling back to {@code yyyy-MM-dd} (set to start of day).
     *
     * @param input The date/time string to parse.
     * @return The parsed {@link LocalDateTime}.
     * @throws DateTimeParseException If the string cannot be parsed in either format.
     */
    private LocalDateTime parseDateTime(String input) {
        try {
            return LocalDateTime.parse(input, INPUT_WITH_TIME);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(input, INPUT_DATE_ONLY).atStartOfDay();
        }
    }

    /**
     * Returns the start date/time of this event.
     *
     * @return The start {@link LocalDateTime}.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of this event.
     *
     * @return The end {@link LocalDateTime}.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the pipe-delimited string representation for file storage.
     *
     * @return The file-formatted string in the format {@code E | isDone | description | from | to}.
     */
    public String toFileString() {
        String formattedFrom = fromHasTime ? from.format(SAVE_WITH_TIME) : from.format(SAVE_DATE_ONLY);
        String formattedTo = toHasTime ? to.format(SAVE_WITH_TIME) : to.format(SAVE_DATE_ONLY);
        return "E | " + getIsDone() + " | " + description + " | " + formattedFrom + " | " + formattedTo;
    }

    /**
     * Returns the display-formatted string showing the task type, status, and date range.
     *
     * @return A string in the format {@code [E][status] description (from: date to: date)}.
     */
    @Override
    public String currentStatus() {
        String formattedFrom = fromHasTime ? from.format(DISPLAY_WITH_TIME) : from.format(DISPLAY_DATE_ONLY);
        String formattedTo = toHasTime ? to.format(DISPLAY_WITH_TIME) : to.format(DISPLAY_DATE_ONLY);
        return "[E]" + super.currentStatus() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}

