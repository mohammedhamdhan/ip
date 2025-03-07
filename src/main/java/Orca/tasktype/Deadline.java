package Orca.tasktype;

import Orca.Task;

/**
 * Represents a task with a deadline.
 * A deadline task has a description and a specific time/date by which it needs to be completed.
 */
public class Deadline extends Task {
    private String deadlineDate;

    /**
     * Creates a new Deadline task.
     * 
     * @param description The full command string in the format: "deadline <description> /by <time>"
     */
    public Deadline(String description) {
        super(description.substring(description.indexOf(" ") + 1, description.indexOf("/")).trim(), "[D]");
        String deadlineDueWords = description.substring(description.indexOf("/") + 1);
        deadlineDate = deadlineDueWords.substring(deadlineDueWords.indexOf(" ") + 1).trim();
    }

    /**
     * Gets the deadline date/time of this task.
     * 
     * @return The deadline date/time as a string.
     */
    public String getDeadline() {
        return deadlineDate;
    }

    /**
     * Sets the deadline date/time for this task.
     * 
     * @param deadlineDate The new deadline date/time.
     */
    public void setDeadline(String deadlineDate) {
        this.deadlineDate = deadlineDate.trim();
    }
}
