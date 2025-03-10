package Orca;

import Orca.tasktype.Deadline;
import Orca.tasktype.Event;
import Orca.tasktype.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistent storage of tasks to and from disk.
 * This class manages saving and loading tasks from a file, ensuring that
 * task data persists between program runs. It handles file operations in an
 * OS-independent way and includes error handling for file operations.
 */
public class Storage {
    // Use OS-independent file separators.
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = DATA_DIR + File.separator + "tasks.txt";

    /**
     * Creates a new Storage instance and ensures the data directory exists.
     */
    public Storage() {
        ensureDataDirectoryExists();
    }

    /**
     * Ensures the data directory exists, creating it if necessary.
     * This method is called during initialization to set up the storage structure.
     */
    private void ensureDataDirectoryExists() {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Loads tasks from the data file.
     * If the file doesn't exist or is corrupted, appropriate error handling is performed.
     * Corrupted entries are skipped with a warning message.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(DATA_FILE);

        // If the file doesn't exist, return an empty list.
        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                try {
                    Task task = parseTaskFromLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    System.out.println("Warning: Skipping corrupted task entry: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to the data file.
     * Each task is converted to a string format and written to the file.
     *
     * @param tasks The ArrayList of Task objects to save.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            for (Task task : tasks) {
                writer.write(formatTaskForStorage(task) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a Task into a pipe-delimited string for storage.
     * The format varies depending on the task type:
     * - Todo: T|isDone|description
     * - Deadline: D|isDone|description|deadline
     * - Event: E|isDone|description|from|to
     *
     * @param task The Task object to format.
     * @return A pipe-delimited string representation of the task.
     */
    private String formatTaskForStorage(Task task) {
        StringBuilder sb = new StringBuilder();

        // Add task type and completion status.
        // For example, "[T]" becomes "T".
        sb.append(task.getTaskType().replace("[", "").replace("]", ""))
                .append("|")
                .append(task.isDone() ? "1" : "0")
                .append("|")
                .append(task.getEntry().trim());

        // Add additional details for Deadline or Event tasks.
        if (task instanceof Deadline) {
            sb.append("|").append(((Deadline) task).getDeadline().trim());
        } else if (task instanceof Event) {
            sb.append("|").append(((Event) task).getEvent_from().trim())
                    .append("|").append(((Event) task).getEvent_to().trim());
        }

        return sb.toString();
    }

    /**
     * Parses a line from the data file and returns a Task.
     * The line should be in the format specified by formatTaskForStorage.
     *
     * @param line The line to parse.
     * @return A Task object created from the line.
     * @throws IllegalArgumentException If the line format is invalid.
     */
    private Task parseTaskFromLine(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format");
        }

        String type = parts[0].trim();
        boolean isDone = "1".equals(parts[1].trim());
        String description = parts[2].trim();
        Task task;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline format");
            }
            // Construct the deadline command string.
            task = new Deadline("deadline " + description + " /by " + parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid event format");
            }
            // Construct the event command string.
            task = new Event("event " + description + " /from " + parts[3].trim() + " /to " + parts[4].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.setDone(true);
        }

        return task;
    }
}
