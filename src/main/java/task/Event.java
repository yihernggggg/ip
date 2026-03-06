package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from.trim());
        this.fromHasTime = from.trim().contains(" ");
        this.to = parseDateTime(to.trim());
        this.toHasTime = to.trim().contains(" ");
    }

    private LocalDateTime parseDateTime(String input) {
        try {
            return LocalDateTime.parse(input, INPUT_WITH_TIME);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(input, INPUT_DATE_ONLY).atStartOfDay();
        }
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public String toFileString() {
        String formattedFrom = fromHasTime ? from.format(SAVE_WITH_TIME) : from.format(SAVE_DATE_ONLY);
        String formattedTo = toHasTime ? to.format(SAVE_WITH_TIME) : to.format(SAVE_DATE_ONLY);
        return "E | " + getIsDone() + " | " + description + " | " + formattedFrom + " | " + formattedTo;
    }

    @Override
    public String currentStatus() {
        String formattedFrom = fromHasTime ? from.format(DISPLAY_WITH_TIME) : from.format(DISPLAY_DATE_ONLY);
        String formattedTo = toHasTime ? to.format(DISPLAY_WITH_TIME) : to.format(DISPLAY_DATE_ONLY);
        return "[E]" + super.currentStatus() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}

