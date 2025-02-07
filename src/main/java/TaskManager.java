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
                System.out.printf("  %d. %s [%s] %s%n", i + 1, tasks[i].getTaskType() ,tasks[i].getStatusIcon(), tasks[i].getEntry());
            }
        }
        System.out.println(Orca.LINE);
    }

    public void addTodoTask(String taskDescription) {
        if (checkListFull()) return;
        taskDescription = taskDescription.trim();
        Todo newEntry = new Todo(taskDescription.substring(taskDescription.indexOf(" ") + 1));
        tasks[taskCount++] = newEntry;
        printAddedTodoTask(newEntry);
    }

    public void addDeadlineTask(String taskDescription) {
        if (checkListFull()) return;
        taskDescription = taskDescription.trim();
        Deadline newEntry = new Deadline(taskDescription);
        tasks[taskCount++] = newEntry;
        printAddedDeadlineTask(newEntry, newEntry.getDeadline());
    }

    public void addEventTask(String taskDescription) {
        if (checkListFull()) return;
        taskDescription = taskDescription.trim();
        Event newEntry = new Event(taskDescription);
        tasks[taskCount++] = newEntry;
        printAddedEventTask(newEntry, newEntry.getEvent_from(), newEntry.getEvent_to());
    }

    private boolean checkListFull() {
        if (taskCount >= tasks.length) {
            System.out.println(Orca.LINE + "\nError: Task list is full!\n" + Orca.LINE);
            return true;
        }
        return false;
    }

    private static void printAddedTodoTask(Task task) {
        System.out.println(Orca.LINE + "\nAwesome! I've added this task: \n\n" + (task.getTaskType() + "[ ]" + task.getEntry()).indent(5) + "\n" + Orca.LINE);
    }

    private static void printAddedDeadlineTask(Task task, String deadline) {
        System.out.println(Orca.LINE + "\nAwesome! I've added this task: \n\n" + (task.getTaskType() + "[ ]" + task.getEntry() + " (by: " + deadline + ")").indent(5) + "\n" + Orca.LINE);
    }

    private static void printAddedEventTask(Task task, String from, String to) {
        System.out.println(Orca.LINE + "\nAwesome! I've added this task: \n\n" + (task.getTaskType() + "[ ]" + task.getEntry() + " (from: " + from + "to: " + to + ")").indent(5) + "\n" + Orca.LINE);
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

    public void printInvalidInputMessage() {
        System.out.println(Orca.LINE + "\nInvalid input! Please provide a valid input. Use 'help' to see a list of commands.");
    }

}
