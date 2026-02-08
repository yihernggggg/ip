import java.util.Scanner;

public class Flight {
    private static final String logo = "___________.__  .__       .__     __   \n" +
            "\\_   _____/|  | |__| ____ |  |___/  |_ \n" +
            " |    __)  |  | |  |/ ___\\|  |  \\   __\\\n" +
            " |     \\   |  |_|  / /_/  >   Y  \\  |  \n" +
            " \\___  /   |____/__\\___  /|___|  /__|  \n" +
            "     \\/           /_____/      \\/      ";

    private static final String line = "____________________________________________________________";

    private static Command parseInput(String input) {
        input = input.trim();
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String description = parts.length > 1 ? parts[1] : "";

        return new Command(command, description);
    }

    private static void printGreeting() {
        System.out.println(line + "\n" + " Hello! I'm Flight\n" +
                " What can I do for you?\n" + line);
    }

    private static void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void main(String[] args) {

        printGreeting();

        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();

        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println(line);
            Command command = parseInput(input);

            switch (command.commandType) {
            case "bye":
                printGoodbye();
                scanner.close();
                return;

            case "list":
                tasks.displayTasks();
                break;

            case "mark":
                if (Integer.parseInt(command.description) > 0) {
                    tasks.markTask(Integer.parseInt(command.description) - 1);
                    tasks.displayTasks();
                } else {
                    System.out.println("Invalid mark command. Use: mark <number>");
                }
                break;

            case "unmark":
                if (Integer.parseInt(command.description) > 0) {
                    tasks.unmarkTask(Integer.parseInt(command.description) - 1);
                    tasks.displayTasks();
                } else {
                    System.out.println("Invalid unmark command. Use: unmark <number>");
                }
                break;

            case "todo":
                tasks.addToDo(command.description);
                break;

            case "deadline":
                tasks.addDeadline(command.description);
                break;

            case "event":
                tasks.addEvent(command.description);
                break;

            default:
                break;
            }
            System.out.println(line);
        }

    }
}

