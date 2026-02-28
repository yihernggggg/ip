package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toFileString() {
        return "D | " + getIsDone() + " | " + description + " | " + by;
    }

    @Override
    public String currentStatus() {
        return "[D]" + super.currentStatus() + " (by: " + by + ")";
    }
}