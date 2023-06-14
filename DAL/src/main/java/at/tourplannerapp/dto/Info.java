package at.tourplannerapp.dto;

public class Info {
    private Copyright copyright;
    private long statuscode;
    private Object[] messages;

    public Copyright getCopyright() { return copyright; }
    public void setCopyright(Copyright value) { this.copyright = value; }

    public long getStatuscode() { return statuscode; }
    public void setStatuscode(long value) { this.statuscode = value; }

    public Object[] getMessages() { return messages; }
    public void setMessages(Object[] value) { this.messages = value; }
}
