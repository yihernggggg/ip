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

    public void addTask(String description) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = new Task(description);
            System.out.println("added: " + description);
            taskCount++;
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

}
