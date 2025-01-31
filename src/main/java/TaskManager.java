public class TaskManager {
    private final Task[] tasks;
    private int taskCount;

    public TaskManager(int maxSize) {
        this.tasks = new Task[maxSize];
        this.taskCount = 0;
    }

    public void printTasks() {
        System.out.println(Orca.LINE + "\nYour todo list:\n");
        if (taskCount == 0) {
            System.out.println("Nothing in your list yet!");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("  %d. [%s] %s%n", i + 1, tasks[i].getStatusIcon(), tasks[i].getEntry());
            }
        }
        System.out.println(Orca.LINE);
    }

    public void addTask(String taskDescription) {
        if (taskCount >= tasks.length) {
            System.out.println(Orca.LINE + "\nError: Task list is full!\n" + Orca.LINE);
            return;
        }
        Task task = new Task();
        task.setEntry(taskDescription);
        tasks[taskCount++] = task;
        System.out.println(Orca.LINE + "\nAdded: " + task.getEntry() + "\n" + Orca.LINE);
    }

    public void markTask(String input) {
        int index = getIndexFromInput(input);
        if (isValidIndex(index)) {
            tasks[index].setDone(true);
            System.out.println(Orca.LINE + "\nAwesome! Congrats on finishing this task!");
            System.out.println("  [" + tasks[index].getStatusIcon() + "] " + tasks[index].getEntry());
        } else {
            printInvalidIndexMessage();
        }
        System.out.println(Orca.LINE);
    }

    public void unmarkTask(String input) {
        int index = getIndexFromInput(input);
        if (isValidIndex(index) && tasks[index].isDone()) {
            tasks[index].setDone(false);
            System.out.println(Orca.LINE + "\nOkay, I have unmarked this task!");
            System.out.println("  [" + tasks[index].getStatusIcon() + "] " + tasks[index].getEntry());
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
        return index >= 0 && index < taskCount;
    }

    private void printInvalidIndexMessage() {
        System.out.println(Orca.LINE + "\nInvalid input! Please provide a valid index. Use 'list' to see your tasks.");
    }
}
