package Orca;

import Orca.tasktype.Deadline;
import Orca.tasktype.Event;
import Orca.tasktype.Todo;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskManager() {
        this.storage = new Storage();
        this.tasks = storage.loadTasks();
    }

    private void saveTasksToStorage() {
        storage.saveTasks(tasks);
    }

    public void printTasks() {
        System.out.println(Orca.LINE + "\nYour todo list:\n");
        if (tasks.isEmpty()) {
            System.out.println("Nothing in your list yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                StringBuilder taskStr = new StringBuilder();
                // Trim the entry to remove extra spaces after the description.
                String entry = task.getEntry().trim();
                taskStr.append(String.format("  %d. %s[%s] %s",
                        i + 1,
                        task.getTaskType(),
                        task.getStatusIcon(),
                        entry));

                if (task instanceof Deadline) {
                    String deadline = ((Deadline) task).getDeadline();
                    // Remove extra spaces so that only one space exists between words.
                    deadline = deadline.trim().replaceAll("\\s+", " ");
                    taskStr.append(" (by: ").append(deadline).append(")");
                } else if (task instanceof Event) {
                    String eventFrom = ((Event) task).getEvent_from();
                    String eventTo = ((Event) task).getEvent_to();
                    eventFrom = eventFrom.trim().replaceAll("\\s+", " ");
                    eventTo = eventTo.trim().replaceAll("\\s+", " ");
                    taskStr.append(" (from: ").append(eventFrom).append(" to: ").append(eventTo).append(")");
                }
                System.out.println(taskStr.toString());
            }
        }
        System.out.println(Orca.LINE);
    }

    public void addTodoTask(String taskDescription) {
        try {
            taskDescription = taskDescription.trim();
            // Ensure there is a description after the command.
            if (!taskDescription.contains(" ")) {
                throw new IllegalArgumentException("The description of a todo cannot be empty.");
            }
            String description = taskDescription.substring(taskDescription.indexOf(" ") + 1).trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("The description of a todo cannot be empty.");
            }
            Todo newEntry = new Todo(description);
            tasks.add(newEntry);
            saveTasksToStorage();
            printAddedTodoTask(newEntry);
        } catch (Exception e) {
            System.out.println(Orca.LINE + "\nError adding todo task: " + e.getMessage() + "\n" + Orca.LINE);
        }
    }

    public void addDeadlineTask(String taskDescription) {
        try {
            taskDescription = taskDescription.trim();
            if (!taskDescription.contains(" ")) {
                throw new IllegalArgumentException("The description of a deadline cannot be empty.");
            }
            String[] parts = taskDescription.split("/", -1);
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        "A deadline task should contain exactly one '/' separator. " +
                                "Format: deadline <description> /by <time>."
                );
            }
            String command = "deadline";
            if (!parts[0].toLowerCase().startsWith(command)) {
                throw new IllegalArgumentException("Deadline task must start with 'deadline'.");
            }
            String description = parts[0].substring(command.length()).trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("The description of a deadline cannot be empty.");
            }
            String byPart = parts[1].trim();
            if (!byPart.toLowerCase().startsWith("by")) {
                throw new IllegalArgumentException("Deadline task should specify '/by <time>'.");
            }
            String deadlineTime = byPart.substring("by".length()).trim();
            if (deadlineTime.isEmpty()) {
                throw new IllegalArgumentException("The deadline time cannot be empty.");
            }
            Deadline newEntry = new Deadline(taskDescription);
            tasks.add(newEntry);
            saveTasksToStorage();
            printAddedDeadlineTask(newEntry, deadlineTime);
        } catch (Exception e) {
            System.out.println(Orca.LINE + "\nError adding deadline task: " + e.getMessage() + "\n" + Orca.LINE);
        }
    }

    public void addEventTask(String taskDescription) {
        try {
            taskDescription = taskDescription.trim();
            if (!taskDescription.contains(" ")) {
                throw new IllegalArgumentException("The description of an event cannot be empty.");
            }
            String[] parts = taskDescription.split("/", -1);
            if (parts.length != 3) {
                throw new IllegalArgumentException(
                        "An event task should contain exactly two '/' separators. " +
                                "Format: event <description> /from <start> /to <end>."
                );
            }
            String eventCommand = "event";
            if (!parts[0].toLowerCase().startsWith(eventCommand)) {
                throw new IllegalArgumentException("Event task must start with 'event'.");
            }
            String description = parts[0].substring(eventCommand.length()).trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("The description of an event cannot be empty.");
            }
            String fromPart = parts[1].trim();
            if (!fromPart.toLowerCase().startsWith("from")) {
                throw new IllegalArgumentException("Event task should specify '/from <start>'.");
            }
            String eventFrom = fromPart.substring("from".length()).trim();
            if (eventFrom.isEmpty()) {
                throw new IllegalArgumentException("The start time (from) cannot be empty.");
            }
            String toPart = parts[2].trim();
            if (!toPart.toLowerCase().startsWith("to")) {
                throw new IllegalArgumentException("Event task should specify '/to <end>'.");
            }
            String eventTo = toPart.substring("to".length()).trim();
            if (eventTo.isEmpty()) {
                throw new IllegalArgumentException("The end time (to) cannot be empty.");
            }
            Event newEntry = new Event(taskDescription);
            tasks.add(newEntry);
            saveTasksToStorage();
            printAddedEventTask(newEntry, eventFrom, eventTo);
        } catch (Exception e) {
            System.out.println(Orca.LINE + "\nError adding event task: " + e.getMessage() + "\n" + Orca.LINE);
        }
    }

    private static void printAddedTodoTask(Task task) {
        System.out.println(Orca.LINE + "\nAwesome! I've added this task: \n\n" +
                (task.getTaskType() + "[ ]" + task.getEntry()).indent(5) +
                "\n" + Orca.LINE);
    }

    private static void printAddedDeadlineTask(Task task, String deadline) {
        System.out.println(Orca.LINE + "\nAwesome! I've added this task: \n\n" +
                (task.getTaskType() + "[ ]" + task.getEntry() + " (by: " + deadline + ")").indent(5) +
                "\n" + Orca.LINE);
    }

    private static void printAddedEventTask(Task task, String from, String to) {
        System.out.println(Orca.LINE + "\nAwesome! I've added this task: \n\n" +
                (task.getTaskType() + "[ ]" + task.getEntry() + " (from: " + from + " to: " + to + ")").indent(5) +
                "\n" + Orca.LINE);
    }

    public void markTask(String input) {
        int index = getIndexFromInput(input);
        if (isValidIndex(index)) {
            tasks.get(index).setDone(true);
            saveTasksToStorage();
            System.out.println(Orca.LINE + "\nAwesome! Congrats on finishing this task!\n");
            System.out.println("  [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getEntry());
        } else {
            printInvalidIndexMessage();
        }
        System.out.println(Orca.LINE);
    }

    public void unmarkTask(String input) {
        int index = getIndexFromInput(input);
        if (isValidIndex(index) && tasks.get(index).isDone()) {
            tasks.get(index).setDone(false);
            saveTasksToStorage();
            System.out.println(Orca.LINE + "\nOkay, I have unmarked this task!\n");
            System.out.println("  [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getEntry());
        } else {
            printInvalidIndexMessage();
        }
        System.out.println(Orca.LINE);
    }

    private int getIndexFromInput(String input) {
        String[] words = input.split(" ");
        try {
            return (words.length > 1) ? Integer.parseInt(words[1]) - 1 : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    private void printInvalidIndexMessage() {
        System.out.println(Orca.LINE + "\nInvalid input! Please provide a valid index. Use 'list' to see your tasks.\n" + Orca.LINE);
    }

    public void printInvalidInputMessage() {
        System.out.println(Orca.LINE + "\nInvalid input! Please provide a valid input. Use 'help' to see a list of commands.\n" + Orca.LINE);
    }

    public void printHelpOptions() {
        System.out.println(Orca.LINE);
        System.out.println("Available Commands:");
        System.out.println("  list                            - Display all tasks in your list.");
        System.out.println("  mark <task number>              - Mark a task as done.");
        System.out.println("  unmark <task number>            - Mark a task as not done.");
        System.out.println("  todo <description>              - Add a new todo task.");
        System.out.println("  deadline <description> /by <time>  - Add a new deadline task. (Ensure exactly one '/' is used)");
        System.out.println("  event <description> /from <start time> /to <end time> - Add a new event task. (Ensure exactly two '/' are used)");
        System.out.println("  help                            - Display this help message.");
        System.out.println("  bye                             - Exit the application.");
        System.out.println(Orca.LINE);
    }

    public void deleteTask(String input) {
        int index = getIndexFromInput(input);
        if (isValidIndex(index)) {
            Task removedTask = tasks.remove(index);
            saveTasksToStorage();
            System.out.println(Orca.LINE + "\nNoted. I've removed this task:\n");
            System.out.println("  " + removedTask.getTaskType() + "[" + removedTask.getStatusIcon() + "] " + removedTask.getEntry() + "\n");
            System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            System.out.println(Orca.LINE);
        } else {
            printInvalidIndexMessage();
        }
    }
}
