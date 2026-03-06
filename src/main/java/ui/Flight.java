package ui;

import command.Command;
import command.CommandException;
import task.TaskList;
import storage.Storage;
import parser.Parser;

public class Flight {

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

            default:
                CommandException.invalidCommand(ui);
                break;
            }
        }

    }
}

