import java.util.Scanner;

public class Flight {

    private static Command parseInput(String input) {
        input = input.trim();
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        return new Command(command, argument);
    }

    public static void main(String[] args) {
        String logo = "___________.__  .__       .__     __   \n" +
                "\\_   _____/|  | |__| ____ |  |___/  |_ \n" +
                " |    __)  |  | |  |/ ___\\|  |  \\   __\\\n" +
                " |     \\   |  |_|  / /_/  >   Y  \\  |  \n" +
                " \\___  /   |____/__\\___  /|___|  /__|  \n" +
                "     \\/           /_____/      \\/      ";

        String line = "____________________________________________________________";

        String greeting = " Hello! I'm Flight\n" +
                " What can I do for you?\n";

        String goodBye = " Bye. Hope to see you again soon!";

        System.out.println(line + "\n" + greeting + line);

        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();

        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println(line);
            Command command = parseInput(input);

            switch (command.commandType) {
            case "bye":
                System.out.println(goodBye);
                System.out.println(line);
                scanner.close();
                return;

            case "list":
                tasks.displayTasks();
                break;

            case "mark":
                if (Integer.parseInt(command.argument) > 0) {
                    tasks.markTask(Integer.parseInt(command.argument) - 1);
                    tasks.displayTasks();
                } else {
                    System.out.println("Invalid mark command. Use: mark <number>");
                }
                break;

            case "unmark":
                if (Integer.parseInt(command.argument) > 0) {
                    tasks.unmarkTask(Integer.parseInt(command.argument) - 1);
                    tasks.displayTasks();
                } else {
                    System.out.println("Invalid unmark command. Use: unmark <number>");
                }
                break;

            default:
                tasks.addTask(input);
                break;
            }
            System.out.println(line);
        }

    }
}

