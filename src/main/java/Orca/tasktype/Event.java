package Orca.tasktype;

import Orca.Task;

/**
 * Represents an event task.
 * An event task has a description and a specific time period (from start time to end time).
 */
public class Event extends Task {
    private String event_from;
    private String event_to;

    /**
     * Creates a new Event task.
     * 
     * @param input The full command string in the format: "event <description> /from <start time> /to <end time>"
     */
    public Event(String input) {
        super(input.substring(input.indexOf(" ") + 1, input.indexOf("/")).trim(), "[E]");
        event_from = input.substring(input.indexOf("/") + 6, input.lastIndexOf("/")).trim();
        event_to = input.substring(input.lastIndexOf("/") + 4).trim();
    }

    /**
     * Gets the start time of the event.
     * 
     * @return The start time as a string.
     */
    public String getEvent_from() {
        return event_from;
    }

    /**
     * Gets the end time of the event.
     * 
     * @return The end time as a string.
     */
    public String getEvent_to() {
        return event_to;
    }
}
