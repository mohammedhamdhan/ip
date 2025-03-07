package Orca;

public class Parser {

    /**
     * Extracts the command type from the input string.
     *
     * @param input The full input string.
     * @return The command type.
     */
    public static String parseCommandType(String input) {
        String[] parts = input.trim().split(" ", 2);
        return parts[0].toLowerCase();
    }

    /**
     * Extracts the description from a todo command.
     *
     * @param input The full todo command.
     * @return The task description.
     * @throws IllegalArgumentException If the description is empty.
     */
    public static String parseTodoDescription(String input) throws IllegalArgumentException {
        input = input.trim();
        if (!input.contains(" ")) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        String description = input.substring(input.indexOf(" ") + 1).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        return description;
    }

    /**
     * Parses a deadline command and extracts relevant information.
     *
     * @param input The full deadline command.
     * @return A string array containing [description, deadline].
     * @throws IllegalArgumentException If the format is incorrect.
     */
    public static String[] parseDeadlineCommand(String input) throws IllegalArgumentException {
        input = input.trim();
        validateCommandNotEmpty(input, "deadline");
        
        String[] parts = input.split("/", -1);
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "A deadline task should contain exactly one '/' separator. " +
                            "Format: deadline <description> /by <time>."
            );
        }
        
        String command = "deadline";
        validateCommandStart(parts[0], command);
        
        String description = parts[0].substring(command.length()).trim();
        validateDescriptionNotEmpty(description, "deadline");
        
        String byPart = parts[1].trim();
        if (!byPart.toLowerCase().startsWith("by")) {
            throw new IllegalArgumentException("Deadline task should specify '/by <time>'.");
        }
        
        String deadlineTime = byPart.substring("by".length()).trim();
        if (deadlineTime.isEmpty()) {
            throw new IllegalArgumentException("The deadline time cannot be empty.");
        }
        
        return new String[]{description, deadlineTime};
    }

    /**
     * Parses an event command and extracts relevant information.
     *
     * @param input The full event command.
     * @return A string array containing [description, from, to].
     * @throws IllegalArgumentException If the format is incorrect.
     */
    public static String[] parseEventCommand(String input) throws IllegalArgumentException {
        input = input.trim();
        validateCommandNotEmpty(input, "event");
        
        String[] parts = input.split("/", -1);
        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "An event task should contain exactly two '/' separators. " +
                            "Format: event <description> /from <start> /to <end>."
            );
        }
        
        String eventCommand = "event";
        validateCommandStart(parts[0], eventCommand);
        
        String description = parts[0].substring(eventCommand.length()).trim();
        validateDescriptionNotEmpty(description, "event");
        
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
        
        return new String[]{description, eventFrom, eventTo};
    }

    /**
     * Extracts the task index from mark, unmark, or delete commands.
     *
     * @param input The full command string.
     * @return The zero-based index of the task.
     * @throws NumberFormatException If the index is not a valid number.
     * @throws IllegalArgumentException If no index is provided.
     */
    public static int parseTaskIndex(String input) throws NumberFormatException, IllegalArgumentException {
        String[] words = input.split(" ");
        if (words.length < 2) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        
        try {
            return Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("'" + words[1] + "' is not a valid task number.");
        }
    }
    
    // Helper methods
    private static void validateCommandNotEmpty(String input, String commandType) {
        if (!input.contains(" ")) {
            throw new IllegalArgumentException("The description of a " + commandType + " cannot be empty.");
        }
    }
    
    private static void validateCommandStart(String part, String commandType) {
        if (!part.toLowerCase().startsWith(commandType)) {
            throw new IllegalArgumentException(commandType + " task must start with '" + commandType + "'.");
        }
    }
    
    private static void validateDescriptionNotEmpty(String description, String commandType) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a " + commandType + " cannot be empty.");
        }
    }
} 