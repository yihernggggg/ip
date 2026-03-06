package task;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;

import storage.Storage;
import ui.Ui;


public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

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

    public void add(Task task) {
        tasks.add(task);
        taskCount++;
    }
}
