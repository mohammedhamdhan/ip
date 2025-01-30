import java.util.Scanner;

public class Orca {
    public static final String LINE = "----------------------------------------";
    public static final TaskManager taskManager = new TaskManager(100);

    public static void startOrcaManager() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(LINE + "\nBye! Hope to see you again soon :)\n" + LINE);
                break;
            }

            switch (input.split(" ")[0].toLowerCase()) {
            case "list":
                taskManager.printTasks();
                break;
            case "mark":
                taskManager.markTask(input);
                break;
            case "unmark":
                taskManager.unmarkTask(input);
                break;
            default:
                taskManager.addTask(input);
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        String logo =
                "  ___    ____     ____      _   \n"
                        + " / _ \\  |  _ \\   / ___|    / \\ \n"
                        + "| | | | | |_) | | |       / _ \\ \n"
                        + "| |_| | |  _ <  | |___   / ___ \\ \n"
                        + " \\___/  |_| \\_\\  \\____| /_/   \\_\\\n";

        System.out.println(logo + "\n" + LINE);
        System.out.println("Hello! I'm Orca \nWhat can I do for you today?\n" + LINE);
        startOrcaManager();
    }
}
