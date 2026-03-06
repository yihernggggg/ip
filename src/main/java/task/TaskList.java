package task;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;

import storage.Storage;
import ui.Ui;

/**
 * Represents a list of tasks and provides operations to add, delete,
 * mark, unmark, and search tasks. Also handles saving changes to storage.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return The task count.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param taskIndex The zero-based index of the task.
     * @return The {@link Task} at the given index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Finds and displays all tasks that fall on the specified date.
     * For {@link Deadline} tasks, checks if the deadline matches the date.
     * For {@link Event} tasks, checks if the date falls within the event's date range (inclusive).
     *
     * @param description The date string in {@code yyyy-MM-dd} format.
     * @param ui The UI instance used to display results or error messages.
     */
    public void findTasksOnDate(String description, Ui ui) {
        try {
            LocalDate date = LocalDate.parse(description.trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ArrayList<Task> result = new ArrayList<>();
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.getBy().toLocalDate().equals(date)) {
                        result.add(task);
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (!date.isBefore(event.getFrom().toLocalDate())
                            && !date.isAfter(event.getTo().toLocalDate())) {
                        result.add(task);
                    }
                }
            }
            ui.printTasksOnDate(result, date);
        } catch (DateTimeParseException e) {
            TaskListException.onDateInvalidInput(ui);
        }
    }

    /**
     * Finds and displays all tasks whose descriptions contain the given keyword.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for.
     * @param ui The UI instance used to display results or error messages.
     */
    public void findTasksWithKeyword(String keyword, Ui ui) {
        if (keyword.trim().isEmpty()) {
            TaskListException.withKeywordInvalidInput(ui);
            return;
        }
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        ui.printTasksWithKeyword(result);
    }

    /**
     * Marks the task at the specified index as done and saves the updated list.
     * Validates that the description is a valid numeric index within bounds.
     *
     * @param description The one-based index of the task to mark, as a string.
     * @param storage The storage instance used to save the updated task list.
     * @param ui The UI instance used to display the updated list or error messages.
     */
    public void markTask(String description, Storage storage, Ui ui) {
        if (description.trim().isEmpty() || !description.matches("\\d+")) {
            TaskListException.invalidMark(ui);
            return;
        }
        int index = Integer.parseInt(description) - 1;
        if (index >= 0 && index < taskCount) {
            tasks.get(index).markAsDone();
            storage.save(tasks, taskCount, ui);
            ui.printTaskList(this);
        } else {
            TaskListException.markTaskOutOfBounds(ui, taskCount);
        }
    }

    /**
     * Marks the task at the specified index as not done and saves the updated list.
     * Validates that the description is a valid numeric index within bounds.
     *
     * @param description The one-based index of the task to unmark, as a string.
     * @param storage The storage instance used to save the updated task list.
     * @param ui The UI instance used to display the updated list or error messages.
     */
    public void unmarkTask(String description, Storage storage, Ui ui) {
        if (description.trim().isEmpty() || !description.matches("\\d+")) {
            TaskListException.invalidMark(ui);
            return;
        }
        int index = Integer.parseInt(description) - 1;
        if (index >= 0 && index < taskCount) {
            tasks.get(index).markAsNotDone();
            storage.save(tasks, taskCount, ui);
            ui.printTaskList(this);
        } else {
            TaskListException.markTaskOutOfBounds(ui, taskCount);
        }
    }

    /**
     * Adds a new {@link ToDo} task to the list.
     * Validates that the description is not empty.
     *
     * @param description The description of the ToDo task.
     * @param storage The storage instance used to save the updated task list.
     * @param ui The UI instance used to display confirmation or error messages.
     */
    public void addToDo(String description, Storage storage, Ui ui) {
        if (description.trim().isEmpty()) {
            TaskListException.todoInvalidCommand(ui);
            return;
        }

        tasks.add(new ToDo(description));
        ui.printTaskAdded(this);
        taskCount++;
        storage.save(tasks, taskCount, ui);
    }

    /**
     * Adds a new {@link Deadline} task to the list.
     * Parses the description to extract the task name and deadline date.
     * Validates the format and date before adding.
     *
     * @param description The full description containing the task and {@code /by} date.
     * @param storage The storage instance used to save the updated task list.
     * @param ui The UI instance used to display confirmation or error messages.
     */
    public void addDeadline(String description, Storage storage, Ui ui) {
        String[] parts = description.split(" /by ");
        if (parts.length == 1) {
            TaskListException.deadlineInvalidCommand(ui);
            return;
        }
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            TaskListException.deadlineInvalidCommand(ui);
            return;
        }
        try {
            tasks.add(new Deadline(parts[0], parts[1]));
            ui.printTaskAdded(this);
            taskCount++;
            storage.save(tasks, taskCount, ui);
        } catch (DateTimeParseException e) {
            TaskListException.invalidDateTimeInput(ui);
        }
    }

    /**
     * Adds a new {@link Event} task to the list.
     * Parses the description to extract the task name, start date, and end date.
     * Validates the format and dates before adding.
     *
     * @param description The full description containing the task, {@code /from}, and {@code /to} dates.
     * @param storage The storage instance used to save the updated task list.
     * @param ui The UI instance used to display confirmation or error messages.
     */
    public void addEvent(String description, Storage storage, Ui ui) {
        String[] parts = description.split(" /from | /to ");
        if (parts.length < 3) {
            TaskListException.eventInvalidCommand(ui);
            return;
        }
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            TaskListException.eventInvalidCommand(ui);
            return;
        }
        try {
            tasks.add(new Event(parts[0], parts[1], parts[2]));
            ui.printTaskAdded(this);
            taskCount++;
            storage.save(tasks, taskCount, ui);
        } catch (DateTimeParseException e) {
            TaskListException.invalidDateTimeInput(ui);
        }
    }

    /**
     * Deletes the task at the specified index from the list and saves the updated list.
     * Validates that the description is a valid numeric index.
     *
     * @param description The one-based index of the task to delete, as a string.
     * @param storage The storage instance used to save the updated task list.
     * @param ui The UI instance used to display confirmation or error messages.
     */
    public void deleteTask(String description, Storage storage, Ui ui) {
        if (description.trim().isEmpty() || !description.matches("\\d+")) {
            TaskListException.invalidDelete(ui);
            return;
        }
        int index = Integer.parseInt(description) - 1;
        ui.printTaskDeleted(this, index);
        tasks.remove(index);
        taskCount--;
        storage.save(tasks, taskCount, ui);
    }

    /**
     * Adds a pre-constructed task to the list.
     * Used by {@link storage.Storage} when loading tasks from file.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
        taskCount++;
    }
}
