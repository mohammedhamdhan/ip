package Orca;

import Orca.tasktype.Deadline;
import Orca.tasktype.Event;
import Orca.tasktype.Todo;

import java.util.ArrayList;

/**
 * Represents a list of tasks with operations to add, delete, and modify tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    
    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    /**
     * Constructs a TaskList with the given tasks.
     * 
     * @param tasks The initial tasks in the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Gets the size of the task list.
     * 
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Checks if the task list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    
    /**
     * Gets all tasks in the list.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    /**
     * Gets a task at the specified index.
     * 
     * @param index The index of the task.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Task index out of range: " + index);
        }
        return tasks.get(index);
    }
    
    /**
     * Adds a new todo task to the list.
     * 
     * @param description The description of the todo task.
     * @return The added todo task.
     */
    public Todo addTodo(String description) {
        Todo newTask = new Todo(description);
        tasks.add(newTask);
        return newTask;
    }
    
    /**
     * Adds a new deadline task to the list.
     * 
     * @param formattedInput The formatted input for the deadline task.
     * @return The added deadline task.
     */
    public Deadline addDeadline(String formattedInput) {
        Deadline newTask = new Deadline(formattedInput);
        tasks.add(newTask);
        return newTask;
    }
    
    /**
     * Adds a new event task to the list.
     * 
     * @param formattedInput The formatted input for the event task.
     * @return The added event task.
     */
    public Event addEvent(String formattedInput) {
        Event newTask = new Event(formattedInput);
        tasks.add(newTask);
        return newTask;
    }
    
    /**
     * Marks a task as done.
     * 
     * @param index The index of the task to mark.
     * @return The marked task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task markTaskAsDone(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Task index out of range: " + index);
        }
        Task task = tasks.get(index);
        task.setDone(true);
        return task;
    }
    
    /**
     * Marks a task as not done.
     * 
     * @param index The index of the task to unmark.
     * @return The unmarked task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     * @throws IllegalStateException If the task is already not done.
     */
    public Task markTaskAsNotDone(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Task index out of range: " + index);
        }
        Task task = tasks.get(index);
        if (!task.isDone()) {
            throw new IllegalStateException("Task is already not done.");
        }
        task.setDone(false);
        return task;
    }
    
    /**
     * Deletes a task from the list.
     * 
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task deleteTask(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Task index out of range: " + index);
        }
        return tasks.remove(index);
    }
    
    /**
     * Checks if an index is valid for this task list.
     * 
     * @param index The index to check.
     * @return true if the index is valid, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
} 