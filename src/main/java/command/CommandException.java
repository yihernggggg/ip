package command;

import ui.Ui;

public class CommandException {
    public static void invalidCommand(Ui ui) {
        ui.printException("Invalid command! Please refer to the commands below:\n" +
                "  todo -- add a new todo task\n" +
                "  deadline -- add a new task with deadline\n" +
                "  event -- add a new task with a start and end time\n" +
                "  list -- list all added tasks" +
                "  mark -- mark a task as done\n" +
                "  unmark -- unmark a task\n" +
                "  bye -- close Flight");
    }
}
