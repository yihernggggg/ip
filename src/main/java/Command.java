public class Command {
    String commandType;
    String argument;

    Command(String commandWord, String argument) {
        this.commandType = commandWord;
        this.argument = argument;
    }
}
