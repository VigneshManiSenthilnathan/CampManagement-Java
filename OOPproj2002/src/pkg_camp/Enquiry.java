package pkg_camp;

public class Enquiry {
    private Student sender;
    private User receiver; // User can be either Staff or CampCommitteeMember
    private String message;
    private String reply;

    public Enquiry(Student sender, User receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    // Getters and Setters
    public Student getSender() {
        return sender;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Sender: " + sender.getUserID() + "\nReceiver: " + receiver.getUserID() + "\nMessage: " + message
                + "\nReply: " + reply;
    }
}
