package Orca.tasktype;

import Orca.Task;

public class Deadline extends Task {
    private  String deadlineDate;

    public Deadline(String description){
        super(description.substring(description.indexOf(" ") + 1,description.indexOf("/")), "[D]");
        String deadlineDueWords = description.substring(description.indexOf("/")+1);
        deadlineDate = deadlineDueWords.substring(deadlineDueWords.indexOf(" ") + 1);
    }


    public String getDeadline() {
        return deadlineDate;
    }

    public void setDeadline(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}
