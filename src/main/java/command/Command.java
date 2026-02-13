package command;

public class Command {
    public String commandType;
    public String description;

    public Command(String commandWord, String description) {
        this.commandType = commandWord;
        this.description = description;
    }
}
