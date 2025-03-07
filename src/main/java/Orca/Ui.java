package Orca;

import Orca.tasktype.Deadline;
import Orca.tasktype.Event;

public class Ui {
    public static final String LINE = "----------------------------------------";
    
    public Ui() {
        // Constructor
    }
    
    public void showWelcomeMessage() {
        System.out.println("  ___    ____     ____      _   ");
        System.out.println(" / _ \\  |  _ \\   / ___|    / \\ ");
        System.out.println("| | | | | |_) | | |       / _ \\");
        System.out.println("| |_| | |  _ <  | |___   / ___ \\");
        System.out.println(" \\___/  |_| \\_\\  \\____| /_/   \\_\\");
        System.out.println();
        System.out.println(LINE);
        System.out.println("Hello! I'm Orca ");
        System.out.println("What can I do for you today?");
        System.out.println(LINE);
    }
    
    public void showGoodbyeMessage() {
        System.out.println(LINE + "\nBye! Hope to see you again soon :)\n" + LINE);
    }
    
    public void showTaskList(java.util.ArrayList<Task> tasks) {
        System.out.println(LINE + "\nYour todo list:\n");
        if (tasks.isEmpty()) {
            System.out.println("Nothing in your list yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                StringBuilder taskStr = new StringBuilder();
                taskStr.append(String.format("%d.[%s][%s] %s", 
                    i + 1,
                    task.getTaskType().replace("[", "").replace("]", ""),
                    task.getStatusIcon(),
                    task.getEntry()));
                
                if (task instanceof Deadline) {
                    taskStr.append(" (by: ").append(((Deadline) task).getDeadline()).append(")");
                } else if (task instanceof Event) {
                    taskStr.append(" (from: ").append(((Event) task).getEvent_from())
                           .append(" to: ").append(((Event) task).getEvent_to()).append(")");
                }
                
                System.out.println(taskStr.toString());
            }
        }
        System.out.println("\n" + LINE);
    }
    
    public void showAddedTodoTask(Task task) {
        System.out.println(LINE + "\nAwesome! I've added this task: \n\n" + (task.getTaskType() + "[ ]" + task.getEntry()).indent(5) + "\n" + LINE);
    }

    public void showAddedDeadlineTask(Task task, String deadline) {
        System.out.println(LINE + "\nAwesome! I've added this task: \n\n" + (task.getTaskType() + "[ ]" + task.getEntry() + " (by: " + deadline + ")").indent(5) + "\n" + LINE);
    }

    public void showAddedEventTask(Task task, String from, String to) {
        System.out.println(LINE + "\nAwesome! I've added this task: \n\n" + (task.getTaskType() + "[ ]" + task.getEntry() + " (from: " + from + " to: " + to + ")").indent(5) + "\n" + LINE);
    }
    
    public void showMarkedTask(Task task) {
        System.out.println(LINE + "\nAwesome! Congrats on finishing this task!\n");
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getEntry() + "\n");
        System.out.println(LINE);
    }
    
    public void showUnmarkedTask(Task task) {
        System.out.println(LINE + "\nOkay, I have unmarked this task!\n");
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getEntry() + "\n");
        System.out.println(LINE);
    }
    
    public void showDeletedTask(Task task, int remainingTasks) {
        System.out.println(LINE + "\nNoted. I've removed this task:\n");
        System.out.println("  " + task.getTaskType() + "[" + task.getStatusIcon() + "] " + task.getEntry() + "\n");
        System.out.println("Now you have " + remainingTasks + (remainingTasks == 1 ? " task" : " tasks") + " in the list.\n");
        System.out.println(LINE);
    }
    
    public void showInvalidIndexMessage() {
        System.out.println(LINE + "\nInvalid input! Please provide a valid index. Use 'list' to see your tasks.\n" + LINE);
    }
    
    public void showInvalidInputMessage() {
        System.out.println(LINE + "\nInvalid input! Please provide a valid input. Use 'help' to see a list of commands.\n" + LINE);
    }
    
    public void showErrorMessage(String message) {
        System.out.println(LINE + "\n" + message + "\n" + LINE);
    }
    
    public void showHelpOptions() {
        System.out.println(LINE);
        System.out.println("Available Commands:");
        System.out.println("  list                            - Display all tasks in your list.");
        System.out.println("  mark <task number>              - Mark a task as done.");
        System.out.println("  unmark <task number>            - Mark a task as not done.");
        System.out.println("  todo <description>              - Add a new todo task.");
        System.out.println("  deadline <description> /by <time>  - Add a new deadline task. (Ensure exactly one '/' is used)");
        System.out.println("  event <description> /from <start time> /to <end time> - Add a new event task. (Ensure exactly two '/' are used)");
        System.out.println("  delete <task number>            - Delete a task.");
        System.out.println("  help                            - Display this help message.");
        System.out.println("  bye                             - Exit the application.");
        System.out.println(LINE);
    }
} 