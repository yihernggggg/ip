package task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String toFileString() {
        return "T | " + getIsDone() + " | " + description;
    }
    @Override
    public String currentStatus() {
        return "[T]" + super.currentStatus();
    }
}
