package task;

/**
 * Represents an abstract task with a description and completion status.
 * Subclasses include {@link ToDo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of this task.
     *
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }


    /**
     * Returns the done status as a string for file storage.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    public String getIsDone() {
        if (isDone) {
            return "1";
        }
        return "0";
    }

    /**
     * Returns a pipe-delimited string representation of this task for file storage.
     *
     * @return The file-formatted string of this task.
     */
    public abstract String toFileString();

    /**
     * Returns a formatted string showing the task's status icon and description
     * for display to the user.
     *
     * @return The display-formatted string of this task.
     */
    public String currentStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
