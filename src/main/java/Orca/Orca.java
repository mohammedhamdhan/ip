package Orca;

import java.util.Scanner;

public class Orca {
    private static Ui ui;
    private static TaskManager taskManager;

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
        default:
            taskManager.printInvalidInputMessage();
        }
    }

    public static void main(String[] args) {
        startOrcaManager();
    }
}
