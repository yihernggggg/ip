public class FlightException {
    public static void FlightInvalidCommand() {
        System.out.println("Invalid command! Please refer to the commands below:");
        System.out.println("  todo -- add a new todo task\n" +
                "  deadline -- add a new task with deadline\n" +
                "  event -- add a new task with a start and end time\n" +
                "  list -- list all added tasks" +
                "  mark -- mark a task as done\n" +
                "  unmark -- unmark a task\n" +
                "  bye -- close Flight"
        );
    }
}
