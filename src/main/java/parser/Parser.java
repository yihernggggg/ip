package parser;

import command.Command;

/**
 * Parses raw user input into a structured {@link Command} object.
 * The parser splits the input into a command word and an optional description.
 */
public class Parser {


    /**
     * Parses the given user input string into a {@link Command}.
     * The input is trimmed, then split on the first space into a command word
     * and a description. The command word is converted to lowercase.
     *
     * @param input The raw user input string.
     * @return A {@link Command} containing the parsed command type and description.
     */
    public static Command parseInput(String input) {
        String[] parts = input.trim().split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String description = parts.length > 1 ? parts[1] : "";
        return new Command(commandType, description);
    }
}
