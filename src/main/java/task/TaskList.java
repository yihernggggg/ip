package task;

import storage.Storage;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void markTask(String description, Storage storage) {
        if (description.trim().isEmpty() || !description.matches("\\d+")) {
            System.out.println("  Invalid mark command. Use: mark <index>");
            return;
        }
        int index = Integer.parseInt(description) - 1;
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
            storage.save(tasks, taskCount);
            displayTasks();
        } else {
            TaskListException.markTaskOutOfBounds(taskCount);
        }
    }

    public void unmarkTask(String description, Storage storage) {
        if (description.trim().isEmpty() || !description.matches("\\d+")) {
            System.out.println("  Invalid mark command. Use: mark <index>");
            return;
        }
        int index = Integer.parseInt(description) - 1;
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
            storage.save(tasks, taskCount);
            displayTasks();
        } else {
            TaskListException.markTaskOutOfBounds(taskCount);
        }
    }

    public void displayTasks() {
        if (taskCount == 0) {
            System.out.println(" Your list is empty!");
        } else {
            System.out.println(" Here are your tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("  " + (i + 1) + ". " + tasks[i].currentStatus());
            }
        }
    }

    public void addToDo(String description, Storage storage) {
        if (taskCount < tasks.length) {
            if (description.trim().isEmpty()) {
                TaskListException.todoInvalidCommand();
                return;
            }
            tasks[taskCount] = new ToDo(description);
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount].currentStatus());
            taskCount++;
            storage.save(tasks, taskCount);
            System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
        } else {
            System.out.println(" Task list is full! Cannot add more tasks.");
        }
    }

    public void addDeadline(String description, Storage storage) {
        if (taskCount < tasks.length) {
            String[] parts = description.split(" /by ");
            if (parts.length == 1) {
                TaskListException.deadlineInvalidCommand();
                return;
            }
            if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                TaskListException.deadlineInvalidCommand();
                return;
            }
            tasks[taskCount] = new Deadline(parts[0], parts[1]);
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount].currentStatus());
            taskCount++;
            storage.save(tasks, taskCount);
            System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
        } else {
            System.out.println(" Task list is full! Cannot add more tasks.");
        }
    }

    public void addEvent(String description, Storage storage) {
        if (taskCount < tasks.length) {
            String[] parts = description.split(" /from | /to ");
            if (parts.length < 3) {
                TaskListException.eventInvalidCommand();
                return;
            }
            if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                TaskListException.eventInvalidCommand();
                return;
            }
            tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount].currentStatus());
            taskCount++;
            storage.save(tasks, taskCount);
            System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
        } else {
            System.out.println(" Task list is full! Cannot add more tasks.");
        }
    }

    public void add(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }
}
