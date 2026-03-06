package ui;

import command.Command;
import command.CommandException;
import task.TaskList;
import storage.Storage;
import parser.Parser;

/**
 * Main entry point for the Flight chatbot application.
 * Flight is a CLI task manager that supports creating, managing,
 * and searching ToDo, Deadline, and Event tasks with file persistence.
 */
public class Flight {

    /**
     * Runs the main application loop.
     * Initialises the UI, storage, and task list, then continuously reads
     * and processes user commands until the "bye" command is received.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.printGreeting();
        Storage storage = new Storage("./data/flight.txt");
        TaskList tasks = storage.load(ui);

        while (true) {
            String input = ui.readInput();
            Command command = Parser.parseInput(input);

            switch (command.commandType) {
            case "bye":
                ui.printGoodbye();
                ui.closeUi();
                return;

            case "list":
                ui.printTaskList(tasks);
                break;

            case "mark":
                tasks.markTask(command.description, storage, ui);
                break;

            case "unmark":
                tasks.unmarkTask(command.description, storage, ui);
                break;

            case "todo":
                tasks.addToDo(command.description, storage, ui);
                break;

            case "deadline":
                tasks.addDeadline(command.description, storage, ui);
                break;

            case "event":
                tasks.addEvent(command.description, storage, ui);
                break;

            case "delete":
                tasks.deleteTask(command.description, storage, ui);
                break;

            case "on":
                tasks.findTasksOnDate(command.description, ui);
                break;

            case "find":
                tasks.findTasksWithKeyword(command.description, ui);
                break;

            default:
                CommandException.invalidCommand(ui);
                break;
            }
        }

    }
}

