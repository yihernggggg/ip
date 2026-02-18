package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import task.Task;
import task.TaskList;
import task.ToDo;
import task.Deadline;
import task.Event;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(Task[] tasks, int taskCount) {
        try {
            File data = new File(filePath);
            data.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(data);
            for (int i = 0; i < taskCount; i++) {
                writer.write(tasks[i].toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            File data = new File(filePath);
            data.getParentFile().mkdirs();
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            System.out.println("  Load complete!");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("  No tasks saved!");
        }
        return tasks;
    }

    private Task parseLine(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                return null;
            }

            if (isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Corrupted line: " + line);
            return null; // handles the corrupted file stretch goal
        }
    }
}