package task;

import java.util.ArrayList;
import storage.Storage;


public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public void markTask(String description, Storage storage) {
        if (description.trim().isEmpty() || !description.matches("\\d+")) {
            System.out.println("  Invalid mark command. Use: mark <index>");
            return;
        }
        int index = Integer.parseInt(description) - 1;
        if (index >= 0 && index < taskCount) {
            tasks.get(index).markAsDone();
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
            tasks.get(index).markAsNotDone();
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
                System.out.println("  " + (i + 1) + ". " + tasks.get(i).currentStatus());
            }
        }
    }
    
    public void addToDo(String description, Storage storage) {
        if (description.trim().isEmpty()) {
            TaskListException.todoInvalidCommand();
            return;
        }
        
        tasks.add(new ToDo(description));
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + tasks.get(taskCount).currentStatus());
        taskCount++;
        storage.save(tasks, taskCount);
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
    }
    
    public void addDeadline(String description, Storage storage) {
        String[] parts = description.split(" /by ");
        if (parts.length == 1) {
            TaskListException.deadlineInvalidCommand();
            return;
        }
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            TaskListException.deadlineInvalidCommand();
            return;
        }
        
        tasks.add(new Deadline(parts[0], parts[1]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + tasks.get(taskCount).currentStatus());
        taskCount++;
        storage.save(tasks, taskCount);
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount);

    }
    
    public void addEvent(String description, Storage storage) {
        String[] parts = description.split(" /from | /to ");
        if (parts.length < 3) {
            TaskListException.eventInvalidCommand();
            return;
        }
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            TaskListException.eventInvalidCommand();
            return;
        }
        
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + tasks.get(taskCount).currentStatus());
        taskCount++;
        storage.save(tasks, taskCount);
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
    }

    public void deleteTask(String description) {
        if (description.trim().isEmpty() || !description.matches("\\d+")) {
            System.out.println("  Invalid delete command. Use: delete <index>");
            return;
        }
        int index = Integer.parseInt(description) - 1;
        System.out.println(" Got it. I've removed this task:");
        System.out.println("  " + tasks.get(index).currentStatus());
        tasks.remove(index);
        taskCount--;
        System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
    }

    public void add(Task task) {
        tasks.add(task);
        taskCount++;
    }
}
