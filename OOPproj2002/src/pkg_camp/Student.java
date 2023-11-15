package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends CampController implements User {
    private String userID;
    private String password;
    private String faculty;
    private User STUDENT;
    private StudentType studentType;

    public Student() {

    }

    public Student(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = "password";
        this.faculty = faculty;
    }

    enum StudentType {
        ATTENDEE, COMMITTEE
    }

    // Implement the methods from the User interface
    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getFaculty() {
        return faculty;
    }

    public User getUserClass() {
        return STUDENT;
    }

    public void setStudentType(StudentType type) {
        if (type == StudentType.ATTENDEE || type == StudentType.COMMITTEE) {
            this.studentType = type;
        } else {
            System.out.println("Invalid Student Type!");
        }
    }

    public StudentType getStudentType() {
        return this.studentType;
    }

    // New camp methods
    // Showing available camps
    // cant have an input parameter here -> wait why not please explain with PEEL

}

// Register for a camp
/*
 * public void registerForCamp(Student student, List<CampInfoController>
 * createdCamps) {
 * // Showing available camps
 * int i = 1;
 * boolean done = false;
 * while (!done) {
 * // List all the camps available to join
 * System.out.println("List of Camps available to join:");
 * for (CampInfoController camp : createdCamps) {
 * if (camp.getUserGroup().equals(this.getFaculty())
 * && (camp.getTotalSlots() - camp.getAttendees().size()) > 0) {
 * boolean userAlreadyAttended = false;
 * 
 * for (Student attendee : camp.getAttendees()) {
 * if (this.getUserID().equals(attendee.getUserID())) {
 * userAlreadyAttended = true;
 * break; // No need to check further, the user has attended this camp
 * }
 * }
 * 
 * if (!userAlreadyAttended) {
 * System.out.println(i + ". " + camp.getCampName() + " - " +
 * camp.getDescription() + " | ");
 * System.out.println("(Slots left: " + (camp.getTotalSlots() -
 * camp.getAttendees().size()) + ")");
 * i++;
 * }
 * }
 * }
 * 
 * // Register for an available Camp
 * System.out.println("Register for Camp: ");
 * Scanner register = new Scanner(System.in);
 * String campname = register.nextLine();
 * 
 * for (CampInfoController camp : createdCamps) {
 * if (campname == camp.getCampName()) {
 * for (Student applicant : camp.getCampCommittee()) {
 * if (applicant.getUserID() ==
 * camp.getCampCommitteeUserID(applicant.getUserID())) {
 * 
 * }
 * }
 * camp.addAttendee(student);
 * 
 * }
 * }
 * }
 * // register.close(); //closing the scanner doesnt work
 * }
 * 
 * 
 * *********************************************************************
 * 
 * 
 * // Enquiry Methods - Deprecated
 * 
 * // Input decides whether staff/campComm receives it
 * public void newEnquiry(User receiver) {
 * 
 * Scanner msg = new Scanner(System.in);
 * String message = msg.nextLine();
 * EnquiryController enquiry = new EnquiryController(this, receiver, message);
 * enquiries.add(enquiry);
 * msg.close();
 * }
 * 
 * // Method to get the list of enquiries
 * public List<EnquiryController> getEnquiries() {
 * return enquiries;
 * }
 * 
 * // Method to edit an existing enquiry
 * public void editEnquiry(EnquiryController enquiry, String newMessage) {
 * 
 * int index = enquiries.indexOf(enquiry);
 * if (index != -1) {
 * System.out.println("Original Message: " + enquiries.get(index).getMessage());
 * enquiries.get(index).setMessage(newMessage);
 * }
 * }
 * 
 * // Method to delete an enquiry
 * public void deleteEnquiry(EnquiryController enquiry) {
 * enquiries.remove(enquiry);
 * }
 * }
 * 
 */
