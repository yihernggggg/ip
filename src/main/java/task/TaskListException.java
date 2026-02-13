package task;

public class TaskListException {

    public static void todoInvalidCommand() {
        System.out.println("  Invalid todo input! Please follow this format:\n" +
                            "  todo <description>");
    }

    public static void deadlineInvalidCommand() {
        System.out.println("  Invalid deadline input! Please follow this format:\n" +
                            "  deadline <description> /by <date or time>");
    }

    public static void eventInvalidCommand() {
        System.out.println("  Invalid event input! Please follow this format:\n" +
                            "  event <description> /from <start time> /to <end time>");
    }

    public static void markTaskOutOfBounds(int taskCount) {
        System.out.printf("  Index provided is out of bounds!\n  There are currently only %d tasks.\n", taskCount);
    }
}
