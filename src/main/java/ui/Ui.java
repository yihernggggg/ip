package ui;

import java.util.Scanner;
import java.util.ArrayList;

import task.TaskList;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles all user interface interactions for the Flight chatbot.
 * Responsible for reading user input and displaying formatted output
 * including greetings, task lists, error messages, and search results.
 */
public class Ui {

    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }


    /**
     * Reads and returns the next line of user input, trimmed of leading
     * and trailing whitespace.
     *
     * @return The trimmed user input string.
     */
    public String readInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the welcome greeting.
     */
    public void printGreeting() {
        System.out.println(LINE
                + "\n Hello! I'm Flight"
                + "\n What can I do for you?"
                + "\n" + LINE);
    }

    /**
     * Displays the goodbye message.
     */
    public void printGoodbye() {
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays an exception message.
     *
     * @param message The exception message to display.
     */
    public void printException(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Displays the full list of tasks with their indices and statuses.
     * Shows a message if the list is empty.
     *
     * @param tasks The {@link TaskList} to display.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(LINE);
        int taskCount = tasks.getTaskCount();
        if (taskCount == 0) {
            System.out.println(" Your list is empty!");
        } else {
            System.out.println(" Here are your tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("  " + (i + 1) + ". " + tasks.getTask(i).currentStatus());
            }
        }
        System.out.println(LINE);
    }

    /**
     * Displays all tasks that occur on the specified date.
     * Shows a message if no tasks are found.
     *
     * @param tasks The list of matching tasks.
     * @param date  The date being searched for.
     */
    public void printTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        System.out.println(LINE);
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (tasks.isEmpty()) {
            System.out.println(" No tasks found on " + date.format(displayFormat));
            return;
        }
        System.out.println(" Tasks on " + date.format(displayFormat) + ":");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tasks.get(i).currentStatus());
        }
        System.out.println(LINE);
    }

    /**
     * Displays all tasks whose descriptions match the search keyword.
     * Shows a message if no matching tasks are found.
     *
     * @param tasks The list of matching tasks.
     */
    public void printTasksWithKeyword(ArrayList<Task> tasks) {
        System.out.println(LINE);
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks found!");
            System.out.println(LINE);
            return;
        }
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tasks.get(i).currentStatus());
        }
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after a task is added,
     * showing the newly added task and the updated task count.
     *
     * @param tasks The {@link TaskList} containing the newly added task.
     */
    public void printTaskAdded(TaskList tasks) {
        int taskCount = tasks.getTaskCount();
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + tasks.getTask(taskCount).currentStatus());
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount + 1);
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after a task is deleted,
     * showing the removed task and the updated task count.
     *
     * @param tasks The {@link TaskList} from which the task was deleted.
     * @param index The zero-based index of the deleted task.
     */
    public void printTaskDeleted(TaskList tasks, int index) {
        int taskCount = tasks.getTaskCount();
        System.out.println(LINE);
        System.out.println(" Got it. I've removed this task:");
        System.out.println("  " + tasks.getTask(index).currentStatus());
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount - 1);
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that tasks were successfully loaded from file.
     */
    public void printLoadComplete() {
        System.out.println("  Load complete!");
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that no saved tasks were found.
     */
    public void printNoTaskLoaded() {
        System.out.println("  No tasks saved!");
        System.out.println(LINE);
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void closeUi() {
        scanner.close();
    }
}
