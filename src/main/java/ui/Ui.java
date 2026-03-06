package ui;

import java.util.Scanner;
import java.util.ArrayList;

import task.TaskList;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {

    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine().trim();
    }

    public void printGreeting() {
        System.out.println(LINE
                + "\n Hello! I'm Flight"
                + "\n What can I do for you?"
                + "\n" + LINE);
    }

    public void printGoodbye() {
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void printException(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

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

    public void printTaskAdded(TaskList tasks) {
        int taskCount = tasks.getTaskCount();
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + tasks.getTask(taskCount).currentStatus());
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount + 1);
        System.out.println(LINE);
    }

    public void printTaskDeleted(TaskList tasks, int index) {
        int taskCount = tasks.getTaskCount();
        System.out.println(LINE);
        System.out.println(" Got it. I've removed this task:");
        System.out.println("  " + tasks.getTask(index).currentStatus());
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount - 1);
        System.out.println(LINE);
    }

    public void printLoadComplete() {
        System.out.println("  Load complete!");
        System.out.println(LINE);
    }

    public void printNoTaskLoaded() {
        System.out.println("  No tasks saved!");
        System.out.println(LINE);
    }

    public void closeUi() {
        scanner.close();
    }
}
