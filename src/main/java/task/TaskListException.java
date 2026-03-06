package task;

import ui.Ui;

public class TaskListException {

    public static void invalidMark(Ui ui) {
        ui.printException("  Invalid mark command. Use: mark <index>");
    }

    public static void invalidDelete(Ui ui) {
        ui.printException("  Invalid delete command. Use: delete <index>");
    }

    public static void todoInvalidCommand(Ui ui) {
        ui.printException("  Invalid todo input! Please follow this format:\n" +
                "  todo <description>");
    }

    public static void deadlineInvalidCommand(Ui ui) {
        ui.printException("  Invalid deadline input! Please follow this format:\n" +
                            "  deadline <description> /by <date or time>");
    }

    public static void eventInvalidCommand(Ui ui) {
        ui.printException("  Invalid event input! Please follow this format:\n" +
                            "  event <description> /from <start time> /to <end time>");
    }

    public static void markTaskOutOfBounds(Ui ui, int taskCount) {
        String count = Integer.toString(taskCount);
        ui.printException("  Index provided is out of bounds!\n  There are currently only " + count + " tasks.\n");
    }
}
