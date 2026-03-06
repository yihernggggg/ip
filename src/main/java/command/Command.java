package command;

/**
 * Represents a parsed user command.
 * A command consists of a command type (e.g., "todo", "deadline") and
 * an optional description containing the remaining user input.
 */
public class Command {
    public String commandType;
    public String description;

    /**
     * Constructs a new Command with the specified command type and description.
     *
     * @param commandWord The type of command (e.g., "todo", "mark", "bye").
     * @param description The remaining input after the command word.
     */
    public Command(String commandWord, String description) {
        this.commandType = commandWord;
        this.description = description;
    }
}
