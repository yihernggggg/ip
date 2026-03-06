package storage;

import java.util.ArrayList;
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
import ui.Ui;

/**
 * Handles loading tasks from and saving tasks to a file on disk.
 * The file uses a pipe-delimited format where each line represents a task.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the file.
     * Each task is written in its file string format, one per line.
     * Creates parent directories if they do not exist.
     *
     * @param tasks     The list of tasks to save.
     * @param taskCount The number of tasks to save.
     * @param ui        The UI instance used to display error messages if saving fails.
     */
    public void save(ArrayList<Task> tasks, int taskCount, Ui ui) {
        try {
            File data = new File(filePath);
            data.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(data);
            for (int i = 0; i < taskCount; i++) {
                writer.write(tasks.get(i).toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.printException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as a {@link TaskList}.
     * If the file does not exist, an empty TaskList is returned.
     * Corrupted lines are skipped with a warning message.
     *
     * @param ui The UI instance used to display load status messages.
     * @return A {@link TaskList} containing the loaded tasks.
     */
    public TaskList load(Ui ui) {
        TaskList tasks = new TaskList();
        try {
            File data = new File(filePath);
            data.getParentFile().mkdirs();
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLine(line, ui);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ui.printNoTaskLoaded();
            return tasks;
        }

        if (tasks.getTaskCount() == 0) {
            ui.printNoTaskLoaded();
        } else {
            ui.printLoadComplete();
        }

        return tasks;
    }

    /**
     * Parses a single line from the save file into a {@link Task} object.
     * The line is expected to be in pipe-delimited format
     *
     * @param line The line to parse.
     * @param ui   The UI instance used to display error messages for corrupted lines.
     * @return The parsed {@link Task}, or {@code null} if the line is corrupted.
     */
    private Task parseLine(String line, Ui ui) {
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
            ui.printException("Corrupted line: " + line);
            return null;
        }
    }
}