public class TaskList {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void markTask(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
        }
    }

    public void unmarkTask(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
        }
    }

    public void displayTasks() {
        if (taskCount == 0) {
            System.out.println("Your list is empty!");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i].currentStatus());
            }
        }
    }

    public void addToDo(String description) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = new ToDo(description);
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount].currentStatus());
            taskCount++;
            System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    public void addDeadline(String description) {
        if (taskCount < tasks.length) {
            String[] parts = description.split(" /by ");
            tasks[taskCount] = new Deadline(parts[0], parts[1]);
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount].currentStatus());
            taskCount++;
            System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    public void addEvent(String description) {
        if (taskCount < tasks.length) {
            String[] parts = description.split(" /from | /to ");
            tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount].currentStatus());
            taskCount++;
            System.out.printf(" Now you have %d tasks in the list.\n", taskCount);
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }
}
