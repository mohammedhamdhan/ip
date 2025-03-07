package Orca;

import java.util.Scanner;

/**
 * Main class for the Orca task management application.
 * This class serves as the entry point and handles the command-line interface
 * for managing tasks, deadlines, and events.
 */
public class Orca {
    private static Ui ui;
    private static TaskManager taskManager;

    /**
     * Starts the Orca task manager and begins processing user input.
     * This method initializes the UI and TaskManager, displays the welcome message,
     * and enters the main command processing loop.
     */
    public static void startOrcaManager() {
        ui = new Ui();
        taskManager = new TaskManager(ui);
        
        ui.showWelcomeMessage();
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                break;
            }

            commandGiven(input);
        }
        scanner.close();
    }

    /**
     * Processes a user command and delegates it to the appropriate handler.
     * This method parses the command type and routes the command to the
     * corresponding TaskManager method.
     *
     * @param input The user's input command string.
     */
    private static void commandGiven(String input) {
        String commandType = Parser.parseCommandType(input);
        
        switch (commandType) {
        case "list":
            taskManager.printTasks();
            break;
        case "mark":
            taskManager.markTask(input);
            break;
        case "unmark":
            taskManager.unmarkTask(input);
            break;
        case "todo":
            taskManager.addTodoTask(input);
            break;
        case "deadline":
            taskManager.addDeadlineTask(input);
            break;
        case "event":
            taskManager.addEventTask(input);
            break;
        case "help":
            taskManager.printHelpOptions();
            break;
        case "delete":
            taskManager.deleteTask(input);
            break;
        case "find":
            taskManager.findTasks(input);
            break;
        default:
            taskManager.printInvalidInputMessage();
        }
    }

    /**
     * Main entry point of the application.
     * Starts the Orca task manager.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        startOrcaManager();
    }
}
