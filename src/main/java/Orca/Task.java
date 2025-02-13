package Orca;

public class Task {
    private String entry = "";
    private boolean isDone = false;
    private String taskType = "";


    public String getEntry() {
        return entry;
    }
    public void setEntry(String entry) {
        this.entry = entry;
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X": " ");
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Task(String entry, String taskType) {
        this.entry = entry;
        this.taskType = taskType;
    }
}
