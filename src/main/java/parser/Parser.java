package parser;

import command.Command;

public class Parser {

    public static Command parseInput(String input) {
        String[] parts = input.trim().split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String description = parts.length > 1 ? parts[1] : "";
        return new Command(commandType, description);
    }
}
