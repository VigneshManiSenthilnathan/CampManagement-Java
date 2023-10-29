package sce.sc2002.yys.proj;

import java.time.LocalDateTime;

public class Enquiry {
    private Student student;
    private Camp camp;
    private String message;
    private LocalDateTime submissionTime;
    private String reply; // Reply from committee member

    public Enquiry(Student student, Camp camp, String message) {
        this.student = student;
        this.camp = camp;
        this.message = message;
        this.submissionTime = LocalDateTime.now(); // Timestamp when the enquiry was submitted
    }

    public Student getStudent() {
        return student;
    }

    public Camp getCamp() {
        return camp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
