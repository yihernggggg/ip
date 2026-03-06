package task;

import ui.Ui;

/**
 * Handles exceptions related to invalid task list operations.
 * Provides static methods to display specific error messages for
 * invalid commands, out-of-bounds indices, and bad date formats.
 */
public class TaskListException {

    /**
     * Displays an error message for an invalid mark/unmark command.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void invalidMark(Ui ui) {
        ui.printException("  Invalid mark command. Use: mark <index>");
    }

    /**
     * Displays an error message for an invalid delete command.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void invalidDelete(Ui ui) {
        ui.printException("  Invalid delete command. Use: delete <index>");
    }

    /**
     * Displays an error message for an invalid todo command format.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void todoInvalidCommand(Ui ui) {
        ui.printException("  Invalid todo input! Please follow this format:\n" +
                "  todo <description>");
    }

    /**
     * Displays an error message for an invalid deadline command format.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void deadlineInvalidCommand(Ui ui) {
        ui.printException("  Invalid deadline input! Please follow this format:\n" +
                            "  deadline <description> /by <date or time>");
    }

    /**
     * Displays an error message for an invalid event command format.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void eventInvalidCommand(Ui ui) {
        ui.printException("  Invalid event input! Please follow this format:\n" +
                            "  event <description> /from <start time> /to <end time>");
    }

    /**
     * Displays an error message for an invalid date/time format.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void invalidDateTimeInput(Ui ui) {
        ui.printException("  Invalid date format! Please follow this format:\n" +
                            "  yyyy-MM-dd or yyyy-MM-dd HHmm");
    }

    /**
     * Displays an error message for an invalid date format in the "on" command.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void onDateInvalidInput(Ui ui) {
        ui.printException("  Invalid format! Please follow this format:\n" +
                            "  on yyyy-MM-dd");
    }

    /**
     * Displays an error message for an invalid find command format.
     *
     * @param ui The UI instance used to display the error message.
     */
    public static void withKeywordInvalidInput(Ui ui) {
        ui.printException("  Invalid find command! Please follow this format:\n" +
                            "  find <keyword>");
    }

    /**
     * Displays an error message when the provided task index is out of bounds.
     *
     * @param ui The UI instance used to display the error message.
     * @param taskCount The current number of tasks in the list.
     */
    public static void markTaskOutOfBounds(Ui ui, int taskCount) {
        String count = Integer.toString(taskCount);
        ui.printException("  Index provided is out of bounds!\n  There are currently only " + count + " tasks.\n");
    }
}
