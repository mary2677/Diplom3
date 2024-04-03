package api;

public class Email {
    private int id;
    private String from;
    private String subject;
    private String date;

    public Email() {
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
