package command;

import ui.Ui;

/**
 * Handles exceptions related to invalid user commands.
 * Provides a static method to display error messages when the user
 * enters an unrecognised command.
 */
public class CommandException {

    /**
     * Displays an error message listing all valid commands when the user
     * enters an unrecognised command.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void invalidCommand(Ui ui) {
        ui.printException("Invalid command! Please refer to the commands below:\n" +
                "  todo -- add a new todo task\n" +
                "  deadline -- add a new task with deadline\n" +
                "  event -- add a new task with a start and end time\n" +
                "  list -- list all added tasks\n" +
                "  mark -- mark a task as done\n" +
                "  unmark -- unmark a task\n" +
                "  on -- search all tasks on a given date\n" +
                "  find -- find a task with a keyword\n" +
                "  bye -- close Flight");
    }
}
