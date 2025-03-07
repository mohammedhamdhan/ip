package Orca;

import Orca.tasktype.Deadline;
import Orca.tasktype.Event;
import Orca.tasktype.Todo;
import java.util.ArrayList;

public class TaskManager {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public TaskManager(Ui ui) {
        this.ui = ui;
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
    }

    private void saveTasksToStorage() {
        storage.saveTasks(taskList.getTasks());
    }

    public void printTasks() {
        ui.showTaskList(taskList.getTasks());
    }

    public void addTodoTask(String taskDescription) {
        try {
            String description = Parser.parseTodoDescription(taskDescription);
            Todo newEntry = taskList.addTodo(description);
            saveTasksToStorage();
            ui.showAddedTodoTask(newEntry);
        } catch (Exception e) {
            ui.showErrorMessage("Error adding todo task: " + e.getMessage());
        }
    }

    public void addDeadlineTask(String taskDescription) {
        try {
            String[] deadlineInfo = Parser.parseDeadlineCommand(taskDescription);
            String description = deadlineInfo[0];
            String deadlineTime = deadlineInfo[1];
            
            // Use the original format for Deadline constructor compatibility
            String formattedInput = "deadline " + description + " /by " + deadlineTime;
            Deadline newEntry = taskList.addDeadline(formattedInput);
            
            saveTasksToStorage();
            ui.showAddedDeadlineTask(newEntry, deadlineTime);
        } catch (Exception e) {
            ui.showErrorMessage("Error adding deadline task: " + e.getMessage());
        }
    }

    public void addEventTask(String taskDescription) {
        try {
            String[] eventInfo = Parser.parseEventCommand(taskDescription);
            String description = eventInfo[0];
            String eventFrom = eventInfo[1];
            String eventTo = eventInfo[2];
            
            // Use the original format for Event constructor compatibility
            String formattedInput = "event " + description + " /from " + eventFrom + " /to " + eventTo;
            Event newEntry = taskList.addEvent(formattedInput);
            
            saveTasksToStorage();
            ui.showAddedEventTask(newEntry, eventFrom, eventTo);
        } catch (Exception e) {
            ui.showErrorMessage("Error adding event task: " + e.getMessage());
        }
    }

    public void markTask(String input) {
        try {
            int index = Parser.parseTaskIndex(input);
            if (taskList.isValidIndex(index)) {
                Task markedTask = taskList.markTaskAsDone(index);
                saveTasksToStorage();
                ui.showMarkedTask(markedTask);
            } else {
                ui.showInvalidIndexMessage();
            }
        } catch (Exception e) {
            ui.showErrorMessage("Error marking task: " + e.getMessage());
        }
    }

    public void unmarkTask(String input) {
        try {
            int index = Parser.parseTaskIndex(input);
            if (taskList.isValidIndex(index) && taskList.getTask(index).isDone()) {
                Task unmarkedTask = taskList.markTaskAsNotDone(index);
                saveTasksToStorage();
                ui.showUnmarkedTask(unmarkedTask);
            } else {
                ui.showInvalidIndexMessage();
            }
        } catch (Exception e) {
            ui.showErrorMessage("Error unmarking task: " + e.getMessage());
        }
    }

    public void printInvalidInputMessage() {
        ui.showInvalidInputMessage();
    }

    public void printHelpOptions() {
        ui.showHelpOptions();
    }

    public void deleteTask(String input) {
        try {
            int index = Parser.parseTaskIndex(input);
            if (taskList.isValidIndex(index)) {
                Task removedTask = taskList.deleteTask(index);
                saveTasksToStorage();
                ui.showDeletedTask(removedTask, taskList.size());
            } else {
                ui.showInvalidIndexMessage();
            }
        } catch (Exception e) {
            ui.showErrorMessage("Error deleting task: " + e.getMessage());
        }
    }

    /**
     * Finds tasks that match the given keyword in the task description.
     *
     * @param input The search command input.
     */
    public void findTasks(String input) {
        try {
            String keyword = Parser.parseSearchKeyword(input);
            ArrayList<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
            ui.showSearchResults(matchingTasks, keyword);
        } catch (Exception e) {
            ui.showErrorMessage("Error searching tasks: " + e.getMessage());
        }
    }
}
