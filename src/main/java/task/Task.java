package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getIsDone() {
        if (isDone) {
            return "1";
        }
        return "0";
    }

    public abstract String toFileString();

    public String currentStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
