public class Event extends Task{
    private String event_from;
    private String event_to;

    public Event(String input) {
        super(input.substring(input.indexOf(" ") + 1, input.indexOf("/")),"[E]");
        event_from = input.substring(input.indexOf("/")+6, input.lastIndexOf("/"));
        event_to = input.substring(input.lastIndexOf("/") + 4);
    }

    public String getEvent_from() {
        return event_from;
    }
    public String getEvent_to() {
        return event_to;
    }


}
