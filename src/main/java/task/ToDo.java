package task;

/**
 * Represents a ToDo task with only a description and no date/time.
 * File format: {@code T | isDone | description}
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the pipe-delimited string representation for file storage.
     *
     * @return The file-formatted string in the format {@code T | isDone | description}.
     */
    public String toFileString() {
        return "T | " + getIsDone() + " | " + description;
    }

    /**
     * Returns the display-formatted string showing the task type and status.
     *
     * @return A string in the format {@code [T][status] description}.
     */
    @Override
    public String currentStatus() {
        return "[T]" + super.currentStatus();
    }
}
