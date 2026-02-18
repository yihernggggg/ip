package task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String currentStatus() {
        return "[T]" + super.currentStatus();
    }
}
